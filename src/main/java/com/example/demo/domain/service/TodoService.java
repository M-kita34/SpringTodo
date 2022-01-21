package com.example.demo.domain.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.model.Todo;
import com.example.demo.domain.repository.Todo_itemsDao;

@Service
public class TodoService {
@Autowired
Todo_itemsDao todo_itemsDao;
public int addTodo(Todo todo) {
	int addNumber = todo_itemsDao.addTodo(todo);
	return addNumber;
}
public List<Todo> getTodo() {
	List<Todo> todoList = todo_itemsDao.getTodo();
	return todoList;
}
public int deleteTodo(int id) {
	int number = todo_itemsDao.deleteTodo(id);
	return number;
}
public int completeTodo(int id) {
	int number = todo_itemsDao.completeTodo(id);
	return number;
}
public List<Todo> searchTodo(String search) {
	List<Todo> searchTodo = todo_itemsDao.searchTodo(search);
	return searchTodo;

}
public List<Todo> getSingleTodo(int id) {
	List<Todo> singleTodo = todo_itemsDao.getSingleTodo(id);
	return singleTodo;
}
public int settingEditTodo(Todo todo) {
	int number = todo_itemsDao.settingEditTodo(todo);
	return number;
}
}
