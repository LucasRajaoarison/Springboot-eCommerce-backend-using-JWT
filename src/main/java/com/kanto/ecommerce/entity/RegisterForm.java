package com.kanto.ecommerce.entity;

import lombok.Data;

@Data
public class RegisterForm {
    private String username;
    private String email;
    private String password;
    private String repassword;
}
