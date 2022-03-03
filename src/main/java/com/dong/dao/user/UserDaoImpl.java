/*
 * dao层，与数据库进行交互。
 * 主要通过调用basedao层的增删改查，来具体实现某一功能。
 * 具体实现了用户的查询，注册，修改个人信息，头像路径保存。
 * */
package com.dong.dao.user;

import com.dong.dao.BaseDao;
import com.dong.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    //得到登录对象
    public User getLoginUser(Connection connection, String userCode) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        //数据库在service层连接的，如果连接成功。
        if (connection != null) {

            //编写sql语句，params是因为要预处理，占位要传的参。
            String sql = "select * from user where usercode=?";
            Object[] params = {userCode};

            //调用basedao方法，查询数据，返回结果集。
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            //必须rs.next()读取,不然会报错。
            while (rs.next()) {
                //将结果集里的值交给user对象。
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setPath(rs.getString("path"));
            }
            //释放资源，因为在service层开启的connetion，所以在service层释放connetion，所以这里为null
            BaseDao.closeResource(null, pstm, rs);

        }



        return user;
    }
    //用户注册
    public int getRegisterUser(Connection connection, String userCode, String userPassword,String userEmail) throws SQLException {


        ResultSet rs = null;
        PreparedStatement pstm = null;
        int rs1 = 0;
        User user = null;

        //数据库在service层连接的，如果连接成功。
        if (connection != null) {

            //编写sql语句，params是因为要预处理，占位要传的参。
            String sql = "insert into user (userCode,userPassword,email) values (?,?,?)";
            Object[] params = {userCode,userPassword,userEmail};

            //调用basedao方法，查询数据，返回结果。
            rs1 = BaseDao.execute(connection, pstm,sql, params);

            //释放资源，因为在service层开启的connetion，所以在service层释放connetion，所以这里为null
            BaseDao.closeResource(null, pstm, rs);

        }

        return rs1;
    }

    //用户个人信息修改
    public int getUpdateUser(Connection connection, String userCode,String userName,String birthday,String phone) throws SQLException{


        ResultSet rs = null;
        PreparedStatement pstm = null;
        int rs1 = 0;
        User user = null;

        //数据库在service层连接的，如果连接成功。
        if (connection != null) {

            //编写sql语句，params是因为要预处理，占位要传的参。
            String sql = "update webdong.user set userName=?,birthday=?,phone=? where userCode=?";
            Object[] params = {userName,birthday,phone,userCode};

            //调用basedao方法，查询数据，返回结果。
            rs1 = BaseDao.execute(connection, pstm,sql, params);

            //释放资源，因为在service层开启的connetion，所以在service层释放connetion，所以这里为null
            BaseDao.closeResource(null, pstm, rs);

        }

        return rs1;

    }

    //用户头像路径地址存放。
    public int getUploadUser(Connection connection, String userCode,String path) throws SQLException{

        //便于传参。
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int rs1 = 0;
        User user = null;

        //数据库在service层连接的，如果连接成功。
        if (connection != null) {

            //编写sql语句，params是因为要预处理，占位要传的参。
            String sql = "update webdong.user set path=? where userCode=?";
            Object[] params = {path,userCode};

            //调用basedao方法，查询数据，返回结果。
            rs1 = BaseDao.execute(connection, pstm,sql, params);

            //释放资源，因为在service层开启的connetion，所以在service层释放connetion，所以这里为null
            BaseDao.closeResource(null, pstm, rs);

        }

        return rs1;

    }


}
