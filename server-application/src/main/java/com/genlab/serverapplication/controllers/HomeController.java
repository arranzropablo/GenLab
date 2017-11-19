package com.genlab.serverapplication.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	@GetMapping(value={"/home", "/"})
	public String getHome(HttpSession session, HttpServletRequest request) {
		return "redirect:/home/twoloci";
	}
	
	@GetMapping("/home/{sectionName}")
	public String getHome(@PathVariable("sectionName") String sectionName, RedirectAttributes redirecAttributes, HttpSession session, HttpServletRequest request) {
		//los enlaces van a ser siempre los mismos lo unico que ten la session tenemos en qe seccion estamos para luego al hacer las consultas pasarlo
		//SectionsMapping s = (SectionsMapping)session.getAttribute("currentSection");
		return "home";
	}
}
