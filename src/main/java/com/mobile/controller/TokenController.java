package com.mobile.controller;

import com.mobile.jwt.JwtTokenUtil;
import com.mobile.redis.RedisUtils;
import com.mobile.security.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TokenController {

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/getToken")
    public String getToken(@RequestBody SysUser sysUser){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getUsername());
        String passwd = sysUser.getPassword();
        boolean key = new BCryptPasswordEncoder().matches(passwd,userDetails.getPassword());
        if(true == key){
            boolean ex = redisUtils.existsKey(sysUser.getUsername()+"_token");
            if(true == ex){
                return String.valueOf(redisUtils.get(sysUser.getUsername()+"_token"));
            }else{
                String token = jwtTokenUtil.generateToken(userDetails);
                redisUtils.setKeyTimeOut(sysUser.getUsername()+"_token",token);
                return token;
            }

        }else {
            return "getToken failed !! wrong password";
        }
    }

}
