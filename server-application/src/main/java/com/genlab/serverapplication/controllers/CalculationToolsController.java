package com.genlab.serverapplication.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculationtools")
public class CalculationToolsController {

	@GetMapping("")
	public String getCalculationToolsList(HttpServletRequest request, Model model) {
	
		return "ctList";
	}
	
}
