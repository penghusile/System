package com.web2019.team4.system.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Slf4j
public class IndexAspect {
    @Pointcut("execution(public * com.web2019.team4.system.Controller.IndexController.login(..))")
    public void login() {
    }

    @Around("login()")
    public Object doAroundLogin(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("参数="+arg.toString()+" ");
        }
        try {
            Object object=point.proceed();
            System.out.println("返回值="+object);
            //必须返回，不然前端接收不到
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕增强：日志log");
        return null;
    }
    @Pointcut("execution(public * com.web2019.team4.system.Controller.IndexController.regist(..))")
    public void regist() {
    }
    @Around("regist()")
    public Object doAroundRegist(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("参数="+arg+" ");
        }
        try {
            Object object=point.proceed();
            System.out.println("返回值="+object);
            //必须返回，不然前端接收不到
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕增强：日志log");
        return null;
    }
}
