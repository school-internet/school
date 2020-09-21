package com.school.internet.corn.config;

import com.school.internet.corn.task.SchedulingRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) throws Exception {
        SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskNoParams", null);
        cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");
        SchedulingRunnable task1 = new SchedulingRunnable("demoTask", "taskWithParams", "test",2);
        cronTaskRegistrar.addCronTask(task1, "0/15 * * * * ?");
        SchedulingRunnable task2 = new SchedulingRunnable("demoTask", "taskWithParams", "test1",1);
        cronTaskRegistrar.addCronTask(task2, "0/8 * * * * ?");

        System.out.println();
    }

}
