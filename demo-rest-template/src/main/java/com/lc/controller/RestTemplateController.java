package com.lc.controller;

import com.alibaba.fastjson.JSONObject;
import com.lc.domain.Teacher;
import com.lc.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuci
 * @date 2017/12/11
 * @desc
 */
@RestController
public class RestTemplateController {

    private Logger log = LoggerFactory.getLogger(RestTemplateController.class);

    @Value("${test.url}")
    private String testUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/postByParameters")
    public String postByParameters(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("id", "1");
        map.add("name", "lc");
        map.add("desc", "lc desc");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        String url = testUrl + "/getUser1";
        log.info(url);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        log.info("status : " + response.getStatusCode());
        log.info("statusCodeValue : " + response.getStatusCodeValue());
        log.info("respMsg: " + response.getBody());
        log.info("heads's size: " + response.getHeaders().size());
        log.info("heads: " + response.getHeaders());

        log.info(response.toString());
        return "success";
    }

    @RequestMapping("/postByJson")
    public String postByJson(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject teacherJson = new JSONObject();
        teacherJson.put("id","1");
        teacherJson.put("name","1");
        teacherJson.put("desc","1");
        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(teacherJson, headers);

        String url = testUrl + "/getUsers";
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, entity, JSONObject.class);
        JSONObject user = responseEntity.getBody();
        log.info(user.toString());
        return "success";
    }

    @RequestMapping("/postByJson1")
    public String postByJson1(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Teacher teacher = new Teacher("1","lc001","test1");
        HttpEntity<Teacher> entity = new HttpEntity<Teacher>(teacher, headers);

        String url = testUrl + "/getUsers";
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, entity, User.class);
        User user = responseEntity.getBody();
        log.info(user.toString());
        return "success";
    }

    @RequestMapping("/postByObject")
    public String postByObject(){
        JSONObject teacherJson = new JSONObject();
        teacherJson.put("id","1");
        teacherJson.put("name","1");
        teacherJson.put("desc","1");

        String url = testUrl + "/getUsers";

        JSONObject user = restTemplate.postForObject(url,teacherJson,JSONObject.class);

        log.info(user.toJSONString());

        return "success";
    }

    @RequestMapping("/postByObject1")
    public String postByObject1(){
        Teacher teacher = new Teacher("1","lc001","test1");

        String url = testUrl + "/getUsers";

        User user = restTemplate.postForObject(url,teacher,User.class);

        log.info(user.toString());

        return "success";
    }



}
