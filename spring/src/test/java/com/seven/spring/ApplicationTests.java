package com.seven.spring;

import com.seven.spring.aop.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class ApplicationTests {
    @Autowired
    TestService testService;

    /**
     * 环绕通知之前
     * 前置通知
     * 	我是方法
     * 后置通知
     * 环绕通知之后
     */
    @Test
    void zc() {
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println("springVersion:" + springVersion + "\tspringBootVersion:" + springBootVersion);
        testService.zc();
    }

    /**
     * 环绕通知之前
     * 前置通知
     * 	我是错误方法
     * 异常通知
     * 后置通知
     */
    @Test
    void error() {
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println("springVersion:" + springVersion + "\tspringBootVersion:" + springBootVersion);
        testService.error();
    }

}
