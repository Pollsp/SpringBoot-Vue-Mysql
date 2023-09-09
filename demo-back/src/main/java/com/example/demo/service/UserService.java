package com.example.demo.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能:
 * 作者:Pollsp
 * 日期:2023/8/27 13:40
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    @Autowired
    UserMapper userMapper;


    @Override
    public boolean save(User entity) {
            entity.setId(null);
        if (StrUtil.isBlank(entity.getName())){
            entity.setName(entity.getUsername());
        }
        if (StrUtil.isBlank(entity.getPassword())){
            entity.setPassword("123");
        }
        if (StrUtil.isBlank(entity.getRole())){
            entity.setRole("用户");
        }
        //
        return super.save(entity);
    }

    //    public void insertUser(User user){
//        userMapper.insert(user);
//
//    }
//
//    public void updateUser(User user) {
//        userMapper.updateUser(user);
//    }
//
//    public void deleteUser(Integer id) {
//        userMapper.deleteUser(id);
//    }
//
//    public void batchdeleteUser(List<Integer> ids) {
//        for (Integer id : ids){
//            userMapper.deleteUser(id);
//        }
//
//    }
//
//    public List<User> selectAll() {
//        return userMapper.selectAll();
//    }
//
//    public Map<String, Object>  selectByPage(Integer pageNum,Integer pageSize, String username, String name) {
//        Integer skipnum = (pageNum - 1) * pageSize; //计算出来
//        Map<String, Object> result = new HashMap<>();
//        List<User> users1 = userMapper.selectByPage(skipnum, pageSize, username, name);
//        List<User> total = userMapper.selectCountByPage(username, name);
//        result.put("list",users1);
//        result.put("total",total);
//
//        return result;
//    }
    //验证用户账户是否合法
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
       User dbUser  = userMapper.selectOne(queryWrapper);
       if (dbUser == null){
           //抛出一个自定义的异常
           throw new ServiceException("用户名或密码错误");
       }
       if (!user.getPassword().equals(dbUser.getPassword())){
           throw new ServiceException("用户名或密码错误");
       }
       //生成token
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }
}
