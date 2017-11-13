package com.genlab.serverapplication.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.models.Test;

@Controller
public class ProblemsController {
	private ArrayList<Problem> problems;
	
	private void initialize() {
		problems = new ArrayList<Problem> ();
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		
		problems.add(new Problem(1, "Problema de los peces", f.format(new Date())));
		problems.add(new Problem(2, "Demasiados genes", f.format(new Date())));
		problems.add(new Problem(3, "Laboratorio", f.format(new Date())));
	}
	@RequestMapping(value = "/problems", method=RequestMethod.GET)
	public String getProblemsList(HttpServletRequest request, Model model) {
		
		this.initialize();
		model.addAttribute("problems",problems);
		return "problemsList";
	}

}
