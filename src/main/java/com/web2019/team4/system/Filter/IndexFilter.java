package com.web2019.team4.system.Filter;

import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@Slf4j
@WebFilter(urlPatterns={"/","/index","/main"},filterName="indexFilter")
@Order(1)
public class IndexFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = req.getRequestURI();
        System.out.println("过滤器：请求地址"+requestURI);
        HttpSession session = req.getSession();
        if(session.getAttribute("username")==null){
            servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);

        }else{
            chain.doFilter(servletRequest, servletResponse);
        }
    }
    @Override
    public void destroy() {

    }
}
