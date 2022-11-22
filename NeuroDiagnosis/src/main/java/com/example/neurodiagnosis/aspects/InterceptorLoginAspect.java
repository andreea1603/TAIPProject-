package com.example.neurodiagnosis.aspects;


import com.example.neurodiagnosis.aspects.beans.UserLoggedInCounterBean;
import com.example.neurodiagnosis.webapi.dtos.JwtTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
@Slf4j
public class InterceptorLoginAspect {
    static Logger LOG = Logger.getLogger(InterceptorLoginAspect.class.getName());

    @Pointcut("execution(* com.example.neurodiagnosis.webapi.controllers.AuthController.loginUser(..))")
    public void loggingPointCut() {}

    @Before(value = "loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        LOG.info("Login attempt for user: " + joinPoint.getArgs()[0]);
    }

    @AfterThrowing(
            value = "loggingPointCut()",
            throwing = "e")
    public void after(JoinPoint joinPoint, Exception e) {
        LOG.info("Login failed for: " + joinPoint.getArgs()[0]);

        var counter = new UserLoggedInCounterBean();
        counter.addFailedLogin();
    }

    @AfterReturning(
            value = "loggingPointCut()",
            returning = "token")
    public void afterReturning(JoinPoint joinPoint, JwtTokenResponse token) {
        LOG.info("Login succeeded for: " + joinPoint.getArgs()[0]);

        var counter = new UserLoggedInCounterBean();
        counter.addSucceededLogin();
    }
}
