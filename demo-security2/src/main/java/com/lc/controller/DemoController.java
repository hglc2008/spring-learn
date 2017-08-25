package com.lc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/24.
 */
@Controller
public class DemoController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserCache userCache;

    @RequestMapping("/test")
    public String test(){
        String msg = messageSource.getMessage("UserDetails.isLocker.1",null,null);
        System.out.println(msg);

        UserDetails userDetails = userCache.getUserFromCache("cacheTest");
        System.out.println(userDetails.getAuthorities());

        return "test";
    }
}
