package top.sinfulxx.loki.common;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * rest请求工具类
 *
 * @author hanyuzhe
 * @date 2019/6/21 上午10:22
 * @since 1.0
 */
@Component
public class OhMyHttpUtils {

    @Autowired
    private RestTemplate restTemplate;

    public <T> T get(String url, Map<String, String> paramMap, Class<T> resultType) {
        StringBuilder uri = new StringBuilder(url + "?");
        if (!CollectionUtils.isEmpty(paramMap)) {
            paramMap.forEach((v, k) -> uri.append(v).append("=").append(k).append("&"));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String responseStr = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class).getBody();
        return JSONObject.parseObject(responseStr, resultType);
    }

    public <T> T get(String url, Map<String, String> paramMap, HttpHeaders headers, Class<T> resultType) {
        StringBuilder uri = new StringBuilder(url + "?");
        if (!CollectionUtils.isEmpty(paramMap)) {
            paramMap.forEach((v, k) -> uri.append(v).append("=").append(k).append("&"));
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String responseStr = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class).getBody();
        return JSONObject.parseObject(responseStr, resultType);
    }


    public <T> T post(String url, Map<String, String> paramMap, Class<T> resultType) {
        HttpHeaders headers = new HttpHeaders();
        // 大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        paramMap.forEach(param::add);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(param, headers);
        // 执行HTTP请求
        String responseStr = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
        return JSONObject.parseObject(responseStr, resultType);
    }

    public String postForString(String url, Map<String, String> paramMap) {
        HttpHeaders headers = new HttpHeaders();
        // 大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        paramMap.forEach(param::add);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(param, headers);
        // 执行HTTP请求
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

}
