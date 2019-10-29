package com.utils;

public class RandomUtil {
    /**
     * 根据需要生成指定长度的纯数字随机数,这个随机数的每一位都是从(0-9)这个产生的一位
     * 
     * @param date
     * @return 随机字符串
     */
    public static String getNewRandomCode(int codeLen) {
        java.util.Random randomCode = new java.util.Random();
        String strCode = "";
        while (codeLen > 0) {
            int charCode = randomCode.nextInt(9);
            System.out.println(charCode);
            strCode += charCode;
            codeLen--;
        }
        return strCode;
    }
}