package com.itheima.Intercepor;

import com.itheima.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截方法拦截啦");
        //不拦截静态资源
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return true;
        }
        request.getSession().setAttribute("login_msg","您未登录,请登录" );
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return false;
}
}
