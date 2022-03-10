/*
* 接口实现类
* 实现了邮件发送
* */
package com.dong.utl;
import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailImpl implements Mail {
    @Override
    public void sendmail(String accmail) throws Exception{

        //设置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");  //设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码

        //设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        //创建session
        Session session = Session.getDefaultInstance(prop, new Authenticator() {//获取SMTP服务器的连接对象
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("2984861018@qq.com", "lwqjeisufegadeic");
            }
        });

        //1、开启Session的debug模式，可查看邮件发送状态
        session.setDebug(true);

        //2、通过session得到transport对象
        Transport ts = session.getTransport();//通过这一次和SMTP服务器的连接对象获取发送邮件的传输对象

        //3、使用邮箱的用户名和授权码连上SMTP邮件服务器，即登陆
        ts.connect("smtp.qq.com", "2984861018@qq.com", "lwqjeisufegadeic");

        //4、创建邮件对象MimeMessage——点击网页上的写信
        //创建一个邮件对象
        MimeMessage message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("2984861018@qq.com"));//设置发件人
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(accmail));//设置收件人
        //写标题
        message.setSubject("注册验证");//设置邮件主题
        //写正文
        message.setContent("<h2 style='color:red'>注册成功，如非本人操作，请及时修改密码</h2>", "text/html;charset=UTF-8");//设置邮件的具体内容

        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        //6、关闭连接对象，即关闭服务器上的连接资源
        ts.close();

    }
}
