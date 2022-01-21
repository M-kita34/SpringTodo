package com.example.demo.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UsersDao;

@Repository
public class UsersDaojdbcImpl implements UsersDao{
@Autowired
JdbcTemplate jdbc;

@Override
public List<User> getUser() {
	ArrayList<User> userList = new ArrayList<>();

	List<Map<String,Object>> getUser = jdbc.queryForList("SELECT id,user,pass,Family_name,First_name,"
			+ "is_admin FROM users");
	for(Map<String,Object> recode : getUser) {
		User userDate = new User();
		int id = (Integer)recode.get("id");
			userDate.setId(id);
		String nickName = (String)recode.get("user");
			userDate.setNickName(nickName);
    	String pass = (String)recode.get("pass");
    		userDate.setPass(pass);
		String Family_name = (String)recode.get("Family_name");
			userDate.setFamily_name(Family_name);
		String First_name = (String)recode.get("First_name");
			userDate.setFirst_name(First_name);

		userList.add(userDate);
 	}
	return userList;
}

@Override
public String nameSpecific(String user) throws DataAccessException {
	Map<String, Object> map  = jdbc.queryForMap("SELECT Family_name FROM users WHERE user = ? " , user);
	String specificedName = (String)map.get("Family_name");
	return specificedName ;
}
}
