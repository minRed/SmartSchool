package com.zhijia.smartschool.pojo;

import lombok.Data;

@Data
public class LoginForm {

    private String username;
    private String password;
    private Integer userType;
    private String verifiCode;
}
