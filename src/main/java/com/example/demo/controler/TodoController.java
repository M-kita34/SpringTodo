package com.example.demo.controler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.AddTodoForm;
import com.example.demo.domain.model.EditTodoForm;
import com.example.demo.domain.model.Todo;
import com.example.demo.domain.model.User;
import com.example.demo.domain.service.TodoService;
import com.example.demo.domain.service.UsersService;


@Controller
public class TodoController {
@Autowired
TodoService todoService;
@Autowired
UsersService usersService;

@GetMapping("/todoList")
public String getTodoList(Model model,@AuthenticationPrincipal UserDetails userDetail,
						   @RequestParam(name = "search",required = false)String search) {
	String user = userDetail.getUsername();
	String familyName = usersService.nameSpecific(user);
	model.addAttribute("loginName",familyName);
	if(search == "" || search == null) {
		List<Todo> todoList = todoService.getTodo();
		model.addAttribute("todoList",todoList);
	}else {
		List<Todo> searchTodo = todoService.searchTodo(search);
		model.addAttribute("todoList",searchTodo);
	}
	return "todoList";
}

@GetMapping("/addTodo")
public String getAddTodo(@ModelAttribute AddTodoForm form,Model model) {
	model.addAttribute(form);
	List<User> userDate = usersService.getUser();
	model.addAttribute("userDate",userDate);
	return "addTodo";
}

@PostMapping("/addTodo")
public String postAddTodo(@ModelAttribute @Validated AddTodoForm form,
						   BindingResult bindingResult,Model model) {
	if(bindingResult.hasErrors() ) {
		return getAddTodo(form,model);
	}
	Todo todo = new Todo(form);
	todoService.addTodo(todo);
	return "redirect:/todoList";
}

@PostMapping("/complete")
public String postComplate(@RequestParam("id") int id,Model model) {
	int number = todoService.completeTodo(id);
	if(number == 1){
		return "redirect:/todoList";
	}else {
		return "error";
	}
}

@PostMapping("/notComplete")
public String postNotComplate(@RequestParam("id") int id,Model model) {
	int number = todoService.notCompleteTodo(id);
	if(number == 1){
		return "redirect:/todoList";
	}else {
		return "error";
	}
}

@PostMapping("/edit")
public String postEdit(@RequestParam("id") int id, @ModelAttribute EditTodoForm form,Model model) {
	Todo singleTodo = todoService.getSingleTodo(id);
		form.setId(id);
		form.setUser(singleTodo.getUser_id());
		form.setItem_name(singleTodo.getItem_name());
		form.setExpire_date(singleTodo.getExpire_date());
		form.setFinished_date(singleTodo.getFinished_date());
		if(singleTodo.getFinished_date() != null) {
			form.setEnd(1);
		}
	model.addAttribute("editTodoForm",form);
	List<User> userDate = usersService.getUser();
	model.addAttribute("userDate",userDate);
	return "edit";
}

@PostMapping("/editTodo")
public String postEditTodo(@ModelAttribute @Validated EditTodoForm form,
		   BindingResult bindingResult,Model model) {
	if(bindingResult.hasErrors()){
		return postEdit(form.getId(),form,model);
	}
	Todo todo = new Todo(form);
	int number = todoService.settingEditTodo(todo);
	if(number == 1){
		return "redirect:/todoList";
	}else {
		return "error";
	}

}

@PostMapping("/delete")
public String postDelete(@RequestParam("id")int id,Model model) {
	Todo singleTodo = todoService.getSingleTodo(id);
	model.addAttribute("deleteTodo",singleTodo);
	model.addAttribute("deleteId",id);
	return "/delete";
}

@PostMapping("/deleteTodo")
public String PostDelete(@RequestParam("id")int id,Model model) {
	int number = todoService.deleteTodo(id);
	if(number == 1){
		return "redirect:/todoList";
	}else {
		return "error";
	}
}
}