package com.seven.nacos.bean;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class HttpBean {
    private String url;
    //    private Map<String, String> uriVariables = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private HttpMethod httpMethod;
    private JSONObject jsonObject;
    private Map<String, String> fromData;


    public static HttpBean request(@NonNull String url, @NonNull HttpMethod httpMethod, Map<String, String> uriVariables, Map<String, String> headers) {
        HttpBean httpBean = new HttpBean();
        String[] split = url.split("\\?");
        StringBuilder sb = new StringBuilder(split[0] + "?");
        if (uriVariables != null) {
            uriVariables.forEach((k, v) -> {
                sb.append(k).append("={").append(k).append("}").append("&");
            });
        }
        if (split.length > 1) {
            sb.append(split[1]);
        }
        if (headers == null) headers = new HashMap<>();

        return httpBean.setHttpMethod(httpMethod).setUrl(sb.toString()).setHeaders(headers);
    }

    public static HttpBean requestJson(@NonNull String url, @NonNull HttpMethod httpMethod, Map<String, String> uriVariables, Map<String, String> headers, JSONObject jsonObject) {
        HttpBean httpBean = request(url, httpMethod, uriVariables, headers);
        if (jsonObject != null) {
            headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            httpBean.setJsonObject(jsonObject);
        }
        return httpBean;
    }

    public static HttpBean requestFromData(@NonNull String url, @NonNull HttpMethod httpMethod, Map<String, String> uriVariables, Map<String, String> headers, Map<String, String> from) {
        HttpBean httpBean = request(url, httpMethod, uriVariables, headers);
        if (from != null) {
            headers.put("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
            httpBean.setFromData(from);
        }
        return httpBean;
    }

    public static HttpBean getRequest(String url) {
        return request(url, HttpMethod.GET, null, null);
    }

    public static HttpBean getRequest(String url, Map<String, String> uriVariables) {
        return request(url, HttpMethod.GET, uriVariables, null);
    }

    public static HttpBean getRequest(String url, Map<String, String> uriVariables, Map<String, String> headers) {
        return request(url, HttpMethod.GET, uriVariables, headers);
    }

    public static HttpBean postRequest(String url) {
        return request(url, HttpMethod.POST, null, null);
    }

    public static HttpBean postRequest(String url, JSONObject jsonObject) {
        return requestJson(url, HttpMethod.POST, null, null, jsonObject);
    }

    public static HttpBean postRequest(String url, JSONObject jsonObject, Map<String, String> headers) {
        return requestJson(url, HttpMethod.POST, null, headers, jsonObject);
    }

    public static HttpBean postRequest(String url, Map<String, String> uriVariables) {
        return request(url, HttpMethod.POST, uriVariables, null);
    }

    public static JSONObject bean2JSONObject(Object bean) {
        return JSONUtil.parseObj(bean);
    }

    private HttpBean() {
    }
}
