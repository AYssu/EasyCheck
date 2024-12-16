package com.easyverify.springboot.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtil {

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

    public static String generateRandomKey() {
        // 定义英文字母和数字
        String letters = "ZXCVBNMLKJHG";
        String numbers = "1234567890";

        // 将字母和数字合并到一个列表中
        List<Character> chars = new ArrayList<>();
        for (char c : letters.toCharArray()) {
            chars.add(c);
        }
        for (char c : numbers.toCharArray()) {
            chars.add(c);
        }

        // 随机排列列表中的元素
        Collections.shuffle(chars);

        // 将排列后的字符转换为字符串
        StringBuilder password = new StringBuilder();
        for (Character c : chars) {
            password.append(c);
        }

        return password.toString();
    }
}
