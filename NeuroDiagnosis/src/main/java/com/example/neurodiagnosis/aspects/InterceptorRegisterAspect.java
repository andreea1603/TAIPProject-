package com.example.neurodiagnosis.aspects;


import com.example.neurodiagnosis.aspects.beans.UserRegisterCounterBean;
import com.example.neurodiagnosis.webapi.dtos.ApplicationUserDTO;
import com.example.neurodiagnosis.webapi.dtos.JwtTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
@Slf4j
public class InterceptorRegisterAspect {
    static Logger LOG = Logger.getLogger(InterceptorRegisterAspect.class.getName());

    @Pointcut("execution(* com.example.neurodiagnosis.webapi.controllers.AuthController.registerUser(..))")
    public void loggingPointCut() {}

    @Before(value = "loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        LOG.info("Register attempt for user: " + joinPoint.getArgs()[0]);
    }

    @AfterThrowing(value = "loggingPointCut()",
        throwing = "e")
    public void after(JoinPoint joinPoint, Exception e) {
        LOG.info("Register failed for: " + joinPoint.getArgs()[0]);

        var counter = new UserRegisterCounterBean();
        counter.addFailedRegister();
    }

    @AfterReturning(value = "loggingPointCut()",
            returning = "user")
    public void afterReturning(JoinPoint joinPoint, ApplicationUserDTO user) {
        LOG.info("Register succeeded for: " + joinPoint.getArgs()[0]);

        var counter = new UserRegisterCounterBean();
        counter.addSucceededRegister();
    }
}
