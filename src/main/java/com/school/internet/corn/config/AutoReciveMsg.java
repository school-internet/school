
package com.school.internet.corn.config;


import com.school.internet.corn.task.RevciveMsgRunnable;
import com.school.internet.corn.task.SchedulingRunnable;
import com.school.internet.corn.task.TaskService;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.service.IEqRuleService;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;


@Component
public class AutoReciveMsg   implements CommandLineRunner {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private IEqRuleService iEqRuleService;
    @Autowired
    private TaskService taskService;
    @Override
    public  synchronized  void run(String... args) throws Exception {
        List<EqRule> eqRuleList = iEqRuleService.list();

        for (EqRule eqRule : eqRuleList) {

            SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams",eqRule.getPkRule() );
            cronTaskRegistrar.addCronTask(task, eqRule.getRuleValue());
        }

    }





}

