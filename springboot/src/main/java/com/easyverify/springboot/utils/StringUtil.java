package com.easyverify.springboot.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class StringUtil {

    private static String str = "qwertyuiopasdfghjklzxcvbnm0987654321";

    /**
     * 获取一个UUID
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString();
    }


    /**
     * 判断一个字符串是否为非空
     * @param str
     * @return
     */
    public static boolean isNotNul(String str){
        return str != null && !"".equals(str);
    }

    public static String generateRandomString(int length) {
        // 定义字符集，包括大写字母和数字
        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new SecureRandom();

        // 循环生成随机字符并添加到StringBuilder中
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charSet.length());
            randomString.append(charSet.charAt(randomIndex));
        }

        return randomString.toString().toUpperCase(Locale.ROOT);
    }
    public static String shuffleString(String input) {
        // 将输入字符串转换为字符数组
        char[] characters = input.toCharArray();

        // 创建一个随机数生成器
        Random random = new Random();

        // 使用Fisher-Yates洗牌算法打乱字符数组
        for (int i = characters.length - 1; i > 0; i--) {
            // 随机选择一个索引与当前索引交换
            int index = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[index];
            characters[index] = temp;
        }

        // 将打乱后的字符数组转换回字符串
        return new String(characters);
    }

    /**
     * 随机生成指定长度的字符串
     * @param len
     * @return
     */
    public static String randomStr(int len){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * str.length());
            stringBuilder.append(str.charAt(index));
        }
        return stringBuilder.toString();
    }

    /**
     * 获取当前时间的字符串类型
     * @return
     */
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
