package com.genlab.serverapplication.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.models.Theory;

@Controller
public class ProblemsController {
	private ArrayList<Problem> problem_list;
	private Problem actual_problem;
	
	@RequestMapping(value = "/problems", method=RequestMethod.GET)
	public String getTheory(HttpServletRequest request, Model model) {
		problem_list = new ArrayList<Problem> ();
		
		this.actual_problem = new Problem();
		problem_list.add(new Problem(1, "Demasiados genes", "Esto es el contenido de demasiados genes"));
		this.problem_list.add(new Problem(2, "Problema 2", "Contenido del problema 2"));
		
		model.addAttribute("problem_list", this.problem_list);
		model.addAttribute("actual_problem", this.actual_problem);
		
		return "problems";
	}
	
	@RequestMapping(value = "/problem/newProblem", method=RequestMethod.GET)
	public String newTheory(HttpServletRequest request, Model model) {
		actual_problem = new Problem(1, "New Problem", "");
		
		model.addAttribute("problem_list", this.problem_list);
		model.addAttribute("actual_problem", this.actual_problem);
		
		return "problems";
	}
	
	@RequestMapping(value = "/problem/{id}", method=RequestMethod.GET)
	public String changeTheory(@PathVariable("id") long id, HttpServletRequest request, Model model) {
		actual_problem = this.problem_list.get((int) id - 1);
		
		model.addAttribute("problem_list", this.problem_list);
		model.addAttribute("actual_problem", this.actual_problem);
		
		return "problems";
	}

}
