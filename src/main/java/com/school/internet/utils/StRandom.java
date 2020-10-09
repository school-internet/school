package com.school.internet.utils;

import java.util.Random;

public final class StRandom {

    //随机数生成器
    private static Random random;
    //种子值
    private static long seed;

    //静态代码块，初始化种子值及随机数生成器
    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    //私有构造函数，禁止实例化
    private StRandom() {}

    /**
     18      * 设置种子值
     19      * @param s 随机数生成器的种子值
     20      */
    public static void setSeed(long s){
        seed = s;
        random = new Random(seed);
    }

    /**
     27      * 获取种子值
     28      * @return long 随机数生成器的种子值
     29      */
    public static long getSeed(){
        return seed;
    }

    /**
     35      * 随机返回0到1之间的实数 [0,1)
     36      * @return double 随机数
     37      */
    public static double uniform(){
        return random.nextDouble();
    }

    /**
     43      * 随机返回0到N-1之间的整数 [0,N)
     44      * @param N 上限
     45      * @return int 随机数
     46      */
    public static int uniform(int N){
        return random.nextInt(N);
    }

    /**
     52      * 随机返回0到1之间的实数 [0,1)
     53      * @return double 随机数
     54      */
    public static double random(){
        return uniform();
    }

    /**
     60      * 随机返回a到b-1之间的整数 [a,b)
     61      * @param a 下限
     62      * @param b 上限
     63      * @return int 随机数
     64      */
    public static int uniform(int a,int b){
        return a + uniform(b - a);
    }

    /**
     70      * 随机返回a到b之间的实数
     71      * @param a 下限
     72      * @param b 上限
     73      * @return double 随机数
     74      */
    public static double uniform(double a,double b){
        return a + uniform() * (b - a);
    }
}