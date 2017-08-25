package com.lc.tool;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Created by Administrator on 2017/8/23.
 */
public class AntUrlPathMatcher implements UrlMatcher{

    private boolean requiresLowerCaseUrl;

    private PathMatcher pathMatcher;

    public AntUrlPathMatcher(){
        this(true);
    }

    public AntUrlPathMatcher(Boolean requiresLowerCaseUrl){
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
        pathMatcher = new AntPathMatcher();

    }

    @Override
    public Object compile(String path) {
        if (this.requiresLowerCaseUrl) {
            return path.toLowerCase();
        }
        return path;
    }

    @Override
    public boolean pathMatchesUrl(Object path, String url) {
        if (("/**".equals(path)) || ("**".equals(path))) {
            return true;
        }

        return this.pathMatcher.match((String)path, url);
    }

    @Override
    public String getUniversalMatchPattern() {
        return"/**";
    }

    @Override
    public boolean requiresLowerCaseUrl() {

        return this.requiresLowerCaseUrl;
    }

    public boolean isRequiresLowerCaseUrl() {
        return requiresLowerCaseUrl;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public String toString() {
        return super.getClass().getName() + "[requiresLowerCase='"
                + this.requiresLowerCaseUrl + "']";
    }
}
