package com.school.internet.utils;

import com.school.internet.corn.config.ByteUtils;
import com.school.internet.corn.config.Dcc_client;
import com.school.internet.corn.config.dcc_msg;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.service.IEqEquipdocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.SocketChannel;
@Component
public   class MsgUtil {

           @Autowired
           private IEqEquipdocService iEqEquipdocService;

    public  Integer  sendMsg(String imei,String value)  {
        try {
            Dcc_client dcc_client = new Dcc_client();
            SocketChannel socket = dcc_client.dcc_Socket("iot.harvestcloud.cn", 9877);

            //数据包格式看mserver相关手册
            //发送广播\
            System.out.print("住="+iEqEquipdocService.list());
            dcc_msg msg = new dcc_msg();
            msg.setMsg_type((byte) 0x00);
            msg.setMsg_len(8);
            msg.setMsg_body(ByteUtils.getByteArray(value));
            msg.setImei(imei);
            return   dcc_client.dcc_msg_send2(socket,msg);
        } catch (IOException e) {

            e.printStackTrace();
            return  1;
        }
    }
}
