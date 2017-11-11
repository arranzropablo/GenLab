package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genlab.serverapplication.models.CalculationTool;

@Controller
@RequestMapping("/calculationtools")
public class CalculationToolsController {

	@GetMapping("")
	public String getCalculationToolsList(Model model) {
		
		List<CalculationTool> calculationTools = new ArrayList<>();
		calculationTools.add(CalculationTool.builder().name("Testcross Dominance").build());
		calculationTools.add(CalculationTool.builder().name("F2 Dominance").build());
		
		model.addAttribute("calculationTools", calculationTools);
		
		return "ctList";
	}
	
}
