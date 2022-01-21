package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UsersDao;

@Service
public class UsersService {
@Autowired
UsersDao usersDao;

public List<User> getUser() {
	List<User> userDate = usersDao.getUser();
	return userDate;
}

public String nameSpecific(String user) {
	String userName = usersDao.nameSpecific(user);
	return userName;
}



}
