package com.seven.spring4;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAop {

    @Pointcut("execution(public * com.seven.spring4.TestService.*())")
    public void pointcut() {
        System.out.println("pointcut");
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("前置通知");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("后置通知");

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知之前");
        Object proceed = pjp.proceed();
        System.out.println("环绕通知之后");
        return proceed;
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("异常通知");
    }
}
