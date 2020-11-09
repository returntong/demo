package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcuts {

    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    private void anyOldTransfer() {
    }

    /**
     * 前置通知
     */
    @Before("anyOldTransfer()")
    public void doAccessCheck() {
        // ...
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     */
    @After("anyOldTransfer()")
    public void doReleaseLock() {
        // ...
        System.out.println("后置通知");
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("anyOldTransfer()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        try {
            System.out.println("环绕通知1");
            // start stopwatch
            retVal = pjp.proceed();
            System.out.println("环绕通知2");
        } catch (Exception e) {
            System.out.println("环绕通知(catch)");
        }

        // stop stopwatch
        return retVal;
    }

    /**
     * 方法执行返回后通知
     */
    @AfterReturning("anyOldTransfer()")
    public void afterAccessCheck() {
        // ...
        System.out.println("方法执行返回后通知");
    }

    /**
     * 抛出异常后通知
     */
    @AfterThrowing("anyOldTransfer()")
    public void doRecoveryActions() {
        // ...
        System.out.println("抛出异常后通知");
    }
}
