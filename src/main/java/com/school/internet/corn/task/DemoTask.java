package com.school.internet.corn.task;

import com.school.internet.corn.config.Dcc_client;
import com.school.internet.utils.MsgUtil;
import org.springframework.stereotype.Component;

import java.nio.channels.SocketChannel;

/**
 * @program: simple-demo
 * @description:
 * @author: CaoTing
 * @date: 2019/5/23
 **/
@Component("demoTask")
public class DemoTask {

    public void taskWithParams(String param1, String param2) {

        //数据包格式看mserver相关手册
        //发送广播
       MsgUtil.sendMsg(param1,param2);
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }


}
