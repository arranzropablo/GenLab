package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import com.genlab.serverapplication.models.CalculationToolsMapping;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.Theory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genlab.serverapplication.models.CalculationTool;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/calculationtools")
public class CalculationToolsController {

	@GetMapping("")
	public String getCalculationTool(Model model) {
		return "redirect:/calculationtools/00";
	}
	
	@GetMapping("/{id}")
	public String getCalculationTool(@PathVariable("id") int id, Model model, HttpSession session) {
		List<CalculationTool> calc_list = new ArrayList<>();

		for (CalculationToolsMapping cp : CalculationToolsMapping.values()){
			if(cp.getSectionId() == ((SectionsMapping)session.getAttribute("currentSection")).getId()){
				calc_list.add(CalculationTool.builder().id(Integer.parseInt(String.valueOf(cp.getSectionId()) + String.valueOf(cp.getSectionId()))).name(cp.getValue()).build());
			}
		}

		//enviar en el model un id y qe se ponga con utext quizas q es sin formatear
		model.addAttribute("calculationTools", calc_list);

		return "calculationTools";
	}
	
}
