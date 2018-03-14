package com.genlab.serverapplication.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setpriority")
public class PriorityController {
		
	@GetMapping("")
	public String getPriorityView(HttpServletRequest request, Model model, HttpSession session) {
		return "priority";
	}

	@PostMapping("")
	public String setPriority(HttpServletRequest request, Model model, HttpSession session) {
		return "priority";
	}
	
}
