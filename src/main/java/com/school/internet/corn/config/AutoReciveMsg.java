
package com.school.internet.corn.config;


import com.school.internet.corn.task.RevciveMsgRunnable;
import com.school.internet.corn.task.SchedulingRunnable;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.service.IEqRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import org.springframework.boot.CommandLineRunner;

import javax.annotation.PostConstruct;
import java.nio.channels.SocketChannel;
import java.util.List;


@Component
public class AutoReciveMsg   implements CommandLineRunner {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private IEqRuleService iEqRuleService;
    @Override
    public  void run(String... args) throws Exception {
        List<EqRule> eqRuleList = iEqRuleService.list();

        for (EqRule eqRule : eqRuleList) {
            SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams",eqRule.getPkRule() );
            cronTaskRegistrar.addCronTask(task, eqRule.getRuleValue());
        }
          new RevciveMsgRunnable().run();

    }





}

