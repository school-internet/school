package com.school.internet.corn.task;

import com.school.internet.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Map;
import java.util.Random;

public class SchedulingRunnable implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);

    private String beanName;

    private String methodName;

    private Object[] params;



    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, Object...params ) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }



    @Override
    public synchronized void run() {
      //  logger.info("定时任务开始执行 - bean：{}，方法：{}，参数：{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();

        try {
            Object target = SpringContextUtils.getBean(beanName);

            Method method = null;
            if (null != params && params.length > 0) {
                Class<?>[] paramCls = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    paramCls[i] = params[i].getClass();
                }
                method = target.getClass().getDeclaredMethod(methodName, paramCls);
            } else {
                method = target.getClass().getDeclaredMethod(methodName);
            }

            ReflectionUtils.makeAccessible(method);
            if (null != params && params.length > 0) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
            int max = 5000;
            int min = 1000;
            Random random = new Random();
            int s = random.nextInt(max) % (max - min + 1) + min;
            Thread.sleep(s);

        } catch (Exception ex) {
            logger.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", beanName, methodName, params), ex);
        }

        long times = System.currentTimeMillis() - startTime;
        logger.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", beanName, methodName, params, times);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (params == null) {
            return beanName.equals(that.beanName) &&
                    methodName.equals(that.methodName) &&
                    that.params == null;
        }

        return beanName.equals(that.beanName) &&
                methodName.equals(that.methodName) &&
                Arrays.equals(params,that.params);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }

        return Objects.hash(beanName, methodName, params);
    }
}
