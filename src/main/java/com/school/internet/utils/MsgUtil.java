package com.school.internet.utils;

import com.school.internet.corn.config.ByteUtils;
import com.school.internet.corn.config.dcc_client;
import com.school.internet.corn.config.dcc_msg;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public  final class MsgUtil {


    public static Integer  sendMsg(String imei,String value) throws IOException {
        SocketChannel socket = dcc_client.dcc_Socket("iot.harvestcloud.cn", 9877);

        //数据包格式看mserver相关手册
        //发送广播
        dcc_msg msg = new dcc_msg();
        msg.setMsg_type((byte) 0x00);
        msg.setMsg_len(8);
        msg.setMsg_body(ByteUtils.getByteArray(value));
        msg.setImei(imei);
        return   dcc_client.dcc_msg_send(socket,msg);
    }
}
