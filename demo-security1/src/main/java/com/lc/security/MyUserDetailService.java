package com.lc.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2017/8/23.
 */
public class MyUserDetailService implements UserDetailsService {

    //登陆验证时，通过username获取用户的所有权限信息，
    //并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("MyUserDetailService's loadUserByUsername");
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();

        GrantedAuthorityImpl auth2=new GrantedAuthorityImpl("ROLE_ADMIN");
        GrantedAuthorityImpl auth1=new GrantedAuthorityImpl("ROLE_USER");

        if(username.equals("lcy")){
            auths=new ArrayList<GrantedAuthority>();
            auths.add(auth1);
            auths.add(auth2);
        }

        User user = new User(username, "123", true, true, true, true, auths);
        return user;
    }
}
