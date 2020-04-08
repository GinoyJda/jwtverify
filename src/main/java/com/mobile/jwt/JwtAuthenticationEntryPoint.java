package com.mobile.jwt;

import com.mobile.security.JsonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
//
//        System.out.println("JwtAuthenticationEntryPoint:"+authException.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有凭证");
//

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(JsonResult.failed(10001,"Token凭证错误"));
    }
}

