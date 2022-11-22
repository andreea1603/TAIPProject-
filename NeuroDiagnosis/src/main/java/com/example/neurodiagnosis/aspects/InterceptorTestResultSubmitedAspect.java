package com.example.neurodiagnosis.aspects;


import com.example.neurodiagnosis.aspects.beans.TestCounterBean;
import com.example.neurodiagnosis.webapi.dtos.TestResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
@Slf4j
public class InterceptorTestResultSubmitedAspect {
    static Logger LOG = Logger.getLogger(InterceptorTestResultSubmitedAspect.class.getName());

    @Pointcut("execution(* com.example.neurodiagnosis.webapi.controllers.MmseTestsController.submitTestsResults(..))")
    public void loggingPointCut() {}

    @Before(value = "loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        LOG.info("Attempt of new test result submission");
    }

    @AfterReturning(value = "loggingPointCut()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, TestResultDTO result) {
        LOG.info("New test generated");

        var counter = new TestCounterBean();
        counter.addNewTestSubmission(result.userId);
    }
}
