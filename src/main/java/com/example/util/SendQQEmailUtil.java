package com.example.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendQQEmailUtil
{
    private String mailAddress = null;
    private String pass = null;//验证码

    public SendQQEmailUtil()
    {

    }

    public void send() throws MessagingException
    {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("2624250238@qq.com"));
        // 设置收件人邮箱地址
//         message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("2254789150@qq.com"),new InternetAddress("494989746@qq.com"),new InternetAddress("974481066@qq.com")});
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailAddress));//一个收件人
        // 设置邮件标题
        message.setSubject("来自安不浪的隐秘洞窟");
        // 设置邮件内容
        message.setText("接受我的邀约,来一起狂欢吧⚜\n邀请码:" + pass);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("2624250238@qq.com", "bknqzagvutblebee");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public String getMailAddress()
    {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress)
    {
        this.mailAddress = mailAddress;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }
}
