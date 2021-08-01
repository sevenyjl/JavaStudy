package com.seven.spring4;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String zc() {
        System.out.println("\t我是方法");
        return "success";
    }

    public String error() {
        System.out.println("\t我是错误方法");
        int i = 1 / 0;
        return "success";
    }
}
