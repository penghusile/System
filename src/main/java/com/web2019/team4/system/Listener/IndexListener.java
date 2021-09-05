package com.web2019.team4.system.Listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class IndexListener implements HttpSessionListener, HttpSessionAttributeListener {
    private Integer CURRENT_ONLINE_COUNT = 0;  //计算在线人数的数量
    private Integer CURRENT_LOGIN_COUNT = 0;  //计算登陆人数的数量
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        CURRENT_ONLINE_COUNT++;
        ServletContext app = httpSessionEvent.getSession().getServletContext();
        app.setAttribute("OnlineCount", CURRENT_ONLINE_COUNT);
        System.out.println("在线人数：" + CURRENT_ONLINE_COUNT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        CURRENT_ONLINE_COUNT--;
        ServletContext app = httpSessionEvent.getSession().getServletContext();
        app.setAttribute("OnlineCount", CURRENT_ONLINE_COUNT);
        System.out.println("在线人数：" + CURRENT_ONLINE_COUNT);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        CURRENT_LOGIN_COUNT++;
        ServletContext app = httpSessionBindingEvent.getSession().getServletContext();
        app.setAttribute("LoginCount", CURRENT_ONLINE_COUNT);
        System.out.println("登陆人数：" + CURRENT_LOGIN_COUNT);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        CURRENT_LOGIN_COUNT--;
        ServletContext app = httpSessionBindingEvent.getSession().getServletContext();
        app.setAttribute("LoginCount", CURRENT_ONLINE_COUNT);
        System.out.println("登陆人数：" + CURRENT_LOGIN_COUNT);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
