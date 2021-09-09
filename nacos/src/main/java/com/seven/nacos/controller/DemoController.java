package com.seven.nacos.controller;

import cn.hutool.json.JSONObject;
import com.seven.nacos.common.ApiResult;
import lombok.Data;
import lombok.experimental.Accessors;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping
public class DemoController {

    @GetMapping("get")
    public ApiResult get() {
        return ApiResult.success();
    }

    @GetMapping("get/param")
    public ApiResult getParam(@RequestParam String p1, @RequestParam String p2) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("p1", p1);
        objectObjectHashMap.put("p2", p2);
        return ApiResult.success("请求成功", objectObjectHashMap);
    }

    @GetMapping("get/param/header")
    public ApiResult getParamHeader(@RequestParam String p1, @RequestParam String p2
            , @RequestHeader String token) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("p1", p1);
        objectObjectHashMap.put("p2", p2);
        objectObjectHashMap.put("token", token);
        return ApiResult.success("请求成功", objectObjectHashMap);
    }

    @GetMapping("get/param/header/{path}")
    public ApiResult getParamHeaderPath(@RequestParam String p1, @RequestParam String p2
            , @RequestHeader String token, @PathVariable String path) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("p1", p1);
        objectObjectHashMap.put("p2", p2);
        objectObjectHashMap.put("token", token);
        objectObjectHashMap.put("path", path);
        return ApiResult.success("请求成功", objectObjectHashMap);
    }


    @PostMapping("post")
    public ApiResult post() {
        return ApiResult.success();
    }

    @PostMapping("post/param")
    public ApiResult postParam(@RequestParam String p1, @RequestParam String p2) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("p1", p1);
        objectObjectHashMap.put("p2", p2);
        return ApiResult.success("请求成功", objectObjectHashMap);
    }

    @PostMapping("post/param/header")
    public ApiResult postParamHeader(@RequestParam String p1, @RequestParam String p2
            , @RequestHeader String token) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("p1", p1);
        objectObjectHashMap.put("p2", p2);
        objectObjectHashMap.put("token", token);
        return ApiResult.success("请求成功", objectObjectHashMap);
    }

    @PostMapping("post/param/header/{path}")
    public ApiResult postParamHeaderPath(@RequestParam String p1, @RequestParam String p2
            , @RequestHeader String token, @PathVariable String path) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("p1", p1);
        objectObjectHashMap.put("p2", p2);
        objectObjectHashMap.put("token", token);
        objectObjectHashMap.put("path", path);
        return ApiResult.success("请求成功", objectObjectHashMap);
    }

    @PostMapping("post/param/header/{path}/json")
    public ApiResult postParamHeaderPathJson(@RequestParam String p1, @RequestParam String p2
            , @RequestHeader String token, @PathVariable String path, @RequestBody JSONObject jsonObject) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("p1", p1);
        objectObjectHashMap.put("p2", p2);
        objectObjectHashMap.put("token", token);
        objectObjectHashMap.put("path", path);
        objectObjectHashMap.put("jsonObject", jsonObject);
        return ApiResult.success("请求成功", objectObjectHashMap);
    }

//    @PostMapping("post/param/header/{path}/from")
//    public ApiResult postParamHeaderPathFrom(@RequestParam String p1, @RequestParam String p2
//            , @RequestHeader String token, @PathVariable String path,@RequestParam("file") JSONObject from) {
//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("p1", p1);
//        objectObjectHashMap.put("p2", p2);
//        objectObjectHashMap.put("token", token);
//        objectObjectHashMap.put("path", path);
//        objectObjectHashMap.put("from", from);
//        return ApiResult.success("请求成功", objectObjectHashMap);
//    }


}
