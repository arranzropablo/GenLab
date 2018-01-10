package com.genlab.serverapplication.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.userService.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/loginPage", method=RequestMethod.GET)
	public String getLoginList(@RequestParam(required=false, name="error")String error,@RequestParam(required=false, name="registrado")String registrado,HttpServletRequest request, Model model,HttpSession session,Principal principal) {
		if(error != null) {
			model.addAttribute("msgError", "Error al introducir las credenciales");
		}
		if(registrado != null) {
			model.addAttribute("msgRegister", "Registrado con éxito");
		}
		return "loginPage";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String registerPage(HttpServletRequest request, Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/registerUser", method=RequestMethod.POST)
	public String registerPage(@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("repassword") String repassword,HttpServletRequest request, Model model, HttpSession session) {
		if(password.equals(repassword)) {
			User u = User.builder().email(email).password(passwordEncoder.encode(password)).roles("USER").build();
			if(service.getUser(email)==null) {
				service.addUser(u);
				session.setAttribute("user", u);
			}
		}
		return "redirect:/loginPage?registrado";
	}
}
