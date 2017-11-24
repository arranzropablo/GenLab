package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genlab.serverapplication.models.Test;
import com.genlab.serverapplication.models.TestAnswer;
import com.genlab.serverapplication.models.TestQuestion;

@Controller
public class TestsController {
	
	private List<Test> tests;
	private int cont=0;
	
	/**
	 * Determinamos los atributos que vamos a utilizar
	 * */
	@ModelAttribute
	public void addAttributes(Model m) {
		m.addAttribute("prefixCSS", "/css/");
		m.addAttribute("prefix", "../static/");
		m.addAttribute("base", "../");
	}
	
	/**
	 * Añadimos los documentos CSS que necesitamos
	 * */
	public static void añadirCSSalModelo(Model model) {
		List<String> listaCSS = new ArrayList<String>();
		listaCSS.add("tests.css");
		model.addAttribute("pageExtraCSS", listaCSS);
	}
	
	private void initializeTests() {
		
		TestAnswer answer11 = new TestAnswer("abc", true);
		TestAnswer answer12 = new TestAnswer("def", false);
		List<TestAnswer> list1 = new ArrayList<TestAnswer>();
		list1.add(answer11);
		list1.add(answer12);
		TestQuestion question1 = new TestQuestion("¿Cuál es un periódico?", list1);
		TestAnswer answer21 = new TestAnswer("ghi", false);
		TestAnswer answer22 = new TestAnswer("jfk", true);
		List<TestAnswer> list2 = new ArrayList<TestAnswer>();
		list2.add(answer21);
		list2.add(answer22);
		TestQuestion question2 = new TestQuestion("¿Cuáles son las siglas de Kennedy?", list2);
		List<TestQuestion> listQ1 = new ArrayList<TestQuestion>();
		listQ1.add(question1);
		listQ1.add(question2);
		Test test1 = new Test("Test1", listQ1);
		
		answer11 = new TestAnswer("abc", true);
		answer12 = new TestAnswer("def", false);
		list1 = new ArrayList<TestAnswer>();
		list1.add(answer11);
		list1.add(answer12);
		question1 = new TestQuestion("¿Cuál es un periódico?", list1);
		answer21 = new TestAnswer("ghi", false);
		answer22 = new TestAnswer("jfk", true);
		list2 = new ArrayList<TestAnswer>();
		list2.add(answer21);
		list2.add(answer22);
		question2 = new TestQuestion("¿Cuáles son las siglas de Kennedy?", list2);
		List<TestQuestion> listQ2 = new ArrayList<TestQuestion>();
		listQ2.add(question1);
		listQ2.add(question2);
		Test test2 = new Test("Test2", listQ2);
		
		test1.setId(0);
		test2.setId(1);
		
		tests = new ArrayList<Test>();
		tests.add(test1);
		cont++;
		tests.add(test2);
		cont++;
	}
	
	/**
	 * Mostrar la página de resumen de tests
	 * **/
	@RequestMapping(value="/tests", method=RequestMethod.GET)
	public String allTests(HttpServletRequest request, Model model) {
		
		añadirCSSalModelo(model);
		if(cont==0) {
			initializeTests();
		}
		model.addAttribute("tests",tests);		
		return "tests";
	}
	
	/**
	 * Mostrar la página de un test por ID
	 * **/
	@RequestMapping(value = "/tests/{id}", method=RequestMethod.GET)
	public String oneTest(@PathVariable("id") long id, HttpServletRequest request, Model model) {
		if(id > tests.size()-1) {
			return "redirect:/tests";
		}else {
			añadirCSSalModelo(model);
			Test t = tests.get((int) id);
			model.addAttribute("test",t);		
			return "test";
		}
	}
	
	/**
	 * Añadir un Test
	 * **/
	@RequestMapping(value = "/tests/newTest", method=RequestMethod.GET)
	public String addTest(HttpServletRequest request, Model model) {
		añadirCSSalModelo(model);
		Test t = new Test("New Test"+(cont+1), new ArrayList<TestQuestion>());
		t.setId(cont);
		tests.add(t);
		cont++;
		model.addAttribute("tests",tests);
		return "tests";
	}
	
	/**
	 * Añadir una pregunta
	 * */
	@RequestMapping(value = "/tests/{id}/newQ", method=RequestMethod.GET)
	public String addQuestion(@PathVariable("id") long id, HttpServletRequest request, Model model) {
		if(id > tests.size()-1) {
			return "redirect:/tests";
		}else {
			añadirCSSalModelo(model);
			Test t = tests.get((int) id);
			t.getQuestions().add(new TestQuestion("", new ArrayList<TestAnswer>()));
			model.addAttribute("test",t);		
			return "test";
		}
	}
}
