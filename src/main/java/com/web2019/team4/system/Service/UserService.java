package com.web2019.team4.system.Service;

import com.web2019.team4.system.Common.Utils.JwtTokenUtil;
import com.web2019.team4.system.Dao.Entity.Permission;
import com.web2019.team4.system.Dao.Entity.User;
import com.web2019.team4.system.Dao.Mapper.UserMapper;
import com.web2019.team4.system.Common.Utils.EhcacheUtil;
import com.web2019.team4.system.Dao.Mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    UserMapper userMapper;
    @Resource
    private EhcacheUtil ehcacheUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRoleMapper userRoleMapper;

    //注册函数
    @Transactional
    public boolean regist(User user){
        userMapper.insertUser(user);
        //检查事务是否起作用
        if (user.getId().equals("123456")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }
    //登录函数
    public Map login(User user){
        String id = user.getId();
        int userCount= userMapper.selectUser(user);
        Map resultMap=new HashMap();

        boolean flag=ehcacheUtil.get(id);
        //已经锁定
        if(flag){
            resultMap.put("success","false");
            resultMap.put("message","用户已被锁定，请5分钟后登陆!");
            return resultMap;
        }
        String message="";
        String isSuccess="false";
        //判断输入错误
        if(userCount==0){
            boolean isLook=ehcacheUtil.put(id);
            if(isLook){
                message="连续输错3次密码，账号已被锁定";
            }else{
                message="密码错误，请重新输入!";
            }
        }else{
            message="登陆成功";
            isSuccess="true";
        }
        resultMap.put("success",isSuccess);
        resultMap.put("message",message);
        return resultMap;

    }

    public String login(String username, String password) {

        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    public User getUserById(String userId){
       User user=userMapper.selectUserById(userId);
       return user;
    }

    public List<Permission> getPermissionList(String userId) {
        return userRoleMapper.getPermissionList(userId);
    }
}
