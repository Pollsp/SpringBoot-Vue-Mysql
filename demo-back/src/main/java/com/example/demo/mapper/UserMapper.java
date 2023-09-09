package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


//    @Insert("insert into `user`(username,password,name,phone,email,address,avatar)" +
//            " values(#{username},#{password},#{name},#{phone},#{email},#{address},#{avatar})")
//    void insert(User user);
//
//    @Update("update `user` set username = #{username},password = #{password},name = #{name},phone = #{phone}," +
//            "email = #{email},address = #{address},avatar = #{avatar} where id = #{id}")
//    void updateUser(User user);
//    @Delete("delete from `user` where id = #{id}")
//    void deleteUser(Integer id);
//    @Select("select * from `user` order by id desc")
//    List<User> selectAll();
//
//    @Select("select * from `user` where username like concat('%',#{username},'%') and name like ('%',#{name},'%') order by" +
//            "id desc limite #{skipnum},#{pageSize}")
//    List<User> selectByPage(@Param("skipnum") Integer skipnum,@Param("pageSize") Integer pageSize,@Param("username") String username,
//                            @Param("name") String name);
//    @Select("select count(id) from `user` where username like concat('%',#{username},'%') and name like ('%',#{name},'%') order by" +
//            "id desc ")
//    List<User> selectCountByPage(@Param("username") String username,@Param("name") String name);
//    @Select("select * from `user` where username = #{username}")
//    User selectByUsername(String username);
//
//
//    @Select("select * from `user` where id = #{userid}")
//    User selectByUserid(Integer userid);





}
