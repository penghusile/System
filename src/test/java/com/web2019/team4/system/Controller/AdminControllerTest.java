package com.web2019.team4.system.Controller;

import com.web2019.team4.system.Service.AdminService;
import com.web2019.team4.system.SystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
//SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
@SpringBootTest(classes = SystemApplication.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
public class AdminControllerTest {
    @Autowired
    private AdminService adminService;
    @Test
    public void addCourse() {
    }

    @Test
    public void loadCourse() {
        adminService.selectAllCourse();
    }
}