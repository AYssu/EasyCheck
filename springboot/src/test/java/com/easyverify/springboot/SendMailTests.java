package com.easyverify.springboot;

import com.easyverify.springboot.service.SendMailService;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class SendMailTests {

    @Autowired
    private SendMailService sendMailService;
    @Test
    void sendHtml() throws MessagingException, UnsupportedEncodingException {
        sendMailService.sendHtmlMail("2997036064@qq.com", "网页测试", "你好呀网页");
    }

    @Test
    void sendText() throws MessagingException, UnsupportedEncodingException {
        sendMailService.sendTextMail("2997036064@qq.com", "文本测试", "你好呀文本");
    }

    @Test
    void sendCode() throws MessagingException, UnsupportedEncodingException {
        Integer random = (int)((Math.random()*9+1)*100000);
        sendMailService.sendCodeMail("2997036064@qq.com",random);
    }
}
