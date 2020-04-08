package com.mobile.security;

import com.mobile.redis.RedisUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        System.out.println("JwtUserDetailsService:" + user);
        boolean userexists = redisUtils.existsKey(user);
        if(true == userexists){
            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("ROLE_CAR_APIS"));
            String password = String.valueOf(redisUtils.get(user));
            return new SecurityUserDetails(user,password,authorityList);
        }else {
            throw new AuthenticationException("User not exists"){};
        }
    }
}
