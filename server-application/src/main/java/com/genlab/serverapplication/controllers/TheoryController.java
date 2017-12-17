package com.genlab.serverapplication.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.services.theoryService.TheoryServiceImpl;

@Controller
@RequestMapping("/theory")
public class TheoryController {
	
	@Autowired
	private TheoryServiceImpl service;
		
	@GetMapping("")
	public String getTheory(HttpServletRequest request, Model model, HttpSession session) {

		List<Theory> theory_list = service.getTheoryBySection(((SectionsMapping)session.getAttribute("currentSection")).getId());
		
		
		if(theory_list.size() > 0) {
			model.addAttribute("actual_theory", theory_list.get(0));
		} else {
			return "redirect:/theory/new";
		}
		
		model.addAttribute("theory_list", theory_list);
		
		return "theory";
	}
	
	@GetMapping("/new")
	public String newTheory(HttpServletRequest request, Model model, HttpSession session) {
		List<Theory> theory_list = service.getTheoryBySection(((SectionsMapping)session.getAttribute("currentSection")).getId());
		model.addAttribute("theory_list", theory_list);
		model.addAttribute("actual_theory", Theory.builder().id(-1).build());
		
		return "theory";
	}
	
	@PostMapping("/save/{id}")
	public String saveTheory(@RequestParam("theory-title") String title, @PathVariable("id") int id, @RequestParam("theory-content") String content, HttpServletRequest request, Model model, HttpSession session) {
		Theory newTheory = Theory.builder().titulo(title).contenido(content).sectionid(((SectionsMapping)session.getAttribute("currentSection")).getId()).build();
		if(id > 0) {
			newTheory.setId(id);
		}
		int nuevoID = service.saveTheory(newTheory);
		return "redirect:/theory/detail/"+nuevoID;
	}
	
	@GetMapping("/detail/{id}")
	public String changeTheory(@PathVariable("id") int id, HttpServletRequest request, Model model, HttpSession session) {
		if(service.existsTheory(id)) {
			model.addAttribute("theory_list", service.getTheoryBySection(((SectionsMapping)session.getAttribute("currentSection")).getId()));
			model.addAttribute("actual_theory", service.getTheory(id));
			return "theory";
		}else {
			return "redirect:/theory";
		}
	}
}
