package com.lc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuci
 * @date 2017/12/12
 * @desc
 */
@RestController
public class MockController {

    @RequestMapping("/testMock")
    public String testMock(){

        return "success";
    }
}
