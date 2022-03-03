/*
* 接口
* 具体实现邮件的发送
* */
package com.dong.utl;

public interface Mail {
    public void sendmail(String accmail) throws Exception;
}