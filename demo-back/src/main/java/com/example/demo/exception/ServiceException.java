package com.example.demo.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能:
 * 作者:Pollsp
 * 日期:2023/8/27 18:09
 */
@Getter
public class ServiceException  extends RuntimeException{

    private String code;

    public ServiceException(String msg){
        super(msg);
    }
    public ServiceException(String code,String msg){
        super(msg);
        this.code = code;
    }
}
