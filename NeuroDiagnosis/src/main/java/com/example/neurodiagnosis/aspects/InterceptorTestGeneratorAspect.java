package com.example.neurodiagnosis.aspects;


import com.example.neurodiagnosis.aspects.beans.TestCounterBean;
import com.example.neurodiagnosis.webapi.dtos.test.TestDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
@Slf4j
public class InterceptorTestGeneratorAspect {
    static Logger LOG = Logger.getLogger(InterceptorTestGeneratorAspect.class.getName());

    @Pointcut("execution(* com.example.neurodiagnosis.webapi.controllers.TestGeneratorController.getTestForUser())")
    public void loggingPointCut() {}

    @Before(value = "loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        LOG.info("Attempt of new test generation");
    }

    @AfterReturning(value = "loggingPointCut()",
            returning = "test")
    public void afterReturning(JoinPoint joinPoint, TestDTO test) {
        LOG.info("New test generated");

        var counter = new TestCounterBean();
        counter.addNewGeneratedTest();
    }
}
