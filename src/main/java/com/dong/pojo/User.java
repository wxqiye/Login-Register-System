/*
* user类，与数据库映射。
* */

package com.dong.pojo;

import java.util.Date;
public class User {
    //静态属性
    //用户id
    private Integer id;
    //用户邮箱
    private String email;
    //用户编码
    private String userCode;
    //用户名称
    private String userName;
    //用户密码
    private String userPassword;
    //用户性别
    private Integer gender;
    //用户出生日期
    private Date birthday;
    //用户电话
    private String phone;
    //用户年龄
    private Integer age;
    //用户头像存储路径。
    private String path;

    //get与set方法
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

   }
