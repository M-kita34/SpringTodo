package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class EditTodoForm {
private int id;
private int user;
@NotBlank
@Length(max=50)
private String item_name;
@DateTimeFormat(pattern ="yyyy-MM-dd")
private Date expire_date;
@DateTimeFormat(pattern ="yyyy-MM-dd")
private Date finished_date;
private int end = 0;
}
