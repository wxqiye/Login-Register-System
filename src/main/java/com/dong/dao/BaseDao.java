/*
* 直接与数据库进行交互，实现了最基本的增删改查功能
* */
package com.dong.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
private static String driver;
private static String url;
private static String username;
private static String password;
//静态代码块，预加载
static {
    Properties properties = new Properties();
    //通过类加载器获取对应的资源。
    InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
    try {
        //读取properties文件里的内容
        properties.load(is);
    } catch (IOException e) {
        e.printStackTrace();
    }
    //得到properties里的这四个量。
    driver = properties.getProperty("driver");
    url = properties.getProperty("url");
    username = properties.getProperty("username");
    password = properties.getProperty("password");

}
//连接数据库
public static Connection getConnection(){
    Connection connection =null;
    try {
        Class.forName(driver);//加载驱动
        connection = DriverManager.getConnection(url,username,password);//连接数据库
    } catch (Exception e) {
        e.printStackTrace();
    }
    return connection;
}
//编写查询类
public static ResultSet execute(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] params) throws SQLException {

    preparedStatement =connection.prepareStatement(sql);//预加载

    for (int i = 0; i < params.length; i++) {
        preparedStatement.setObject(i+1,params[i]);
    }//把要查询的参数对象传入预加载里，因为占位从1开始所以加一。
    resultSet = preparedStatement.executeQuery();//得到结果集。
    return resultSet;
}
//编写增删改类
public static int execute(Connection connection,PreparedStatement preparedStatement, String sql, Object[] params ) throws SQLException {
    preparedStatement =connection.prepareStatement(sql);//预加载
    for (int i = 0; i < params.length; i++) {
        preparedStatement.setObject(i+1,params[i]);
    }//把要查询的参数对象传入预加载里，因为占位从1开始所以加一
    int updateRows = preparedStatement.executeUpdate();//得到结果。
    return updateRows;
}
//释放资源
    public static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
    boolean flag = true;//打标,有一个地方没有释放成功，就会成为false
    if(resultSet!=null) {
        try {
            resultSet.close();
            //GC回收
            resultSet = null;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
    }
    if(preparedStatement!=null) {
        try {
            preparedStatement.close();
            //GC回收
            preparedStatement = null;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
    }
    if(connection!=null) {
        try {
            connection.close();
            //GC回收
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
    }

    return flag;
    }


    }
