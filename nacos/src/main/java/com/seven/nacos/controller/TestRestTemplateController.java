package com.seven.nacos.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("test01")
    public String test01() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://nacos/restTemplate/get", String.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return "请求失败";
        }
    }

    @PostMapping("ask")
    public Object ask(@RequestBody Ask ask) {
        ResponseEntity<String> execute = restTemplate.exchange(ask.getParmaUrl(), ask.httpMethod, ask.getHttpEntity(), String.class, ask.uriVariables);
        if (execute.getStatusCode().is2xxSuccessful()) {
            return execute.getBody();
        } else {
            return "请求失败";
        }
    }

    @Data
    public static class Ask {
        private String url;
        private Map<String, Object> uriVariables = new HashMap<>();
        private Map<String, Object> header = new HashMap<>();
        private HttpMethod httpMethod;
        private String body;

        public String getParmaUrl() {
            StringBuilder sb = new StringBuilder(url);
            if (!uriVariables.isEmpty() && !url.contains("?")) {
                sb.append("?");
            }
            uriVariables.forEach((k, v) -> {
                sb.append(k).append("={").append(k).append("}").append("&");
            });
            return sb.toString();
        }

        public HttpEntity<?> getHttpEntity() {
            HttpHeaders headers = new HttpHeaders();
            headers.addAll(headers);
            return new HttpEntity<>(body, headers);
        }
    }

    @GetMapping("get")
    public String getInfo() {
        return "get success";
    }

    @GetMapping("get/uri")
    public String getInfo(@RequestParam String p1, @RequestParam String p2) {
        return "get/uri success  " + p1 + p2;
    }

    @PostMapping("post")
    public String postInfo() {
        return "post success";
    }

    // TODO: 2021/9/9 有问题
    @PostMapping("post/body")
    public String postInfo(@RequestBody Ask ask) {
        return "post/body success" + ask;
    }
}
