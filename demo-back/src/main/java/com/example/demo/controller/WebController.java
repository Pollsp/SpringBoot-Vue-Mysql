package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能: 提供接口返回数据
 * 作者:Pollsp
 * 日期:2023/8/27 10:57
 */

@RestController
public class WebController {
    @Resource
    UserService userService;
    @GetMapping("/")
    public Result hello(){
        return Result.success("success");
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("数据输入不合法");

        }
        user =  userService.login(user);
        return Result.success(user);

    }

}







