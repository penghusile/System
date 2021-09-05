package com.web2019.team4.system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    //退出
    @RequestMapping(value = {"/quit"})
    public String quit(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }
    //管理员业务页面跳转
    @RequestMapping(value = {"/toAddCourse"})
    public String toAddCourse(){
        return "admin/addCourse";
    }
    @RequestMapping(value = {"/toDelCourse"})
    public String toDelCourse(){
        return "admin/delCourse";
    }
    @RequestMapping(value = {"/toUpdateCourse"})
    public String toUpdateCourse(){
        return "admin/updateCourse";
    }
    @RequestMapping(value = {"/toSearchCourse"})
    public String toSearchCourse(){
        return "admin/searchCourse";
    }
    //学生业务页面跳转
}
