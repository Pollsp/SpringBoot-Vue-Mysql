package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能:
 * 作者:Pollsp
 * 日期:2023/8/27 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    public static final String COOE_SUCCESS = "200";
    public static final String COOE_AUTH_ERROR = "401";
    public static final String COOE_SYS_ERROR = "500";

    /**        code
     * 请求的返回编码 200 401 404 500
     * 编码表示请求还是失败
     * 或者 可以看出失败的类型是什么
     */
    private String code;
    /**       msg
     * 表示错误的详细信息
     */
    private String msg;
    /**
     * 数据从什么地方返回出去
     * 就是这个data
     * user     Object类型就是User
     * list     Object类型就是List
     * map      Object类型就是Map
     *
     */
    private Object data;




    public static Result success(){
        return new Result(COOE_SUCCESS, "请求成功", null);
    }
    public static Result success(Object data){
        return new Result(COOE_SUCCESS, "请求成功", data);
    }

    public static Result error(String msg){
        return new Result(COOE_SYS_ERROR,msg,null);
    }
    public static  Result error(String code,String msg){
        return new Result(code,msg,null);
    }
    public static Result error(){
        return new Result(COOE_SYS_ERROR,"系统错误",null);
    }


}
