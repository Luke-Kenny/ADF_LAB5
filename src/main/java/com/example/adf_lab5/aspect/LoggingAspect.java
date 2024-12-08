package com.example.adf_lab5.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut to match all methods in the service package
    @Pointcut("execution(* com.example.adf_lab5.service.impl.*.*(..))")
    public void serviceMethods() {}

    // Log before method execution
    @Before("serviceMethods()")
    public void logBeforeMethod() {
        logger.info("Before method execution");
    }

    // Log after method execution
    @After("serviceMethods()")
    public void logAfterMethod() {
        logger.info("After method execution");
    }

    // Log after a method returns successfully
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(Object result) {
        logger.info("Method returned successfully with result: {}", result);
    }

    // Log after a method throws an exception
    @AfterThrowing(value = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        logger.error("Exception thrown: {}", exception.getMessage());
    }

    // Log before and after method execution with execution time and parameters
    @Around("serviceMethods()")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        logger.info("Around advice: Before executing method: {}", joinPoint.getSignature());
        logger.debug("Method arguments: {}", Arrays.toString(joinPoint.getArgs()));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Around advice: Exception in method: {}", joinPoint.getSignature());
            throw e;
        }

        long executionTime = System.currentTimeMillis() - start;
        logger.info("Around advice: After executing method: {}", joinPoint.getSignature());
        logger.info("Execution time: {} ms", executionTime);

        return result;
    }
}
