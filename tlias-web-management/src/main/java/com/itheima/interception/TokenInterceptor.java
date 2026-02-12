package com.itheima.interception;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component  //交给ioc容器管理
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求url.
        String requestURI = request.getRequestURI(); //URI是不带前边协议的路径 格式形如 employee/login
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if (requestURI.contains("/login")) {
            //是登陆操作，放行
            log.info("登录请求，放行");
            return true;  //放行
        }
        //3.获取请求头中的令牌(token)。
        String token = request.getHeader("token");
        //4。判断令牌是否存在，如果不存在，响应401。
        if (token == null || token.isEmpty()) {//令牌不存在或为空串
            log.info("令牌不存在");
            response.setStatus(401);
            return false;
        }
        //5.解析token，如果解析失败，响应401。
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            //解析失败响应401
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return false;
        }
        //6.放行
        log.info("令牌合法，放行");
        return true;
    }

}
