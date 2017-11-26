package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genlab.serverapplication.models.CalculationTool;

@Controller
@RequestMapping("/calculationtools")
public class CalculationToolsController {

//	@GetMapping("")
//	public String getCalculationTool(Model model) {
//		//se carga primero el 00 y luego por javascript pide fragmentos
//		List<CalculationTool> calculationTools = new ArrayList<>();
//		calculationTools.add(CalculationTool.builder().name("Testcross Dominance").id(0).build());
//		calculationTools.add(CalculationTool.builder().name("F2 Dominance").id(1).build());
//		
//		model.addAttribute("calculationTools", calculationTools);
//		
//		return "calculationTools";
//	}
	
	@GetMapping("")
	public String getCalculationTool(Model model) {
		return "redirect:/calculationtools/10";
	}
	
	@GetMapping("/{id}")
	public String getCalculationTool(@PathVariable("id") int id, Model model) {
		
		List<CalculationTool> calculationTools = new ArrayList<>();
		calculationTools.add(CalculationTool.builder().name("Testcross Dominance").id(0).build());
		calculationTools.add(CalculationTool.builder().name("F2 Dominance").id(1).build());
		
		model.addAttribute("calculationTools", calculationTools);
		
		return "calculationTools";
	}
	
}
