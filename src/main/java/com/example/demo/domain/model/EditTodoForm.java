package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class EditTodoForm {
private int id;
private int user;
@NotBlank
private String item_name;
@DateTimeFormat(pattern ="yyyy-MM-dd")
private Date expire_date;
private int is_deleted;

}
