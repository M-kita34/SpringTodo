package com.example.demo.domain.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Todo {
private int id;
private int user_id;
private String family_name;
private String first_name;
private String item_name;
@DateTimeFormat(pattern ="yyyy-MM-dd")
private Date registration_date;
@DateTimeFormat(pattern ="yyyy-MM-dd")
@NotBlank
private Date expire_date;
@DateTimeFormat(pattern ="yyyy-MM-dd")
private Date finished_date;
private int is_deleted;

public boolean isExpired() {
	Date today = new Date();
	if(this.expire_date.before(today)) {
		return true;
	}else {
		return false;
	}
}

public Todo(AddTodoForm form) {
		Date today = new Date();
		this.item_name = form.getItem_name();
		this.user_id = (Integer)form.getUser();
		this.expire_date = form.getLimit();
		if(form.getComplete()==1) {
			this.finished_date = today ;
		}else {
			this.finished_date = null ;
		}
		this.is_deleted = 0;
		this.registration_date = today;
		this.first_name = null;
		this.family_name = null;
	}
public Todo(EditTodoForm form) {
	Date today = new Date();
	this.id = form.getId();
	this.item_name = form.getItem_name();
	this.user_id = (Integer)form.getUser();
	this.expire_date = form.getExpire_date();
	if(form.getIs_deleted()==1) {
		this.finished_date = today ;
	}else {
		this.finished_date = null ;
	}
	this.is_deleted = 0 ;
	this.registration_date = today;
	this.first_name = null;
	this.family_name = null;
}
public Todo() {
}
}
