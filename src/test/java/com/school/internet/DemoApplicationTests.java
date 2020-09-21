package com.school.internet;

import com.school.internet.corn.config.CronTaskRegistrar;
import com.school.internet.corn.task.SchedulingRunnable;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.service.IEqRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private IEqRuleService iEqRuleService;

    @Test
    public void testrule(){
        List<EqRule> ruleList = iEqRuleService.all();
        for(EqRule eqRule : ruleList){
            System.out.println(eqRule.getRuleValue());
        }
    }

    @Test
    public void testTask() throws InterruptedException {
        SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskNoParams", null);
        cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");
        SchedulingRunnable task1 = new SchedulingRunnable("demoTask", "taskWithParams", "test",2);
        cronTaskRegistrar.addCronTask(task1, "0/15 * * * * ?");
        SchedulingRunnable task2 = new SchedulingRunnable("demoTask", "taskWithParams", "test1",1);
        cronTaskRegistrar.addCronTask(task2, "0/8 * * * * ?");
        // 便于观察

        Thread.sleep(3000000);
    }

}
