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

import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.services.problemsService.ProblemsService;

@Controller
@RequestMapping("/problems")
public class ProblemsController {
	
	@Autowired
	private ProblemsService service;
	
	@GetMapping("")
	public String getProblems(HttpServletRequest request, Model model, HttpSession session) {
		List <Problem> problem_list = service.getProblemsBySection(((SectionsMapping)session.getAttribute("currentSection")).getId());
		
		if(problem_list.size() > 0) {
			model.addAttribute("actual_problem", problem_list.get(0));
		}
		else {
			return "redirect:/problems/new";
		}
		
		model.addAttribute("problem_list", problem_list);
		
		return "problems";
	}
	
	@GetMapping("/new")
	public String newProblem(HttpServletRequest request, Model model, HttpSession session) {
		List<Problem> problem_list = service.getProblemsBySection(((SectionsMapping)session.getAttribute("currentSection")).getId());
		model.addAttribute("problem_list", problem_list);
		model.addAttribute("actual_problem", Problem.builder().id(-1).build());
		
		return "problems";
	}
	
	@PostMapping("/save/{id}")
	public String saveProblem(@RequestParam("problem-name") String nombre, @PathVariable("id") int id, @RequestParam("problem-content") String content, HttpServletRequest request, Model model, HttpSession session) {
		Problem newProblem = Problem.builder().nombre(nombre).contenido(content).sectionid(((SectionsMapping)session.getAttribute("currentSection")).getId()).build();
		if(id > 0) {
			newProblem.setId(id);
		}
		int nuevoID = service.saveProblem(newProblem);
		return "redirect:/problems/detail/"+nuevoID;
	}

	@GetMapping("/detail/{id}")
	public String changeProblem(@PathVariable("id") int id, HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("problem_list", service.getProblemsBySection(((SectionsMapping)session.getAttribute("currentSection")).getId()));
		model.addAttribute("actual_problem", service.getProblem(id));
		return "problems";
	}

}
