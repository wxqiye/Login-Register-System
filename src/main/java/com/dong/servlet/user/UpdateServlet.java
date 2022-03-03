/*
* 接收用户更新个人信息的请求。
* 修改数据库，重设session，并回到个人信息页面。
* */
package com.dong.servlet.user;

import com.dong.pojo.User;
import com.dong.service.user.UserService;
import com.dong.service.user.UserServiceImpl;
import com.dong.utl.Constants;
import com.mysql.cj.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从session里拿到参数
        String userCode = (String) req.getSession().getAttribute(Constants.USER_CODE);
        String userName = req.getParameter("userName");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");

        //用接口引用实现类，调用service层的update方法，修改个人信息。
        UserService userService = new UserServiceImpl();
        int rs=0;
        rs = userService.update(userCode,userName,birthday,phone);

        //从数据库取得信息，再在session里重设，使前端页面可取得。
        String userPassword = "0";
        User user = userService.login(userCode,userPassword);
        req.getSession().setAttribute(Constants.USER_NAME,user.getUserName());
        req.getSession().setAttribute(Constants.USER_BIRTHDAY,user.getBirthday());
        req.getSession().setAttribute(Constants.USER_PHONE,user.getPhone());

        //重定向回个人信息页面
        resp.sendRedirect("information.jsp");
    }
}
