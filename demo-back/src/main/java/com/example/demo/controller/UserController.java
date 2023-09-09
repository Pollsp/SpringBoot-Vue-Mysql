package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 功能:
 * 作者:Pollsp
 * 日期:2023/8/27 13:41
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     *  新增用户信息
     */
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        try{
            userService.save(user);
        }catch (Exception e){
            if (e instanceof DuplicateKeyException){
                return Result.error("插入数据库错误");
            }else {
                return  Result.error("系统错误");
            }

        }
        return Result.success();
    }
    /**
     *  修改用户信息
     */
    @PutMapping("/updata")
    public Result update(@RequestBody User user){

        try{
            userService.updateById(user);
        }catch (Exception e){
            if (e instanceof DuplicateKeyException){
                return Result.error("请重写数据");
            }else {
                return  Result.error("系统错误");
            }

        }

        return Result.success();
    }

    /**
     *  通过id
     *  删除用户信息
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.removeById(id);
        return Result.success();
    }

    /**
     *  批量
     *  删除用户信息
     */

    @DeleteMapping("/delete/batch")
    public Result batchdelete(@RequestBody List<Integer> ids){
        userService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     *  查询全部用户信息
     *
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
//        List<User> u = new ArrayList<>();
//        u = userService.selectAll();
        List<User> users = userService.list(new QueryWrapper<User>().orderByDesc("id"));
        return Result.success(users);
    }



    /**
     *  查询全部用户信息
     *  pageNum 当前页码
     *
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String name){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().orderByDesc("id");
        queryWrapper.like(StrUtil.isNotBlank(username),"username",username);
        queryWrapper.like(StrUtil.isNotBlank(name),"name",name);

        Page<User> page = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }


    /**
     *  查询
     *
     */



}
