package com.easyverify.springboot.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
@Slf4j
public class Sutils {
    private static final int BASE = 3;
    private static final int EXPAND = 4;
    private static final int SIGN = -128;
    private static final char END = '=';

    public static String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static void set_base64(String str) {
        if(str.isEmpty())
            Sutils.str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        else
            Sutils.str = str;
    }

    public static String base64_encode(String str){
        return new String(encodeBase64(str));
    }
    public static String base64_encode(String str,String base64){
        set_base64(base64);
        return new String(encodeBase64(str));
    }
    public static byte[] encodeBase64(String str){
        return encodeBase64(str.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] encodeBase64(byte[] data){
        int len = data.length;
        int overBytes = len%BASE;
        int lenBytes = len/BASE;

        int encodeBytesLen;
        if(overBytes == 0){
            encodeBytesLen = lenBytes*EXPAND;
        }else{
            encodeBytesLen = (lenBytes+1)*EXPAND;
        }

        byte[] encodeBytes = new byte[encodeBytesLen];
        int step;
        int encodeIndex = 0;
        byte b1,b2,b3,m0,m1;
        for(int i=0;i<lenBytes;i++){
            step = i*3;
            //取出三个字节
            b1 = data[step];
            b2 = data[step+1];
            b3 = data[step+2];
            //获取第一个字节的最后两位
            m0 = (byte) (b1&0x03);
            //获取第二个字节的最后四位
            m1 = (byte) (b2&0x0f);
            // 字节&-128，非负数都为0，负数的话把高位11转换成00
            byte b1Index = (byte)((b1&SIGN)==0?b1>>2:b1>>2^0xc0);
            byte b2Index = (byte)((b2&SIGN)==0?b2>>4:b2>>4^0xf0);
            byte b3Index = (byte)((b3&SIGN)==0?b3>>6:b3>>6^0xfc);
            //根据字节的数值当做下标，寻找对应的字符

            try{
                encodeBytes[encodeIndex]= (byte) str.charAt(b1Index);
                encodeBytes[encodeIndex+1]= (byte) str.charAt(b2Index|(m0<<4));
                encodeBytes[encodeIndex+2]= (byte) str.charAt(b3Index|(m1<<2));
                encodeBytes[encodeIndex+3]= (byte) str.charAt(b3&0x3f);

                encodeIndex+=4;
            }catch (IndexOutOfBoundsException ignored){

            }
        }
        //当剩下一个字节，或者两个字节时，需要做的处理
        if(overBytes == 1){
            b1 = data[len-1];
            byte b1Index = (byte)((b1&SIGN)==0?b1>>2:b1>>2^0xc0);
            m0 = (byte) (b1&0x03);
            encodeBytes[lenBytes*EXPAND]= (byte) str.charAt(b1Index);
            encodeBytes[lenBytes*EXPAND+1]= (byte) str.charAt((m0<<4));
            encodeBytes[lenBytes*EXPAND+2]= END;
            encodeBytes[lenBytes*EXPAND+3]= END;
        }else if(overBytes == 2){
            b1 = data[len-2];
            b2 = data[len-1];
            byte b1Index = (byte)((b1&SIGN)==0?b1>>2:b1>>2^0xc0);
            byte b2Index = (byte)((b2&SIGN)==0?b2>>4:b2>>4^0xf0);
            m0 = (byte) (b1&0x03);
            m1 = (byte) (b2&0x0f);

            encodeBytes[lenBytes*EXPAND]= (byte) str.charAt(b1Index);
            encodeBytes[lenBytes*EXPAND+1]= (byte) str.charAt(b2Index|(m0<<4));
            encodeBytes[lenBytes*EXPAND+2]= (byte) str.charAt((m1<<2));
            encodeBytes[lenBytes*EXPAND+3]= END;
        }
        return encodeBytes;
    }

    public static String base64_decode(String str){
        return new String(decodeBase64(str));
    }
    public static String base64_decode(String str,String base64){
        set_base64(base64);
        return new String(decodeBase64(str));
    }

    public static byte[] decodeBase64(String str){
        return decodeBase64(str.getBytes());
    }


    public static byte[] decodeBase64(byte[] data){
        if(data.length == 0){
            return new byte[0];
        }

        int missingPadding = 4 - (data.length % 4);
        if (missingPadding != 4) {
            byte[] newData = new byte[data.length + missingPadding];
            System.arraycopy(data, 0, newData, 0, data.length);
            for (int i = data.length; i < newData.length; i++) {
                newData[i] = END; // PAD是'='的字符
            }
            data = newData;
        }
        int len = data.length/EXPAND;
        byte[] decodeBytes = new byte[len*BASE];
        int byteLen = 0;
        int decodeIndex;
        byte b1,b2,b3,b4;
        for(int i=0;i<len;i++){
            decodeIndex = i*4;
            b1 = data[decodeIndex];
            b2 = data[decodeIndex+1];
            b3 = data[decodeIndex+2];
            b4 = data[decodeIndex+3];
            if(b3!=END&&b4!=END){
                //无补充符号
                b1 = (byte) str.indexOf(b1);
                b2 = (byte) str.indexOf(b2);
                b3 = (byte) str.indexOf(b3);
                b4 = (byte) str.indexOf(b4);
                decodeBytes[byteLen++] = (byte) (b1<<2|b2>>4);
                decodeBytes[byteLen++] = (byte) (((b2&0xf)<<4)|((b3>>2)&0xf));
                decodeBytes[byteLen++] = (byte) (b3<<6|b4);
            }else if(b3==END){
                b1 = (byte) str.indexOf(b1);
                b2 = (byte) str.indexOf(b2);
                decodeBytes[byteLen++] = (byte) (b1<<2|b2>>4);
            }else {
                b1 = (byte) str.indexOf(b1);
                b2 = (byte) str.indexOf(b2);
                b3 = (byte) str.indexOf(b3);
                decodeBytes[byteLen++] = (byte) (b1<<2|b2>>4);
                decodeBytes[byteLen++] = (byte) (((b2&0xf)<<4)|((b3>>2)&0xf));
            }
        }
        byte[] retBytes = new byte[byteLen];
        System.arraycopy(decodeBytes, 0, retBytes, 0, byteLen);
        return retBytes;
    }


    public static String to_hex(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder hexString = new StringBuilder();
        // 使用UTF-8编码将字符串转换为字节序列
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        // 遍历字节序列，将每个字节转换为两位的十六进制字符串
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            hexString.append(hex.length() == 1 ? "0" + hex : hex);
        }
        return hexString.toString().toUpperCase();
    }

    public static String hex_to_string(String hex) {
        int len = hex.length();

        if (len % 2 != 0) {
            throw new IllegalArgumentException("数据异常");
        }
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 获取两个字符的子字符串
            String byteString = hex.substring(i, i + 2);
            try {
                // 尝试将子字符串转换为16进制数
                int byteValue = Integer.parseInt(byteString, 16);
                bytes[i / 2] = (byte) byteValue;
            } catch (Exception e) {
                log.info("hex: {}",hex);
                // 如果转换失败，抛出自定义异常或处理错误
                e.printStackTrace();
            }
        }
        // 使用UTF-8编码将字节数组转换为字符串
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
