package com.easyverify.springboot;

import cn.hutool.crypto.digest.DigestUtil;
import com.easyverify.springboot.utils.Sutils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SutilsTests {
    @Test
    public void base64(){
        String str = "abc阿夜6哔";
        Sutils.set_base64(""); // 留空默认
        System.out.println(Sutils.base64_encode(str));
        Sutils.set_base64("RbZ4y1KO0WahcfG8rQ7TBVUotNgmdXH/DMxv5zEY+esP6SFwipl3LuIC9A2nJqjk");
        System.out.println(Sutils.base64_encode(str));
        System.out.println(Sutils.base64_decode("MzEzMDMwMzAzMA=="));
    }

    @Test
    public void hex(){
        String str = "abc阿夜6哔";
        System.out.println(Sutils.to_hex(str));
        System.out.println(Sutils.hex_to_string("4D7A457A4D444D774D7A417A4D413D3D"));
        System.out.println(Sutils.hex_to_string("4D7A457A4D444D774D7A417A4D413D3D"));
        System.out.println(Sutils.hex_to_string("4D7A457A4D444D774D7A417A4D413D3D"));
    }
    @Test
    public void md5(){
        String str = "abc阿夜6哔";
        System.out.println(DigestUtil.md5Hex(str));
    }

    @Test
    public void sha256(){
        String str = "abc阿夜6哔";
        System.out.println(DigestUtil.sha256Hex(str));

    }
}
