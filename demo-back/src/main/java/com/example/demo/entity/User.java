package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;


/**
 * 功能:
 * 作者:Pollsp
 * 日期:2023/8/27 13:32
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;
    private String role;

    @TableField(exist = false)
    private String token;


}
