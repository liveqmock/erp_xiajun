package com.wangqin.globalshop.company.aop;

import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author angus
 * @date 2018/8/9
 */
@Slf4j
@Aspect
@Component
public class CompanyOptAspect {

    @Pointcut("execution(* com.wangqin.globalshop.company.controller.CompanyController.*(..))")
    public void authenticate() {
    }

    @Around("authenticate()")
    public Object doBefore(ProceedingJoinPoint pjp) throws Throwable {
        // 商家管理粗略的权限认证
        String legalCompanyNo = "-1";
        String legalUserId = "sysadmin";
        String loginUserCompanyNo = AppUtil.getLoginUserCompanyNo();
        String loginUserId = AppUtil.getLoginUserId();
        if (!legalCompanyNo.equals(loginUserCompanyNo) || !legalUserId.equals(loginUserId)) {
            JsonResult result = new JsonResult();
            result.buildIsSuccess(false).buildMsg("没有权限");
            return result;
        }
        return pjp.proceed();
    }
}
