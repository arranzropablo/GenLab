package com.genlab.serverapplication.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genlab.serverapplication.services.userService.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String getLoginList(@RequestParam(required=false, name="error")String error, HttpServletRequest request, Model model,HttpSession session,Principal principal) {
	    if(error != null) {
			model.addAttribute("msgError", "Error al introducir las credenciales");
		}
		return "login";
	}
	
	/*@PostMapping("/register")
	public String registerPage(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("repassword") String repassword, HttpSession session, RedirectAttributes redirect) {
		if(password.equals(repassword)) {
			User u = User.builder().email(email).password(passwordEncoder.encode(password)).roles("USER").build();
			if(!userService.exists(email)) {
				userService.addUser(u);
				session.setAttribute("user", u);
                redirect.addFlashAttribute("registrado", "Usuario registrado con éxito");
			}
			else {
			    //aqui podriamos poner que redirija a la misma pagina, no a login
                redirect.addFlashAttribute("registrado", "El usuario ya existe.");
			}
		} else {
		    //aqui podriamos poner que redirija a la misma pagina, no a login
			redirect.addFlashAttribute("registrado", "Las contraseñas no coinciden.");
		}
		return "redirect:/login";
	}*/
}
