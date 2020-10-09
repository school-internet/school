package com.school.internet.corn.config;


import com.school.internet.equip.entity.EqReceive;

import com.school.internet.equip.entity.EquipdocVO;
import com.school.internet.equip.mapper.EqEquipdocMapper;
import com.school.internet.equip.service.impl.EqEquipdocServiceImpl;
import com.school.internet.equip.service.impl.EqReceiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Component
public class Dcc_client {
	public static final String ENCODING = "utf-8";
	static final byte g_hdr_id[] = { 0x7e, 0x7e, 0x7e, 0x7e };
	public static final int IMEI_LEN = 15;
	public static final int DTU_NAME = 15;
	public static final int MAX_MSG_LEN = 1492;
	static final int HEAD_ID_LEN = 4;
	private static final int DC_MSG_NEED_AUTH = 0x0D;
	private static final int MAX_AUTH_WAIT = 100;
	private static final int AUTH_RANDOM_LEN = 4;
	private static final byte DC_MSG_AUTH = 0x50;
	private static final int PASSWD_MD5_LEN = 16;
	private static final int USER_NAME_LEN = 16;
	private static final byte DC_MSG_AUTH_RESULT = 0x51;
	private static final byte AUTHRESULT_PASSED = 0x00;



   @Autowired
	private EqEquipdocServiceImpl iEqEquipdocService;

   @Autowired
	private EqReceiveServiceImpl iEqReceiveService;




	public  SocketChannel dcc_Socket(String dc_ip, int mServerPort) {
		// 返回SocketChannel实例，并绑定SocketAddress
		try {
			SocketAddress add = new InetSocketAddress(dc_ip, mServerPort);
			SocketChannel client = SocketChannel.open(add);
			client.configureBlocking(false);
			// client.connect(new InetSocketAddress("localhost", mServerPort));

			return client;
		} catch (Exception e) {
			return null;
		}
	}

	public  BufferedReader dcc_instream(Socket socket) throws IOException {
		BufferedReader Input = new BufferedReader(new InputStreamReader(socket
				.getInputStream()));
		return Input;
	}

	public  PrintWriter dcc_getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(socketOut, true);
		return writer;

	}

	public  int dcc_msg_send(SocketChannel socket, dcc_msg msg)
			throws IOException {
		ByteBuffer result = dcc_msg_encoder(msg);
		socket.write(result);
		return 0;
	}

	public  int dcc_msg_recv(SocketChannel socket, dcc_msg msg)
			throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(HEAD_ID_LEN + IMEI_LEN + 1
				+ DTU_NAME + 1 + 4);
		int len = socket.read(buffer);
		if (len == -1) {
			return -1;
		}

		buffer.flip();
		if (!buffer.hasRemaining()) {
			return 0;
		} else {
			if (len != HEAD_ID_LEN + IMEI_LEN + 1 + DTU_NAME + 1 + 4) {
				return -2;
			}

			// 计算msg body的长度

			int idx = HEAD_ID_LEN + IMEI_LEN + 1 + DTU_NAME + 1;
			//int recvlen = buffer.get(idx + 2) * 256 + buffer.get(idx + 2 + 1);
			byte[] byte_len=new byte[2];
			byte_len[0]=buffer.get(idx + 2);
			byte_len[1]=buffer.get(idx + 2 + 1);
			int recvlen=byteToInt(byte_len);
			// 按msg body的长度取出body
			ByteBuffer buff = ByteBuffer.allocate(recvlen);

			int bodylen = socket.read(buff);
			if (bodylen != recvlen) {
				return -2;
			}
			buff.flip();

			byte message[] = new byte[40 + recvlen];

			System.arraycopy(buffer.array(), 0, message, 0,
					buffer.array().length);
			System.arraycopy(buff.array(), 0, message, 40, buff.array().length);
			// buffer.clear();
			buffer.clear();
			 System.out.println("Reveice msg："+new String(message));
			dcc_msg_decoder2(message, msg);
		}

		return len;
	}
	
	private static int byteToInt(byte[] bytes) {
		// TODO Auto-generated method stub
		int num = bytes[1] & 0xFF;
		num |= ((bytes[0] << 8) & 0xFF00);
		return num;
	}

	public static int dcc_close(SocketChannel sock) throws IOException {
		sock.close();
		return 0;
	}

	public static ByteBuffer dcc_msg_encoder(dcc_msg msg) throws IOException {
		// 包头
		ByteBuffer byteBuf = ByteBuffer.allocate(msg.getMsg_len() + 40);
		byteBuf.put(g_hdr_id);

		// 16位imei
		byteBuf.put(msg.getImei().getBytes(Dcc_client.ENCODING));
		byte empty = 0x00;
		byteBuf.put(empty);

		// 16位name
		byte[] names = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		byteBuf.put(names);

		// 一位type
		byteBuf.put(msg.getMsg_type());

		// 一位保留位
		byteBuf.put(msg.getReserved());

		// 2位msg len
		byte hDate = (byte) (msg.getMsg_len() / 256);
		byte lDate = (byte) (msg.getMsg_len() % 256);
		byteBuf.put(hDate);
		byteBuf.put(lDate);

		// msg body
		if (byteBuf.hasRemaining()) {
			byteBuf.put(msg.getMsg_body());
		}
		byteBuf.flip();
		return byteBuf;
	}


	//接收解析



	public static void dcc_msg_decoder(byte ptr[], dcc_msg msg)
			throws IOException {
		int index = g_hdr_id.length;
		// imei
		byte[] Imeichar = new byte[IMEI_LEN];
		System.arraycopy(ptr, index, Imeichar, 0, IMEI_LEN);
		msg.setImei(new String(Imeichar));
		index += IMEI_LEN + 1;

		// name
		byte[] Namechar = new byte[DTU_NAME];
		System.arraycopy(ptr, index, Namechar, 0, DTU_NAME);
		msg.setName(new String(Namechar));
		index += DTU_NAME + 1;

		// msg type
		msg.setMsg_type(ptr[index]);
		index += 1;

		// reserved
		msg.setReserved(ptr[index]);
		index += 1;

		// length
		//int len = ptr[index] * 256 + ptr[index + 1];
		int h = 0xff & ptr[index];
		int l = 0xff & ptr[index +1];
		int len = (h <<8) | l;
		
		msg.setMsg_len((short) len);
		index += 2;

		// msg body
		byte[] Bodychar = new byte[len];
		System.arraycopy(ptr, index, Bodychar, 0, msg.getMsg_len());
		msg.setMsg_body(Bodychar);
		index += msg.getMsg_len();
	}

	public  int dcc_msg_send_auth(SocketChannel socket, String username,
			String password) throws InterruptedException, IOException,
			NoSuchAlgorithmException {
		int i, ret;
		dcc_msg msg = new dcc_msg();
		byte[] pass = new byte[AUTH_RANDOM_LEN + password.length()];
		// 从mServer接收认证许可
		for (i = 0; i < 100; i++) {
			ret = dcc_msg_recv(socket, msg);
			if (ret < 0)
				return -1;
			else if (ret == 0)
				Thread.sleep(100);
			else if (msg.getMsg_type() != DC_MSG_NEED_AUTH)
				continue;
			else
				break;
		}
		if (i == MAX_AUTH_WAIT)
			return -1;
		else if (msg.getMsg_type() != DC_MSG_NEED_AUTH
				|| msg.getMsg_len() < AUTH_RANDOM_LEN)
			return -1;
		// 连接随机数和密码
		System.arraycopy(msg.getMsg_body(), 0, pass, 0, AUTH_RANDOM_LEN);
		System.arraycopy(password.getBytes(), 0, pass, AUTH_RANDOM_LEN,
				password.length());
		// 连接用户名和密码的MD5校验
		byte[] bodybuf = new byte[32];
		int len = username.length() > (USER_NAME_LEN - 1) ? (USER_NAME_LEN - 1)
				: username.length();
		System.arraycopy(username.getBytes(), 0, bodybuf, 0, len);
		// 获取MD5校验
		java.security.MessageDigest md = java.security.MessageDigest
				.getInstance("MD5");
		md.update(pass);
		byte pass_MD5[] = md.digest();

		System.arraycopy(pass_MD5, 0, bodybuf, USER_NAME_LEN, pass_MD5.length);

		msg.setMsg_type(DC_MSG_AUTH);
		msg.setMsg_len(USER_NAME_LEN + PASSWD_MD5_LEN);
		msg.setMsg_body(bodybuf);
		dcc_msg_send(socket, msg);

		for (i = 0; i < 100; i++) {
			ret = dcc_msg_recv(socket, msg);
			if (ret < 0)
				return -1;
			else if (ret == 0)
				Thread.sleep(100);
			else if (msg.getMsg_type() != DC_MSG_AUTH_RESULT)
				continue;
			else
				break;
		}

		if (i == MAX_AUTH_WAIT)
			return -1;
		if (msg.getMsg_type() == DC_MSG_AUTH_RESULT
				&& msg.getMsg_body()[0] == AUTHRESULT_PASSED)
			return 0;
		else
			return -1;

	}


	public     void dcc_msg_decoder2(byte ptr[], dcc_msg msg)
			throws IOException {
		int index = g_hdr_id.length;
		// imei
		byte[] Imeichar = new byte[IMEI_LEN];
		System.arraycopy(ptr, index, Imeichar, 0, IMEI_LEN);
           String imei  =new String(Imeichar);
		 List<EquipdocVO> eqEquipdoc =  iEqEquipdocService.selectEquipdoc(imei);
		 if(eqEquipdoc ==null||eqEquipdoc.size()==0){
		 	throw  new IOException("没有本设备");
		 }
		msg.setImei(new String(Imeichar));

		index += IMEI_LEN + 1;

		// name
		byte[] Namechar = new byte[DTU_NAME];
		System.arraycopy(ptr, index, Namechar, 0, DTU_NAME);
		msg.setName(new String(Namechar));
		index += DTU_NAME + 1;

		// msg type
		msg.setMsg_type(ptr[index]);
		index += 1;

		// reserved
		msg.setReserved(ptr[index]);
		index += 1;

		// length
		//int len = ptr[index] * 256 + ptr[index + 1];
		int h = 0xff & ptr[index];
		int l = 0xff & ptr[index + 1];
		int len = (h << 8) | l;

		msg.setMsg_len((short) len);
		index += 2;

		// msg body
		byte[] Bodychar = new byte[len];
		System.arraycopy(ptr, index, Bodychar, 0, msg.getMsg_len());


		if (msg.getMsg_type() == (byte) 0) {
			String msgBody = bytesToHex(Bodychar);
               EquipdocVO  vo = new EquipdocVO();
			if (msgBody.length() == 12) {
				for(EquipdocVO equipdoc :eqEquipdoc){
                          if(equipdoc.getTypeName().equals("继电器")){
                          	vo =equipdoc;
						  }else{
							  new IOException("没有本设备类型");
						  }
				}
				//读取继电器状态
				String flag = msgBody.substring(6, 8);
				//8位电路的数值
				String result = ByteUtils.hexString2binaryString(flag);
				//解析中间的2位数值转换成2进制不足的之前补0
				EqReceive eqReveive  = new EqReceive();
				eqReveive.setState(0);
				//PANDUAN
				eqReveive.setPkEquipdoc(vo.getPkEquipdoc());
				eqReveive.setBodyValue(msgBody);
				eqReveive.setReceiveValue(result);
				eqReveive.setPort8(result.substring(0,1));
				eqReveive.setPort7(result.substring(1,2));
				eqReveive.setPort6(result.substring(2,3));
				eqReveive.setPort5(result.substring(3,4));
				eqReveive.setPort4(result.substring(4,5));
				eqReveive.setPort3(result.substring(5,6));
				eqReveive.setPort2(result.substring(6,7));
				eqReveive.setPort1(result.substring(7,8));

				iEqReceiveService.save(eqReveive);
				//这个时候获取继电器的装药
				System.out.println("flag---" + flag);
				//convert(flag, map, tag);

				//latch.countDown();
			} else if (msgBody.length() == 42) {
				//读取温湿度
				//	convertTemp(msgBody, box, map, tag);
				//截取14-18位置的是温度
				//截取18-22位是湿度
				for(EquipdocVO equipdoc :eqEquipdoc){
					if(equipdoc.getTypeName().equals("风扇")){
						vo =equipdoc;
					}else{
						new IOException("没有本设备类型");
					}
				}
				String temp = msgBody.substring(14,18);//3*2 + 2*4
				String hum = msgBody.substring(18,22); // 3*2 + 3*4
				int i = Integer.valueOf(temp, 10);
				int j =Integer.valueOf(hum, 10);
				double a = (i-4000)/200d-20+vo.getRandom();
				double  b =(j-4000)/160d+vo.getRandom();
				BigDecimal ta = new BigDecimal(a);
				a = ta.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal tb = new BigDecimal(b);
				b = tb.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				EqReceive eqReveive  = new EqReceive();
				eqReveive.setState(2);
				//PANDUAN
				eqReveive.setPkEquipdoc(vo.getPkEquipdoc());
				eqReveive.setBodyValue(msgBody);
				eqReveive.setReceiveValue(temp+"&"+hum);
				eqReveive.setTemp(a+"%");
                eqReveive.setHum(b+"%");
				iEqReceiveService.save(eqReveive);
				//latch.countDown();
			}else if(msgBody.length()==18){
               //变频器
				//第一路的数值   100是4095
              String road1 =   msgBody.substring(6,10);
              String road2 =msgBody.substring(10,14);
				int i = Integer.valueOf(road1, 10);
				int j =Integer.valueOf(road2, 10);
				EqReceive eqReveive  = new EqReceive();
				eqReveive.setState(1);
				//PANDUAN
				eqReveive.setPkEquipdoc(vo.getPkEquipdoc());
				eqReveive.setBodyValue(msgBody);
				eqReveive.setReceiveValue(road1+"&"+road2);
				eqReveive.setRoad1(i/4095*100+"%");
				eqReveive.setRoad2(j/4095*100+"%");
				iEqReceiveService.save(eqReveive);
			}
		}
		//logger.error("tag: " + tag + ", msg_body: " + bytesToHex(Bodychar));

		msg.setMsg_body(Bodychar);
		index += msg.getMsg_len();
		System.out.print("22222msg="+ ByteUtils.getHexString(msg.getMsg_body()));

	}


	public static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() < 2) {
				sb.append(0);
			}
			sb.append(hex);
		}
		return sb.toString();
	}

}
