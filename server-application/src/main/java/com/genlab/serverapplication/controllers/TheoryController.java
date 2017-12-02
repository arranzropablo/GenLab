package com.genlab.serverapplication.controllers;

import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.services.TheoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TheoryController {
	
	@Autowired
	private TheoryServiceImpl service;
	
	private Theory actual_theory;
	private int cont;
	
	@RequestMapping(value = "/theory", method=RequestMethod.GET)
	public String getTheory(HttpServletRequest request, Model model) {
//		theory_list = new ArrayList<Theory> ();
		List<Theory> theory_list = service.getAllTheory();
		
		if(theory_list.size()>0) {
			cont = theory_list.size();
			actual_theory = service.getTheory(0);
		}else {
			cont = 0;
			actual_theory = new Theory();
		}
//		this.actual_theory = new Theory();
//		theory_list.add(new Theory(1, "Teoria 1", "Esto es teoria"));
//		this.theory_list.add(new Theory(2, "Teoria 2", "Contenido de la teoria"));
		
		model.addAttribute("theory_list", theory_list);
		model.addAttribute("actual_theory", actual_theory);
		
		return "theory";
	}
	
	@RequestMapping(value = "/theory/newTheory", method=RequestMethod.GET)
	public String newTheory(HttpServletRequest request, Model model) {
		actual_theory = new Theory(1, "New Theory", "");
		
		model.addAttribute("theory_list", service.getAllTheory());
		model.addAttribute("actual_theory", actual_theory);
		
		return "theory";
	}
	
	@RequestMapping(value = "/theory/saveTheory", method=RequestMethod.POST)
	public String saveTheory(@RequestParam("theory-title") String title, @RequestParam("theory-content") String content, @RequestParam("theory-id") Integer id, HttpServletRequest request, Model model) {
		int ident = id;
		if(service.existsTheory((long)ident)) {
			Theory th = new Theory(ident, title, content);
		}else {
			Theory th = new Theory(Integer.toUnsignedLong(cont), title, content);
			service.saveTheory(th);
			actual_theory = th;
		}
		
		return "redirect:/theory";
	}
	
	@RequestMapping(value = "/theory/{id}", method=RequestMethod.GET)
	public String changeTheory(@PathVariable("id") long id, HttpServletRequest request, Model model) {
		actual_theory = service.getAllTheory().get((int) id);
		
		model.addAttribute("theory_list", service.getAllTheory());
		model.addAttribute("actual_theory", actual_theory);
		
		return "theory";
	}
}
