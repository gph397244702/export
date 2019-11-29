package com.example.demothymeleaf.entity;

import lombok.Data;

/**
 * @author gaopanhong
 * @description: TODO
 * @since 2019-09-30
 */
@Data
public class User {

    private String name;

    private String sex;
    private int age;

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
