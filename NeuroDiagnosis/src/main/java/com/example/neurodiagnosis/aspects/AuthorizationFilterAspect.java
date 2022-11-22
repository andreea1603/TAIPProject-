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
public class AuthorizationFilterAspect {
    static Logger LOG = Logger.getLogger(InterceptorLoginAspect.class.getName());

    @Pointcut("execution(* com.example.neurodiagnosis.webapi.filters.AuthorizationFilter.filter(..))")
    public void loggingPointCut() {}

    @AfterThrowing(
            value = "loggingPointCut()",
            throwing = "e")
    public void after(JoinPoint joinPoint, Exception e) {
        LOG.info("Attempt to request with invalid token: " + joinPoint.getArgs()[0]);
    }
}
