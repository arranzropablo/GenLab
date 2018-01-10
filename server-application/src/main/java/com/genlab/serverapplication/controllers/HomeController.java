package com.genlab.serverapplication.controllers;

import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.userService.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	UserService service;
	
	@GetMapping(value={"/home", "/"})
	public String getHome(HttpSession session, HttpServletRequest request, Principal principal, Model model) {		
		if(session.getAttribute("currentSection") == null){
			User u = service.getUser(principal.getName());
			session.setAttribute("user", u);
			//model.addAttribute("username",principal.getName());
            return "redirect:/home/twoloci";
        }
        return "redirect:/home/" + ((SectionsMapping)session.getAttribute("currentSection")).name().toLowerCase();
	}
	
	@GetMapping("/home/{sectionName}")
	public String getHome(@PathVariable("sectionName") String sectionName, RedirectAttributes redirecAttributes, HttpSession session, HttpServletRequest request, Model model) {
		//los enlaces van a ser siempre los mismos lo unico que ten la session tenemos en qe seccion estamos para luego al hacer las consultas pasarlo
		//SectionsMapping s = (SectionsMapping)session.getAttribute("currentSection");
		model.addAttribute("currentSection", SectionsMapping.valueOf(sectionName.toUpperCase()));
		//model.addAttribute("username",principal.getName());
		return "home";
	}
}
