/*
 * service层，主要处理业务逻辑，并调用dao层。
 * 具体实现了用户的查询，注册，个人信息修改，头像路径保存。
 * 注意：为了login方法的复用，login只实现了查询用户的功能，取用户的功能。
 *      密码正确与否的功能我放在了LoginServlet层进行实现
 * */
package com.dong.service.user;

import com.dong.dao.BaseDao;
import com.dong.dao.user.UserDao;
import com.dong.dao.user.UserDaoImpl;
import com.dong.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public  class UserServiceImpl implements UserService {

    //接口引用实现类。
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }


    //用户查询
    @Override
    public User login(String userCode, String password){


        Connection connection =null;
        User user = null;
        try {
            //建立数据库连接。
            connection = BaseDao.getConnection();
            //调用dao层方法，获得登录对象user
            user = userDao.getLoginUser(connection, userCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //释放资源，后两个因为是在dao层里创建开启的，所以在dao层释放。
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }



    //用户注册
    public int register(String userCode, String password,String email){


        Connection connection =null;
        int rs=0;
        try {
            //建立数据库连接。
            connection = BaseDao.getConnection();

            //调用dao层方法，实现注册。
            rs = userDao.getRegisterUser(connection,userCode,password,email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //释放资源，后两个因为是在dao层里创建开启的，所以在dao层释放。
            BaseDao.closeResource(connection,null,null);
        }
        return rs;
    }
    //用户个人信息修改
    public int update(String userCode,String userName, String birthday,String phone){

        Connection connection =null;
        int rs=0;
        try {
            //建立数据库连接。
            connection = BaseDao.getConnection();

            //调用dao层方法，实现个人信息修改
            rs = userDao.getUpdateUser(connection,userCode,userName,birthday,phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //释放资源，后两个因为是在dao层里创建开启的，所以在dao层释放。
            BaseDao.closeResource(connection,null,null);
        }
        return rs;
    }


    //用户头像上传
    public int upload(String userCode,String path){

        Connection connection =null;
        int rs=0;
        try {
            //建立数据库连接。
            connection = BaseDao.getConnection();

            //调用dao层方法，实现头像路径信息的存储。
            rs = userDao.getUploadUser(connection,userCode,path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //释放资源，后两个因为是在dao层里创建开启的，所以在dao层释放。
            BaseDao.closeResource(connection,null,null);
        }
        return rs;


    }

    //测代码时用过的，不想删掉。
    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin","1");
        System.out.println(admin.getUserPassword());
    }
}
