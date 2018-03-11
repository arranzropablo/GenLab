package com.genlab.serverapplication.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.userService.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value={"/home", "/"})
	public String getHome(HttpSession session, HttpServletRequest request, Principal principal, Model model) {		
		if(session.getAttribute("currentSection") == null){
			User u = userService.getUser(principal.getName());
			session.setAttribute("user", u);
			//model.addAttribute("username",principal.getName());
            return "redirect:/home/twoloci";
        }
        return "redirect:/home/" + ((SectionsMapping)session.getAttribute("currentSection")).name().toLowerCase();
	}
	
	@GetMapping("/home/{sectionName}")
	public String getHome(@PathVariable("sectionName") String sectionName, RedirectAttributes redirecAttributes, HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("currentSection", SectionsMapping.valueOf(sectionName.toUpperCase()));
		//model.addAttribute("username",principal.getName());
		//esto quizás para que se ponga el nombre de usuario
		return "home";
	}
}
