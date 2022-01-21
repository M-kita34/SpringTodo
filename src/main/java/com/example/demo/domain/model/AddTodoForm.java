package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AddTodoForm {
@NotBlank
@Length(max=100)
private String item_name;
private int user;
@DateTimeFormat(pattern ="yyyy-MM-dd")
@NotNull
private Date limit;
private int complete;
}
