package com.example.demo.domain.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.example.demo.domain.model.Todo;

public interface Todo_itemsDao {
	public int addTodo(Todo todo) throws DataAccessException;
	public List<Todo> getTodo() throws DataAccessException;
	public int deleteTodo(int id) throws DataAccessException;
	public int completeTodo(int id) throws DataAccessException;
	public List<Todo> searchTodo(String search) throws DataAccessException;
	public Todo getSingleTodo(int id) throws DataAccessException;
	public int settingEditTodo(Todo todo) throws DataAccessException;
}
