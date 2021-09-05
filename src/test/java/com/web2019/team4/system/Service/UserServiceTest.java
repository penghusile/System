package com.web2019.team4.system.Service;

import com.web2019.team4.system.Dao.Entity.Gender;
import com.web2019.team4.system.Dao.Entity.User;
import com.web2019.team4.system.SystemApplication;
import com.web2019.team4.system.Common.Utils.xmlReflectUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
//SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
@SpringBootTest(classes = SystemApplication.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void regist() throws Exception {
        xmlReflectUtil xmlReflectUtil = new xmlReflectUtil("beans.xml");
        //通过List查找指定类型的bean
//        List<User> userList = xmlReflect.getBeansOfType(User.class);
//        List<Gender> genderList = xmlReflect.getBeansOfType(Gender.class);
//        Gender gender=genderList.get(0);
//        User user = userList.get(0);
//        System.out.println(gender.toString());
//        System.out.println(user.toString());
        Map map = xmlReflectUtil.getAllBeans(User.class);
        Set<Map.Entry<String, Object>> entrys = map.entrySet();
        User user= new User();
        System.out.println(map.size());
        for (Map.Entry<String, Object> item : entrys) {
            String id =((User)item.getValue()).getId();
            String password =((User)item.getValue()).getPassword();
            Gender gender =((User)item.getValue()).getGender();
            System.out.println(item.getValue());
            user.setId(id);
            user.setPassword(password);
            user.setGender(gender);
            userService.regist(user);
        }

    }

    @Test
    public void login() {
        User user= new User("1001","0123456");
        System.out.println(userService.login(user));
        System.out.println(userService.login(user));
        System.out.println(userService.login(user));
        System.out.println(userService.login(user));
        System.out.println(userService.login(user));
        System.out.println(userService.login(user));
    }
}