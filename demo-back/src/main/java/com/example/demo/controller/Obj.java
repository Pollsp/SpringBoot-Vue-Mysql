package com.example.demo.controller;

import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能:
 * 作者:Pollsp
 * 日期:2023/8/27 11:54
 */
@RequestMapping("/user")
@RestController
public class Obj {
//    @Autowired
//    MongoTemplate mongoTemplate;
//    @GetMapping("/add")
//    void contextLLoads(){
//        Person person = new Person();
//        person.setId(1);
//        person.setName("test");
//        person.setAge(10);
//        mongoTemplate.save(person);
//
//    }
//    @GetMapping("/find")
//    public List<Person> find(){
//        List<Person> all = mongoTemplate.findAll(Person.class);
//        return all;
//    }
//

}
