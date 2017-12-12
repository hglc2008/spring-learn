package com.lc;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
*@author liuci
*@date 2017/12/12
*@desc http://blog.csdn.net/dounine/article/details/72953463
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoRestTemplateApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoRestTemplateApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {
		System.out.println("*************************8");
	}

    /**
     * GET请求测试
     * @throws Exception
     */
    @Test
    public void get() throws Exception {
        Map<String,String> multiValueMap = new HashMap<>();
        multiValueMap.put("username","lake");//传值，但要在url上配置相应的参数
        JSONObject result = testRestTemplate.getForObject("/test/get?username={username}",JSONObject.class,multiValueMap);
        System.out.println(result.toJSONString());
    }

    /**
     * POST请求测试
     * @throws Exception
     */
    @Test
    public void post() throws Exception {
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        JSONObject result = testRestTemplate.postForObject("/test/post",multiValueMap,JSONObject.class);
        System.out.println(result.toJSONString());
    }

    /**
     * 文件上传测试
     * @throws Exception
     */
    @Test
    public void upload() throws Exception {
        Resource resource = new FileSystemResource("/home/lake/github/wopi/build.gradle");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        multiValueMap.add("files",resource);
        JSONObject result = testRestTemplate.postForObject("/test/post",multiValueMap,JSONObject.class);
        System.out.println(result.toJSONString());
    }

    /**
     * 文件下载测试
     * @throws Exception
     */
    @Test
    public void download() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","xxxxxx");
        HttpEntity formEntity = new HttpEntity(headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<byte[]> response = testRestTemplate.exchange("/test/download?username={1}", HttpMethod.GET,formEntity,byte[].class,urlVariables);
        if (response.getStatusCode() == HttpStatus.OK) {
            //guava文件处理
            Files.write(response.getBody(),new File("/home/lake/github/file/test.gradle"));
        }
    }

    /**
     * 请求头信息传输测试
     * @throws Exception
     */
    @Test
    public void getHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","xxxxxx");
        HttpEntity formEntity = new HttpEntity(headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<JSONObject> result = testRestTemplate.exchange("/test/getHeader?username={username}", HttpMethod.GET,formEntity,JSONObject.class,urlVariables);
        System.out.println(result.getBody().toJSONString());
    }

    @Test
    public void putHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","xxxxxx");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        ResponseEntity<JSONObject> result = testRestTemplate.exchange("/test/putHeader", HttpMethod.PUT,formEntity,JSONObject.class);
        System.out.println(result.getBody().toJSONString());
    }

    @Test
    public void delete() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","xxxxx");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<JSONObject> result = testRestTemplate.exchange("/test/delete?username={username}", HttpMethod.DELETE,formEntity,JSONObject.class,urlVariables);
        System.out.println(result.getBody().toJSONString());
    }

}
