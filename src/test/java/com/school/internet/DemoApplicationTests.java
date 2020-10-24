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
import java.util.Map;
import java.util.HashMap;
import com.school.internet.corn.task.DemoTask;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    private DemoTask demoTask;

    @Test
    public void testrule(){
        demoTask.taskWithParams("1316560309108604929");
    }

    @Test
    public void testTask() throws InterruptedException {
//        SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskNoParams", null);
//        cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");

        SchedulingRunnable task1 = new SchedulingRunnable("demoTask", "taskWithParams", "aaa","111");
        cronTaskRegistrar.addCronTask(task1, "0/15 * * * * ?");
        Thread.sleep(10000);

        SchedulingRunnable task2 = new SchedulingRunnable("demoTask", "taskWithParams", "aaa","111");
        cronTaskRegistrar.addCronTask(task2, "0/8 * * * * ?");
        // 便于观察

        Thread.sleep(3000000);
    }

}
