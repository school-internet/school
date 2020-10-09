package com.school.internet.utils;


import static com.school.internet.utils.StRandom.*;
public class RandomPwd {
  /*  public static void main(String[] args){
              //   int len = Integer.parseInt(args[8]);;
                 System.out.println(randomStr(8));
            }
*/
    /**
     13      * 返回随机字符串，同时包含数字、大小写字母
     14      * @param len 字符串长度，不能小于3
     15      * @return String 随机字符串
     16      */
    public static String randomStr(int len){
        if(len < 6){
            throw new IllegalArgumentException("字符串长度不能小于6");
        }
        //数组，用于存放随机字符
        char[] chArr = new char[len];
        //为了保证必须包含数字、大小写字母
        chArr[0] = (char)('0' + uniform(0,10));
        chArr[1] = (char)('A' + uniform(0,26));
        chArr[2] = (char)('a' + uniform(0,26));


        char[] codes = { '0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T',
                'U','V','W','X','Y','Z','a','b','c','d',
                'e','f','g','h','i','j','k','l','m','n',
                'o','p','q','r','s','t','u','v','w','x',
                'y','z'};
        //charArr[3..len-1]随机生成codes中的字符
        for(int i = 3; i < len; i++){
            chArr[i] = codes[uniform(0,codes.length)];
        }

        //将数组chArr随机排序
        for(int i = 0; i < len; i++){
            int r = i + uniform(len - i);
            char temp = chArr[i];
            chArr[i] = chArr[r];
            chArr[r] = temp;
        }

        return new String(chArr);
    }
}
