package com.genlab.serverapplication.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genlab.serverapplication.models.Theory;

@Controller
public class TheoryController {
	private ArrayList<Theory> theory_list;
	private Theory actual_theory;
	
	@RequestMapping(value = "/theory", method=RequestMethod.GET)
	public String getTheory(HttpServletRequest request, Model model) {
		theory_list = new ArrayList<Theory> ();
		
		this.actual_theory = new Theory();
		theory_list.add(new Theory(1, "Teoria 1", "Esto es teoria"));
		this.theory_list.add(new Theory(2, "Teoria 2", "Contenido de la teoria"));
		
		model.addAttribute("theory_list", this.theory_list);
		model.addAttribute("actual_theory", this.actual_theory);
		
		return "theory";
	}
	
	@RequestMapping(value = "/theory/newTheory", method=RequestMethod.GET)
	public String newTheory(HttpServletRequest request, Model model) {
		actual_theory = new Theory(1, "New Theory", "");
		
		model.addAttribute("theory_list", this.theory_list);
		model.addAttribute("actual_theory", actual_theory);
		
		return "theory";
	}
	
	@RequestMapping(value = "/theory/{id}", method=RequestMethod.GET)
	public String changeTheory(@PathVariable("id") long id, HttpServletRequest request, Model model) {
		actual_theory = this.theory_list.get((int) id - 1);
		
		model.addAttribute("theory_list", this.theory_list);
		model.addAttribute("actual_theory", actual_theory);
		
		return "theory";
	}
}
