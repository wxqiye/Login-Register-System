/*
* 接收用户的登录请求，调用login方法查询是否存在此人
* 查有此人且密码正确，登陆成功，存到session，重定向
* 查无此人或密码错误，登录失败，回登录页面
* */
package com.dong.servlet.user;

import com.dong.pojo.User;
import com.dong.service.user.UserService;
import com.dong.service.user.UserServiceImpl;
import com.dong.utl.Constants;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从req中取得参数
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //调用login方法，查询用户是否存在。
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode,userPassword);

        //判断是否成功查询到，密码是否正确
        if(user!=null&&userPassword.equals(user.getUserPassword())){
            /*
            * 查有此人，且密码正确，
            * 则登录成功
            * 存到session中，为前端展示数据
            * 且重定向至个人信息界面。
            * */
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            req.getSession().setAttribute(Constants.USER_ID,user.getId());
            req.getSession().setAttribute(Constants.USER_CODE,user.getUserCode());
            req.getSession().setAttribute(Constants.USER_NAME,user.getUserName());
            req.getSession().setAttribute(Constants.USER_PASSWORD,user.getUserPassword());
            req.getSession().setAttribute(Constants.USER_GENDER,user.getGender());
            req.getSession().setAttribute(Constants.USER_BIRTHDAY,user.getBirthday());
            req.getSession().setAttribute(Constants.USER_PHONE,user.getPhone());
            req.getSession().setAttribute(Constants.USER_PATH,user.getPath());
            req.getSession().setAttribute(Constants.USER_EMAIL,user.getEmail());
            resp.sendRedirect("information.jsp");
        }
        else {
            /*
            * 查无此人，或者密码错误
            * 回到登录页面
            * */
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
