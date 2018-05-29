package com.wangqin.globalshop.biz1.app.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wangqin.globalshop.common.utils.LogWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录校验
 */
@Aspect
@Component
@Slf4j
public class AuthAndPerfInterceptor {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> template;

//    @Resource(name = "redisTemplate")
//    private ValueOperations<String, Object> vOps;

//    static RedisCacheTemplate redisCache=new RedisCacheTemplate();

    //    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    @Pointcut("execution(public * com.wangqin.globalshop..*Controller.*(..))) and @within(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcutExpression() {

    }


    @Before("pointcutExpression()")
    public void preMethod(JoinPoint jp) {
        LogWorker.log(log,"call preMethod, JoinPoint: " + jp.toString(),"");
        try {
            final Object[] args = jp.getArgs(); // 请求参数
            for (Object Arg : jp.getArgs()) {

            }

            HttpServletRequest request = (HttpServletRequest) args[0];
            String uri = request.getRequestURI();
//            int beginIndex = uri.lastIndexOf("/") + 1;
//            int endIndex = uri.lastIndexOf(".");
//            String urlToken = uri.substring(beginIndex, endIndex);

            template.opsForValue().set("sessionId",request.getSession().getId());



        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @After("pointcutExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        LogWorker.log(log,"后置通知","");
    }
//
//
//    @AfterReturning(value = "pointcutExpression()", returning = "returnValue")
//    public void afterRunningMethod(JoinPoint joinPoint, Object returnValue) {
//        LogWorker.log(log,"返回通知执行，执行结果：" ,"");
//    }
//
//
//    @AfterThrowing(value = "pointcutExpression()", throwing = "e")
//    public void afterThrowingMethod(JoinPoint joinPoint, Exception e) {
//        System.out.println("异常通知, 出现异常 ：" + e);
//    }


    @Around("pointcutExpression()")
    public Object aroundMethod(ProceedingJoinPoint pjd) {

        Object result = null;
        String methodName = pjd.getSignature().getName();

        try {
            //前置通知
//            System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
            LogWorker.logStart(log,methodName+" call, aroundMethod JoinPoint: " + pjd.toString(),"");
            long st=System.nanoTime();

            //执行目标方法
            result = pjd.proceed();
            //返回通知
            long duration=System.nanoTime()-st;
//            System.out.println("The method " + methodName + " use " + duration+" nano-seconds.");
            LogWorker.logStart(log,methodName + " use " + duration/1000000+"ms.","");
        } catch (Throwable e) {
            //异常通知
            System.out.println("The method " + methodName + " occurs exception:" + e);
            throw new RuntimeException(e);
        }
        //后置通知
//        System.out.println("The method " + methodName + " ends");

        return result;
    }
}
