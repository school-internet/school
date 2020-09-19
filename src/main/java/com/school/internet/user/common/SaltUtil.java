package com.school.internet.user.common;

import sun.applet.Main;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Salt随机生成工具类
 * Created by gb.wang on 2018/4/24.
 */
public class SaltUtil {

    //构造方法私有化
    private SaltUtil() {

    }



    //生成随机盐
    public String createSalt() {
        Random random = new SecureRandom();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        try {
            String str =new String(salt,"utf-8");
            String mySalt = new BASE64Encoder().encode(salt);
            return mySalt;
        }catch (Exception e) {
            e.printStackTrace();
            return  "suwuSoft";
        }
    }

    //获取对象
    public static SaltUtil getInstance() {
        return SaltUtilFactory.instance;
    }

    private static class SaltUtilFactory {
        private  static  SaltUtil instance = new SaltUtil();
    }
}
