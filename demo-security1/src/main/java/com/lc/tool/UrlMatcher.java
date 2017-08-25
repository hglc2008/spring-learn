package com.lc.tool;

/**
 * Created by Administrator on 2017/8/23.
 */
public interface UrlMatcher {

    Object compile(String paramString);

    boolean pathMatchesUrl(Object paramObject, String paramString);

    String getUniversalMatchPattern();

    boolean requiresLowerCaseUrl();
}
