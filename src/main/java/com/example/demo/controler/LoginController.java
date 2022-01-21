package com.example.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.domain.model.LoginForm;

@Controller
public class LoginController {
@GetMapping("/login")
public String getLogin(LoginForm form,Model model) {
	return "login";
}
}
