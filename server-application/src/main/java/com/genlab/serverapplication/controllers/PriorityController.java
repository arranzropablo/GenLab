package com.genlab.serverapplication.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genlab.serverapplication.models.Sections;
import com.genlab.serverapplication.services.sectionService.SectionService;

@Controller
@RequestMapping("/setpriority")
public class PriorityController {
		
	@Autowired
	public SectionService sectionService;
	
	@GetMapping("")
	public String getPriorityView(Model model) {
		List<Sections> sectionlist = sectionService.getSectionsSortedByPriority();
		model.addAttribute("sectionlist", sectionlist);
		return "priority";
	}

	@PostMapping("")
	public String setPriority(@RequestBody List<Integer> sortedidlist) {
		sectionService.updatePriority(sortedidlist);
		return "priority";
	}
	
}
