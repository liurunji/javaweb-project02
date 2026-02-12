package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")   //拦截的是所有请求
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //1.获取请求url.
        String requestURI = httpServletRequest.getRequestURI(); //URI是不带前边协议的路径 格式形如 employee/login
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if (requestURI.contains("/login")) {
            //是登陆操作，放行
            log.info("登录请求，放行");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //3.获取请求头中的令牌(token)。
        String token = httpServletRequest.getHeader("token");
        //4。判断令牌是否存在，如果不存在，响应401。
        if (token == null || token.isEmpty()) {//令牌不存在或为空串
            log.info("令牌不存在");
            httpServletResponse.setStatus(401);
            return;
        }
        //5.解析token，如果解析失败，响应401。
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            //解析失败响应401
            log.info("令牌非法，响应401");
            httpServletResponse.setStatus(401);
            return;
        }
        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
