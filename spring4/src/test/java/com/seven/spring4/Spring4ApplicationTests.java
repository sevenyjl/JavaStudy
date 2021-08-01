package com.seven.spring4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Spring4Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Spring4ApplicationTests {

    @Autowired
    TestService testService;

    /**
     * 环绕通知之前
     * 前置通知
     * 	我是方法
     * 环绕通知之后
     * 后置通知
     */
    @Test
    public void zc() {
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println("springVersion:" + springVersion + "\tspringBootVersion:" + springBootVersion);
        testService.zc();
    }

    /**
     * springVersion:4.3.13.RELEASE	springBootVersion:1.5.9.RELEASE
     * 环绕通知之前
     * 前置通知
     * 	我是错误方法
     * 后置通知
     * 异常通知
     */
    @Test
    public void error() {
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println("springVersion:" + springVersion + "\tspringBootVersion:" + springBootVersion);
        testService.error();
    }

}
