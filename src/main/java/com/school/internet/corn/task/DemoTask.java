package com.school.internet.corn.task;

import com.school.internet.corn.config.Dcc_client;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.service.IEqRuleService;
import com.school.internet.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IEqRuleService iEqRuleService;


    public void taskWithParams(String pkRule) {

        //数据包格式看mserver相关手册
        //发送广播
       EqRule eqRule = iEqRuleService.getById(pkRule);
       MsgUtil.sendMsg(eqRule.getImei(),eqRule.getInstructValue());
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }


}
