package com.example.demothymeleaf.entity;

import lombok.Data;

/**
 * @author gaopanhong
 * @description: TODO
 * @since 2019-09-30
 */
@Data
public class EasyPOIModel {

    private String index;
    private String className;
    private User userInfo;

    public EasyPOIModel(String index, String className, User userInfo) {
        this.index = index;
        this.className = className;
        this.userInfo = userInfo;
    }
}
