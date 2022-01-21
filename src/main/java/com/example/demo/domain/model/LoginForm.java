package com.example.demo.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {
@NotBlank
private String name;
private String password;
}
