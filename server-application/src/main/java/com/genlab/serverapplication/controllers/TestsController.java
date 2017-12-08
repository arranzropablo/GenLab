package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.Test;
import com.genlab.serverapplication.models.TestAnswer;
import com.genlab.serverapplication.models.TestQuestion;
import com.genlab.serverapplication.services.TestAnswerServiceImpl;
import com.genlab.serverapplication.services.TestQuestionServiceImpl;
import com.genlab.serverapplication.services.TestServiceImpl;

@Controller
@RequestMapping("/tests")
public class TestsController {
	
	@Autowired
	private TestServiceImpl serviceT;
	@Autowired
	private TestQuestionServiceImpl serviceQ;
	@Autowired
	private TestAnswerServiceImpl serviceA;
	
	
	private Test testTemp;
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
	
	
	/**
	 * Mostrar la página de resumen de tests
	 * **/
	@GetMapping("")
	public String allTests(HttpServletRequest request, Model model, HttpSession session) {
		List<Test> test_list = serviceT.getTestBySection(((SectionsMapping)session.getAttribute("currentSection")).getId());
		añadirCSSalModelo(model);
		model.addAttribute("tests",test_list);		
		return "tests";
	}
	
	/**
	 * Mostrar la página de un test por ID
	 * **/
	@GetMapping("/{id}")
	public String oneTest(@PathVariable("id") int id, HttpServletRequest request, Model model, HttpSession session) {
		if(serviceT.existsTest(id)) {
			añadirCSSalModelo(model);
			Test t = serviceT.getTest(id);
			testTemp = t;
			model.addAttribute("test",t);	
			return "test";
		}else {
			return "redirect:/tests";
		}
	}
	
	/**
	 * Añadir un Test
	 * **/
	@GetMapping("/newTest")
	@Transactional
	public String addTest(HttpServletRequest request, Model model, HttpSession session) {
		añadirCSSalModelo(model);
		Test t = new Test("New Test", new ArrayList<TestQuestion>(),((SectionsMapping)session.getAttribute("currentSection")).getId());
		serviceT.saveTest(t);
		return "redirect:/tests";
	}
	
	/**
	 * Borra un test por ID
	 * **/
	@GetMapping("/{id}/delete")
	public String deleteTest(@PathVariable("id") int id, HttpServletRequest request, Model model, HttpSession session) {
		if(serviceT.existsTest(id)) {
			serviceT.deleteTest(id);
			return "redirect:/tests";
		}else {
			return "redirect:/tests";
		}
	}
	
	/**
	 * Añadir una pregunta
	 * */
	@GetMapping("/{id}/newQ")
	@Transactional
	public String addQuestion(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		if(serviceT.existsTest(id)) {
			Test t = serviceT.getTest(id);
			TestQuestion q = new TestQuestion("New Question", new ArrayList<TestAnswer>());
			q.setTest(t);
			serviceQ.saveQuestion(q);	
			return "redirect:/tests/"+id;
		}else {
			return "redirect:/tests";
		}
	}
	
	/**
	 * Borra una pregunta
	 * */
	@GetMapping("/{id}/{qId}/delete")
	@Transactional
	public String deleteQuestion(@PathVariable("id") int id, @PathVariable("qId") int qId, HttpServletRequest request, Model model) {
		if(serviceT.existsTest(id)) {
			if(serviceQ.existsQuestion(qId)) {
				serviceQ.deleteQuestion(qId);	
				return "redirect:/tests/"+id;
			}
		}
		return "redirect:/tests";
	}
	
	/**
	 * Añadir una respuesta a una pregunta
	 * */
	@GetMapping("/{id}/{qId}/newA")
	@Transactional
	public String addAnswer(@PathVariable("id") int id, @PathVariable("qId") int qId, HttpServletRequest request, Model model) {
		if(serviceT.existsTest(id)) {
			TestQuestion q = serviceQ.getQuestion(qId);
			TestAnswer a = new TestAnswer("New Answer", false);
			a.setPregunta(q);
			serviceA.saveAnswer(a);
			return "redirect:/tests/"+id;
		}else {
			return "redirect:/tests";
		}
	}
	
	/**
	 * Eliminar una respuesta de una pregunta
	 * */
	@GetMapping("/{id}/{qId}/{aId}/delete")
	@Transactional
	public String deleteAnswer(@PathVariable("id") int id, @PathVariable("qId") int qId,@PathVariable("aId") int aId, HttpServletRequest request, Model model) {
		if(serviceT.existsTest(id)) {
			if(serviceQ.existsQuestion(qId)) {
				if(serviceA.existsAnswer(aId)) {
					serviceA.deleteAnswer(aId);	
					return "redirect:/tests/"+id;
				}
			}
		}
		return "redirect:/tests";
	}
	
	/**
	 * Guardar información del formulario de Test
	 * */
	@PostMapping("/{id}/save")
	@Transactional
	public String saveTest(@PathVariable("id") int id, @ModelAttribute Test test) {
		if(serviceT.existsTest(id)) {
			Test t = serviceT.getTest(id);
			for(TestQuestion q: test.getQuestions()) {
				q.setTest(t);
				for(TestAnswer a: q.getAnswers()) {
					a.setPregunta(q);
				}
			}
			t.setName(test.getName());
			t.setQuestions(test.getQuestions());
			serviceT.saveTest(t);
			return "redirect:/tests";
		}
		return "redirect:/tests";
	}
	
}
