package com.school.internet.corn.config;

import com.school.internet.corn.task.SchedulingRunnable;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.service.IEqRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private IEqRuleService iEqRuleService;

    @Override
    public void run(String... args) throws Exception {

          System.out.print("haha");
          List<EqRule> eqRuleList = iEqRuleService.list();

          for (EqRule eqRule : eqRuleList) {
              SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams", eqRule.getImei(), eqRule.getInstructvalue());
              cronTaskRegistrar.addCronTask(task, eqRule.getRuleValue());
//        SchedulingRunnable task1 = new SchedulingRunnable("demoTask", "taskWithParams", "test",2);
//        cronTaskRegistrar.addCronTask(task1, "0/15 * * * * ?");
//        SchedulingRunnable task2 = new SchedulingRunnable("demoTask", "taskWithParams", "test1",1);
//        cronTaskRegistrar.addCronTask(task2, "0/8 * * * * ?");

          }

    }

}
