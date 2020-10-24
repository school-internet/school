package com.school.internet.corn.task;

import java.util.Random;
import java.util.concurrent.ScheduledFuture;

public class ScheduledTask {
    public volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }

    public static void main(String[] args) {
       for(int  i=0;i<100;i++) {


//int i= new java.util.Random().nextInt(900)+100;也可以
       }
    }
}
