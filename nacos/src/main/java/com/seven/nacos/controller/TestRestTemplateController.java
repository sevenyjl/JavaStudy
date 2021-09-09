package com.seven.nacos.controller;

import cn.hutool.json.JSONUtil;
import com.seven.nacos.bean.HttpBean;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("restTemplate")
public class TestRestTemplateController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("ask")
    public Object ask(@RequestBody HttpBean httpBean) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpBean.getHeaders().forEach(httpHeaders::add);
        HttpEntity<Object> objectHttpEntity = null;
        if (httpBean.getJsonObject() != null) {
            objectHttpEntity = new HttpEntity<>(JSONUtil.toJsonStr(httpBean.getJsonObject()), httpHeaders);
        } else if (httpBean.getFromData() != null) {
            objectHttpEntity = new HttpEntity<>(httpBean.getFromData(), httpHeaders);
        } else {
            objectHttpEntity = new HttpEntity<>(null, httpHeaders);
        }
        ResponseEntity<Object> execute = restTemplate.exchange(httpBean.getUrl(), httpBean.getHttpMethod(), objectHttpEntity, Object.class);
        if (execute.getStatusCode().is2xxSuccessful()) {
            return execute.getBody();
        } else {
            // TODO: 2021/9/9 做回调增强
            return "请求失败";
        }
    }

}
