/*
* service层，主要处理业务逻辑，并调用dao层。
* 注意：为了login方法的复用，login只实现了查询用户的功能，取用户的功能。
*      密码正确与否的功能我放在了LoginServlet层进行实现
* */

package com.dong.service.user;

import com.dong.pojo.User;

public interface UserService {

    public User login(String userCode, String password);
    public int register(String userCode, String password,String email);
    public int update(String userCode,String userName, String birthday,String phone);
    public int upload(String userCode,String path);
}
