/*
* 过滤器，设置网页编码避免乱码。
* */
package com.dong.filter;

import javax.servlet.*;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
}

    public void destroy() {
        System.out.println("销毁");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置了请求与转发的编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request,response);
    }
}
