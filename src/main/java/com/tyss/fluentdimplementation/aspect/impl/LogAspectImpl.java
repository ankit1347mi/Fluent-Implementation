package com.tyss.fluentdimplementation.aspect.impl;

import com.tyss.fluentdimplementation.aspect.LogAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspectImpl {
    @Before("@annotation(logAspect)")
    public void logAspect(JoinPoint joinPoint, LogAspect logAspect) {
        String name = joinPoint.getSignature().getDeclaringType().getPackage().getName();
        log.info(joinPoint.getSignature() + " Method started in " + name);
    }

    @After("@annotation(logAspect)")
    public void afterLogAspect(JoinPoint joinPoint, LogAspect logAspect) {
        String name = joinPoint.getSignature().getDeclaringType().getPackage().getName();
        log.info(joinPoint.getSignature().getName() + " method execution completed in " + name);
    }

    @AfterReturning(value = "@annotation(logAspect)", returning = "string")
    public void afterReturningLogAspect(JoinPoint joinPoint, String string, LogAspect logAspect) {
        log.info("valued returned :" + string);
    }
}
