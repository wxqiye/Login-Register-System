/*
* 接收头像上传请求，将头像存储路径保存到数据库。
* 由于时间关系，头像未能在前端正确的显示出来，但是我在前端显示了头像的路径
* 用户可以直接通过此路径访问到头像
* */
package com.dong.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dong.service.user.UserService;
import com.dong.service.user.UserServiceImpl;
import com.dong.utl.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.*;
import java.util.List;
import java.util.UUID;
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求转发，由FileServlet具体实现头像文件的上传。
        req.getRequestDispatcher("/file.do").forward(req,resp);

        //取参，从session中取得userCode及头像文件保存路径path。
        int rs =0;
        String userCode = (String) req.getSession().getAttribute(Constants.USER_CODE);
        String path = (String) req.getSession().getAttribute(Constants.USER_PATH);

        //进行数据持久化处理，把头像文件保存路径path存到数据库中。
        UserService userService = new UserServiceImpl();
        rs = userService.upload(userCode,path);
    }
}
