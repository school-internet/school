package com.school.internet.corn.task;


import com.school.internet.corn.config.Dcc_client;
import com.school.internet.corn.config.dcc_msg;
import com.school.internet.utils.SpringContextUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.SocketChannel;

@Component
public class RevciveMsgRunnable   extends Thread{



    private   Dcc_client dcc_client;


    @Override
    public void run() {
        dcc_client = SpringContextUtils.getApplicationContext().getBean(Dcc_client.class);
        SocketChannel socket = dcc_client.dcc_Socket("iot.harvestcloud.cn", 9877);
        while (true) {
            try {
                dcc_client.dcc_msg_recv(socket, new dcc_msg());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
