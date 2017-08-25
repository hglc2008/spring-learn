package com.lc.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */
public class DefaultAccessDecisionManager extends AbstractAccessDecisionManager{

    public DefaultAccessDecisionManager(){

    }

    public DefaultAccessDecisionManager(List<AccessDecisionVoter> decisionVoters){
        super(decisionVoters);
    }

    /**
     *
     * @param authentication 用户及用户权限信息
     * @param object
     * @param configAttributes 访问资源需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//        User user = (User) authentication.getPrincipal();
//        System.out.println("访问资源的用户为：" + user.getUsername());
        System.out.println("=======" + authentication.getPrincipal());

        //如果访问资源不需要任何权限则直接通过
        if( configAttributes == null ) {
            return ;
        }

        System.out.println("authentication.getAuthorities's size " + authentication.getAuthorities().size());
        for(GrantedAuthority ga: authentication.getAuthorities()){
            System.out.println("url role : " + ga.getAuthority());
        }

        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        //遍历configAttributes看用户是否有访问资源的权限
        while( ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole = ca.getAttribute();
            System.out.println("needRole : " + needRole);

            //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for( GrantedAuthority ga: authentication.getAuthorities()){
                if(needRole.trim().equals(ga.getAuthority().trim())){

                    return;
                }
            }
        }

        throw new AccessDeniedException("");
    }
}
