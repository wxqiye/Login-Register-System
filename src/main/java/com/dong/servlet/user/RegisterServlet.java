/*
* 接收用户的注册请求，
* 调用register方法进行注册，并调用mail类进行邮件的发送
* 由于时间关系，没有来得及使用多线程实现邮件发送。
* */
package com.dong.servlet.user;

import com.dong.pojo.User;
import com.dong.service.user.UserService;
import com.dong.service.user.UserServiceImpl;
import com.dong.utl.Mail;
import com.dong.utl.MailImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从req中取得参数
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        String userEmail = req.getParameter("email");


        //调用login方法查询,并取得user对象
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode,userPassword);

        //如果已存在此用户名，则提示注册失败。
        if(user!=null){
            req.setAttribute("tips","注册失败，已有此人");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
        //不存在，则调用register方法与mail类，进行注册。
        else {
            //调用register方法，实行注册。
           int rs=0;
           rs = userService.register(userCode,userPassword,userEmail);

           //调用mail类，进行邮件的发送。
           Mail mail = new MailImpl();
            try {
                mail.sendmail(userEmail);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //注册成功，回到登录页面
            resp.sendRedirect("login.jsp");
        }
    }
}
