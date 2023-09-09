package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Component;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 功能:
 * 作者:Pollsp
 * 日期:2023/8/29 10:50
 */
@Component
public class TokenUtils {

    private static UserMapper staticUserMapper;
    @Resource
    UserMapper userMapper;
    @PostConstruct
    public void setUserMapper(){
        staticUserMapper = userMapper;
    }
    /**
     * 生成token
     * @return
     */
    public static String createToken(String userId,String sign){
        return JWT.create().withAudience(userId)//
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign));
    }

    /**
    * 获取当前登录的用户信息
     * @return user对象
    */

    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.valueOf(userId));
            }

        }catch (Exception e){
            return null;
        }
        return null;
    }
















}
