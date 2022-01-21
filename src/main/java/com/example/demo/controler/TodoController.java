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
	List<Todo> todoList = todoService.getTodo();
	model.addAttribute("todoList",todoList);
	return "todoList";
}

@PostMapping("/todoList")
public String postTodoList(Model model,@AuthenticationPrincipal UserDetails userDetail,
						   @RequestParam(name = "search",required = false)String search) {
	if(search == "") {
		return "redirect:/todoList";
	}
	model.addAttribute("search",search);
	List<Todo> searchTodo = todoService.searchTodo(search);
	model.addAttribute("searchedTodo",searchTodo);
	return "searchedTodo";
}

@GetMapping("/addTodo")
public String GetAddTodo(@ModelAttribute AddTodoForm form,Model model,
		@RequestParam(name = "search",required = false)String search) {
	if(search != "") {
		model.addAttribute("search",search);
	}
	model.addAttribute(form);
	List<User> userDate = usersService.getUser();
	model.addAttribute("userDate",userDate);
	return "addTodo";
}

@PostMapping("/addTodo")
public String PostAddTodo(@ModelAttribute @Validated AddTodoForm form,
						   BindingResult bindingResult,Model model,
						   @RequestParam(name = "search",required = false)String search) {
	if(bindingResult.hasErrors() ) {
		return "redirect:/error";
	}
	Todo todo = new Todo(form);
	todoService.addTodo(todo);
	if(search != "") {
		model.addAttribute("search",search);
		List<Todo> searchTodo = todoService.searchTodo(search);
		model.addAttribute("searchedTodo",searchTodo);
		return "searchedTodo";
	}else {
		return "redirect:/todoList";
	}
}

@PostMapping("/complete")
public String postComplate(@RequestParam("id") int id,@RequestParam(name = "search",required = false)String search,Model model) {
	int number = todoService.completeTodo(id);
	if(number == 1 && search != null){
		model.addAttribute("search",search);
		List<Todo> searchTodo = todoService.searchTodo(search);
		model.addAttribute("searchedTodo",searchTodo);
		return "searchedTodo";
	}else if(number == 1){
		return "redirect:/todoList";
	}else {
		return "error";
	}
}

@PostMapping("/edit")
public String postEdit(@RequestParam("id") int id,Model model ,
						@RequestParam(name = "search",required = false)String search,
						@ModelAttribute EditTodoForm form) {
	if(search != null) {
		model.addAttribute("search",search);
	}
	List<Todo> editTodo = todoService.getSingleTodo(id);
	model.addAttribute("editId",id);
	model.addAttribute("editTodo",editTodo);
	List<User> userDate = usersService.getUser();
	model.addAttribute("userDate",userDate);
	model.addAttribute(form);
	return "edit";
}

@PostMapping("/editTodo")
public String postEditTodo(@ModelAttribute @Validated EditTodoForm form,
		   BindingResult bindingResult,Model model,@RequestParam(name = "search",required = false)String search) {
	if(bindingResult.hasErrors() ) {
		return "redirect:/error";
	}
	Todo todo = new Todo(form);
	int number = todoService.settingEditTodo(todo);
	if(number == 1 && search != "") {
		model.addAttribute("search",search);
		List<Todo> searchTodo = todoService.searchTodo(search);
		model.addAttribute("searchedTodo",searchTodo);
		return "searchedTodo";
	}else if(number == 1){
		return "redirect:/todoList";
	}else {
		return "error";
	}

}

@PostMapping("/delete")
public String postDelete(@RequestParam("id")int id,@RequestParam(name = "search",required = false)String search,Model model) {
	List<Todo> deleteTodo = todoService.getSingleTodo(id);
	model.addAttribute("deleteTodo",deleteTodo);
	model.addAttribute("deleteId",id);
	if(search != null) {
		model.addAttribute("search",search);
	}
	return "/delete";
}

@PostMapping("/deleteTodo")
public String PostDelete(@RequestParam("id")int id,@RequestParam(name = "search",required = false)String search,Model model) {
	int number = todoService.deleteTodo(id);
	if(number == 1 && search != ""){
		model.addAttribute("search",search);
		List<Todo> searchTodo = todoService.searchTodo(search);
		model.addAttribute("searchedTodo",searchTodo);
		return "searchedTodo";
	}else if(number == 1){
		return "redirect:/todoList";
	}else {
		return "error";
	}
}

@PostMapping("/searchTodo")
public String PostSearchTodo(@RequestParam("search")String search,Model model) {
	if(search == "") {
		return "redirect:/todoList";
	}
	model.addAttribute("search",search);
	List<Todo> searchTodo = todoService.searchTodo(search);
	model.addAttribute("searchedTodo",searchTodo);
	return "searchedTodo";
}
}