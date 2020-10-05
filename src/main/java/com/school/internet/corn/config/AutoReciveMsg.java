
package com.school.internet.corn.config;


import com.school.internet.corn.task.RevciveMsgRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import org.springframework.boot.CommandLineRunner;

import javax.annotation.PostConstruct;
import java.nio.channels.SocketChannel;


@Component
public class AutoReciveMsg   implements CommandLineRunner {
    @Override
    public  void run(String... args) throws Exception {
        System.out.print("hello");

          new RevciveMsgRunnable().run();

    }
}

