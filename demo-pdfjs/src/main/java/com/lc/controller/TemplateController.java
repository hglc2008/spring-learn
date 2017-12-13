package com.lc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * @author liuci
 * @date 2017/12/12
 * @desc
 */
@Controller
public class TemplateController {

    private Logger logger = LoggerFactory.getLogger(TemplateController.class);

    /**
     * 测试hello
     * @return
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "hello";
    }


    /**
     * 预览pdf文件
     * @param fileName
     */
    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public void pdfStreamHandler(String fileName,HttpServletRequest request,HttpServletResponse response) {

        File file = new File("D:/temp/"+fileName);
        if (file.exists()){
            byte[] data = null;
            try {
                FileInputStream input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
                input.close();
            } catch (Exception e) {
                logger.error("pdf文件处理异常：" + e.getMessage());
            }

        }else{
            return;
        }
    }
}
