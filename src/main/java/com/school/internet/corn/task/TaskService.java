package com.school.internet.corn.task;


import com.school.internet.corn.config.Dcc_client;
import com.school.internet.corn.config.dcc_msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.channels.SocketChannel;

@Service
public class TaskService {

    @Autowired
    private Dcc_client dcc_client;

    @Async("task")
    public void  receive(){
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
