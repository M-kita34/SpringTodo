package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.domain.model.User;


public interface UsersDao {
	List<User> getUser() throws DataAccessException;
	String nameSpecific(String user) throws DataAccessException;
}
