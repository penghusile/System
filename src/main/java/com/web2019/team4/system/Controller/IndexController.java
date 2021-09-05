package com.web2019.team4.system.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web2019.team4.system.Dao.Entity.User;
import com.web2019.team4.system.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;



//登录界面的controller：处理登录、注册....
@Controller
public class IndexController {
    String uploadPathStr="E:\\PengHusile\\Desktop";
    Logger logger= LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private UserService userService;
    private HttpSession session = null;
    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }
    //登录并跳转
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request){
        User user;
        //已经登陆后直接输入地址访问
        if(request.getParameter("user_name")==null){
            user=new User(
                    session.getAttribute("username").toString(),
                    session.getAttribute("password").toString());
        }
        //正常登录界面登录
        else {
            user = new User(
                    request.getParameter("user_name"),
                    request.getParameter("user_password"));
        }
        Map<String,String> map =  userService.login(user);
        String message=map.get("message");
        String isSuccess=map.get("success");

        if (isSuccess.equals("false"))
            return new ModelAndView("index","error", message);
        else {
            session = request.getSession();
            session.setAttribute("username", user.getId());
            session.setAttribute("password", user.getPassword());
            return new ModelAndView("main");
        }
    }
    @RequestMapping(value = "/loginFromAndroid")
    @ResponseBody
    public String loginFromAndroid(@RequestBody String jsonObject){
        User user = JSONObject.parseObject(jsonObject,User.class);
        Map<String,String> map =  userService.login(user);
        String message=map.get("message");
        String isSuccess=map.get("success");
        String s = JSON.toJSONString(map);
        return s;

    }
    //跳转注册界面
    @RequestMapping(value = "/toRegist")
    public String toRegist(){
        return "regist";
    }
    @RequestMapping(value = "/regist")
    public ModelAndView regist(HttpServletRequest request){
        User user=new User(
                request.getParameter("user_name"),
                request.getParameter("user_password")
        );
        boolean isRegist = userService.regist(user);
        if (isRegist)
            return new ModelAndView("index","registSuccess", "注册成功");
        else
            return new ModelAndView("regist", "registError", "注册失败");
    }
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam MultipartFile file,@RequestParam String fileName) {
        if (file ==null||fileName==null||file.isEmpty()||fileName.isEmpty()){
            logger.debug("空，上传失败");
            return "上传失败";
        }
        try(InputStream inputStream = file.getInputStream()){
            Path uploadPath= Paths.get(uploadPathStr);
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdirs();
            Files.copy(inputStream, Paths.get(uploadPathStr).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("upload file , filename is "+fileName);

            logger.debug(fileName+file.isEmpty());
            return "上传成功";
        }
        catch (IOException e)
        {logger.debug(e.toString());
            e.printStackTrace();
            return "上传失败";
        }
    }
    @RequestMapping(value = "/uploadFinger")
    @ResponseBody
    public String uploadFinger(@RequestParam String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            logger.debug("空，上传失败");
            return "上传失败";
        }
        System.out.println(fileName);
        return fileName;

    }

}
