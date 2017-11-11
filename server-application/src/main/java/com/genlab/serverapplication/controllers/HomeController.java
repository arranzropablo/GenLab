package com.genlab.serverapplication.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	//@GetMapping("/{sectionName}")
	@GetMapping("")
	public String getHome(HttpServletRequest request, Model model) {
		//esto lo qe va a hacer es qe va a devolver siempre home pero dependiendo de la sectionName que le llegue (name o id)
		//va a activar y desactivar distinas cosas del menú y el default del input va a cambiar por el nombre de la sección en la q esta
		//también van a cambiar los href, todo eso se mete en el model y qe lo coja desde thymeleaf
		
		//de lo qe se trata es de qe todo esto sea sin cambiar la url (quizas se puede incluso sin recargar la pagina)
		return "home";
	}
}
