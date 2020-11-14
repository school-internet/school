package com.school.internet.corn.task;

import com.school.internet.corn.config.Dcc_client;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.entity.EqSendlog;
import com.school.internet.equip.service.IEqRuleService;
import com.school.internet.equip.service.IEqSendlogService;
import com.school.internet.utils.DateTimeUtils;
import com.school.internet.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.channels.SocketChannel;
import java.util.Random;

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
    @Autowired
    private IEqSendlogService iEqSendlogService;


    public   void taskWithParams(String pkRule) {

        //数据包格式看mserver相关手册
        //发送广播
        try {
            int max = 5000;
            int min = 2000;
            Random random = new Random();
            int s = random.nextInt(max) % (max - min + 1) + min;
            Thread.sleep(s);
            EqRule eqRule = iEqRuleService.getById(pkRule);
            MsgUtil msgUtil = new MsgUtil();
            int result = msgUtil.sendMsg(eqRule.getImei(),eqRule.getInstructValue());
            EqSendlog eqSendlog  =  new EqSendlog();
            eqSendlog.setSendTime(DateTimeUtils.formatTime());
            eqSendlog.setResultValue("自动发送");
            eqSendlog.setInstructValue(eqRule.getInstructValue());
            eqSendlog.setFkEquipdoc(eqRule.getFkEquipdoc());
            eqSendlog.setState(result);
            eqSendlog.setUserId(100);
            iEqSendlogService.save(eqSendlog);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }


}
