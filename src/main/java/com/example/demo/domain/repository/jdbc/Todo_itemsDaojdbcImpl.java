package com.example.demo.domain.repository.jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.model.Todo;
import com.example.demo.domain.repository.Todo_itemsDao;

@Repository
public class Todo_itemsDaojdbcImpl implements Todo_itemsDao{
@Autowired
JdbcTemplate jdbc;

@Autowired
PasswordEncoder passwordEncoder;

	@Override
	public int addTodo(Todo todo) throws DataAccessException {
		int addNumber = jdbc.update("INSERT INTO todo_items(User_id,Item_name,Registration_date,expire_date,finished_date,is_deleted) "
				+ " VALUES(?,?,?,?,?,?)"
				,todo.getUser_id()
				,todo.getItem_name()
				,todo.getRegistration_date()
				,todo.getExpire_date()
				,todo.getFinished_date()
				,todo.getIs_deleted());
		return addNumber;
	}

	@Override
	public List<Todo> getTodo() {
		List<Map<String,Object>> getTodo = jdbc.queryForList("SELECT * FROM todo_items inner join users on todo_items.user_id = users.id "
				+ "WHERE is_deleted = 0 ORDER BY expire_date;");
		ArrayList<Todo> todoList = new ArrayList<>();
		for(Map<String,Object> recode : getTodo) {
			Todo todoDate = new Todo();
			int id = (Integer)recode.get("id");
				todoDate.setId(id);
			int user_id = (Integer)recode.get("user_id");
				todoDate.setUser_id(user_id);
			String item_name = (String)recode.get("item_name");
				todoDate.setItem_name(item_name);
			String fullname = (String)recode.get("Family_name") + " " + (String)recode.get("First_name");
				todoDate.setFullname(fullname);
	    	Date registration_date = (Date)recode.get("registration_date");
	    		todoDate.setRegistration_date(registration_date);
	    	Date expire_date = (Date)recode.get("expire_Date");
	    		todoDate.setExpire_date(expire_date);
			Date finished_date = (Date)recode.get("finished_date");
				todoDate.setFinished_date(finished_date);
			int is_deleted = (Integer)recode.get("is_deleted");
				todoDate.setIs_deleted(is_deleted);

			todoList.add(todoDate);
	 	}
		return todoList;
	}

	@Override
	public int deleteTodo(int id) {
		int number = jdbc.update("UPDATE  todo_items SET is_deleted = 1 WHERE id = ?" , id);
		return number;
	}

	@Override
	public int completeTodo(int id) throws DataAccessException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date today = cal.getTime();
			try {
				sdf.parse(sdf.format(today));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		int number = jdbc.update("UPDATE todo_items SET finished_date = ? WHERE id =?" , today,id);
		return number;
	}

	@Override
	public List<Todo> searchTodo(String search) throws DataAccessException {
		List<Map<String,Object>> getTodo = jdbc.queryForList("SELECT * FROM todo_items inner join users on todo_items.user_id = users.id "
				+ "WHERE item_name LIKE '%" + search + "%' "
					+ "OR Family_name LIKE '%" + search + "%' "
					+ "OR First_name LIKE '%" + search + "%'"
					+ "ORDER BY expire_date;");
		ArrayList<Todo> todoList = new ArrayList<>();
		for(Map<String,Object> recode : getTodo) {
			int finished = (Integer)recode.get("is_deleted");
			if(finished == 0) {
				Todo todoDate = new Todo();
				int id = (Integer)recode.get("id");
					todoDate.setId(id);
				int user_id = (Integer)recode.get("user_id");
					todoDate.setUser_id(user_id);
				String item_name = (String)recode.get("item_name");
					todoDate.setItem_name(item_name);
				String fullname = (String)recode.get("Family_name") + " " + (String)recode.get("First_name");
					todoDate.setFullname(fullname);
		    	Date registration_date = (Date)recode.get("registration_date");
		    		todoDate.setRegistration_date(registration_date);
		    	Date expire_date = (Date)recode.get("expire_Date");
		    		todoDate.setExpire_date(expire_date);
				Date finished_date = (Date)recode.get("finished_date");
					todoDate.setFinished_date(finished_date);
				int is_deleted = (Integer)recode.get("is_deleted");
					todoDate.setIs_deleted(is_deleted);

				todoList.add(todoDate);
			}else {
				continue ;
			}

	 	}
		return todoList;
	}

	@Override
	public List<Todo> getSingleTodo(int editId) throws DataAccessException {
		List<Map<String,Object>> getEditTodo = jdbc.queryForList("SELECT * FROM todo_items inner join users on todo_items.user_id = users.id "
			+ "WHERE todo_items.id LIKE " + editId + " ;");
		ArrayList<Todo> todoList = new ArrayList<>();
		for(Map<String,Object> recode : getEditTodo) {
		Todo todoDate = new Todo();
		int id = (Integer)recode.get("id");
			todoDate.setId(id);
		int user_id = (Integer)recode.get("user_id");
			todoDate.setUser_id(user_id);
		String item_name = (String)recode.get("item_name");
			todoDate.setItem_name(item_name);
		String fullname = (String)recode.get("Family_name") + " " + (String)recode.get("First_name");
			todoDate.setFullname(fullname);
    	Date registration_date = (Date)recode.get("registration_date");
    		todoDate.setRegistration_date(registration_date);
    	Date expire_date = (Date)recode.get("expire_Date");
    		todoDate.setExpire_date(expire_date);
		Date finished_date = (Date)recode.get("finished_date");
			todoDate.setFinished_date(finished_date);
		int is_deleted = (Integer)recode.get("is_deleted");
			todoDate.setIs_deleted(is_deleted);

		todoList.add(todoDate);
 	}
	return todoList;
	}

	@Override
	public int settingEditTodo(Todo todo) throws DataAccessException {
		int number = jdbc.update("UPDATE todo_items SET User_id = ? ,"
				+ "Item_name = ? , Registration_date = ? , Finished_date = ? ,"
				+ "Is_deleted = ? , Expire_date = ? WHERE Id = ? "
				 ,todo.getUser_id()
				 ,todo.getItem_name()
				 ,todo.getRegistration_date()
				 ,todo.getFinished_date()
				 ,todo.getIs_deleted()
				 ,todo.getExpire_date()
				 ,todo.getId());
		return number;
	}
}
