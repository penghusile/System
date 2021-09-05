package com.web2019.team4.system.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web2019.team4.system.Dao.Entity.Course;
import com.web2019.team4.system.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping(value = {"/addCourse"})
    @ResponseBody
    public boolean addCourse(@RequestBody String jsonObject) {
        Course course= JSONObject.parseObject(jsonObject,Course.class);
        return adminService.addCourse(course);
    }
    @RequestMapping(value = {"/loadAllCourse"})
    @ResponseBody
    public String loadCourse() {
        List<Course> courseList = adminService.selectAllCourse();
        return JSON.toJSONString(courseList);
    }
    @RequestMapping(value = {"/delCourse"})
    @ResponseBody
    public boolean delCourse(@RequestBody String jsonObject) {
        Course course= JSONObject.parseObject(jsonObject,Course.class);
        return adminService.delCourse(course);
    }
    @RequestMapping(value = {"/updateCourse"})
    @ResponseBody
    public boolean updateCourse(@RequestBody String jsonObject) {
        Course course= JSONObject.parseObject(jsonObject,Course.class);
        return adminService.updateCourse(course);
    }
    @RequestMapping(value = {"/selectCourseByID"})
    @ResponseBody
    public String selectCourseByID(@RequestBody String jsonObject) {
        Course course= JSONObject.parseObject(jsonObject,Course.class);
        List<Course> courseList= adminService.selectCourseByID(course.getId());
        return JSON.toJSONString(courseList);
    }
}
