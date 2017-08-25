package com.lc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2017/8/24.
 */
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private MessageSource messageSource;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("UserDetailServiceImpl's loadUserByUsername ： " + userName);

        //TODO
        //根据用户名从数据库中获取用户信息

        String password = "password";

        if ("liuci001".equals(userName)){
            throw new UsernameNotFoundException(this.messageSource.getMessage(
                    "UserDetails.userNotFount", new Object[]{userName}, null));
        }

        //TODO
        //从数据库中获取用户权限信息
        Collection<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority au1 = new GrantedAuthorityImpl("ROLE_SERVICE");
        autthorities.add(au1);
        GrantedAuthority au2 = new GrantedAuthorityImpl("ROLE_UPDATE");
        autthorities.add(au2);

        UserDetails user =
                new User(userName,password,true,true,
                        true,true,autthorities);

        return user;
    }
}
