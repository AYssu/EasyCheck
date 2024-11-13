package com.easyverify.springboot.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface SendMailService {
    /*
     * 发送文本邮件
     * @param to 接收者
     * @param title 邮件主题
     * @param message
     */
    void sendTextMail(String to, String title, String message) throws MessagingException, UnsupportedEncodingException;

    /*
     * 发送html邮件
     * @param to 接收者
     * @param title 邮件主题
     * @param message
     */
    void sendHtmlMail(String to, String title, String message) throws MessagingException, UnsupportedEncodingException;

    /*
     * 发送验证码邮件
     * @param mail 接收者
     * @param random 验证码
     */
    void sendCodeMail(String mail, Integer random) throws MessagingException, UnsupportedEncodingException;
}
