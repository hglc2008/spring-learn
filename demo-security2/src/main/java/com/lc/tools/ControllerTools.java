package com.lc.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/25.
 */
public class ControllerTools {

    public static boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;
        return isAjax;
    }
}
