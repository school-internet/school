package com.school.internet.user.common;

import java.security.MessageDigest;

/**
 * MD5加密
 * Created by gb.wang on 2018/4/24.
 */
public class MD5Util {

    public String md5Encryption(String source) {

        if(null == source || "".equals(source)) {
            return "";
        }
        //用于加密的字符
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'S', 'U', 'W', 'U', 'B', 'X' };
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = source.getBytes("utf-8");
            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);
            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }
            //返回经过加密后的字符串
            return new String(str);
        } catch (Exception e) {
            return "SUWUSOFTPASSWORD_ERROR";
        }
    }


    public static MD5Util getInstance() {
        return MD5UtilFactory.instance;
    }

    public static class MD5UtilFactory {
        public final static MD5Util instance = new MD5Util();
    }
    private MD5Util() {
    }

}
