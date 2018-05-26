package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.Test;
import com.genlab.serverapplication.models.TestAnswer;
import com.genlab.serverapplication.models.TestQuestion;
import com.genlab.serverapplication.services.testsService.TestAnswerService;
import com.genlab.serverapplication.services.testsService.TestQuestionService;
import com.genlab.serverapplication.services.testsService.TestService;

@Controller
@RequestMapping("/tests")
public class TestsController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private TestQuestionService testQuestionService;
	
	@Autowired
	private TestAnswerService testAnswerService;
	
	/**
	 * Mostrar la página de resumen de tests
	 * **/
	@GetMapping("")
	public String allTests(HttpServletRequest request, Model model, HttpSession session) {
		List<Test> test_list = testService.getTestBySection(((SectionsMapping)session.getAttribute("currentSection")).getId());
		model.addAttribute("tests",test_list);
		session.setAttribute("listAnswersDeleted", new ArrayList<Integer>());
		session.setAttribute("listQuestionsDeleted", new ArrayList<Integer>());
		return "tests";
	}

	/**
	 * Mostrar la página de un test por ID
	 * **/
	@GetMapping("/detail/{id}")
	public String oneTest(@PathVariable("id") int id, HttpServletRequest request, @ModelAttribute("currentTest") Test currentTest, Model model, HttpSession session) {
		if(currentTest != null && currentTest.getTitulo() != null) {
			model.addAttribute("test", currentTest);	
			return "test";
		} else if(testService.existsTest(id)) {
			Test retrievedTest = testService.getTest(id);
			model.addAttribute("test", retrievedTest);	
			return "test";
		} else {
			return "redirect:/tests";
		}
	}
	
	/**
	 * Añadir un Test
	 * **/
	@GetMapping("/new")
	public String addTest(HttpServletRequest request, Model model, HttpSession session) {
		Test newTest = Test.builder().titulo("New Test").questions(new ArrayList<TestQuestion>()).sectionid(((SectionsMapping)session.getAttribute("currentSection")).getId()).build();
		session.setAttribute("currentTest", newTest);
		model.addAttribute("test", newTest);
		return "test";
	}
	
	/**
	 * Añadir una pregunta
	 * 
	 */
	@PostMapping(path="/save", params= {"questionAdd"})
	public String addQuestion(@ModelAttribute("test") Test test, Model model, RedirectAttributes redirect) {
		//TODO hacer qe esto se muestre como hint, no como value
		TestQuestion question = TestQuestion.builder().answers(new ArrayList<TestAnswer>()).texto("Nueva Pregunta").build();
		if(test.getQuestions() == null) {
			test.setQuestions(new ArrayList<TestQuestion>());
		}
		test.getQuestions().add(question);
		redirect.addFlashAttribute("currentTest", test);
		return "redirect:/tests/detail/" + test.getId();
	}
	
	/**
	 * Añadir una respuesta a una pregunta
	 * */
	@PostMapping(path="/save", params= {"answerAdd"})
	public String addAnswer(@RequestParam("questionPos") int questionPos, @ModelAttribute("test") Test test, Model model, RedirectAttributes redirect) {
		//TODO hacer qe esto se muestre como hint, no como value
		TestAnswer answer = TestAnswer.builder().correcta(false).texto("Nueva Respuesta").build();
		TestQuestion testQ = test.getQuestions().get(questionPos);
		if(testQ.getAnswers() == null) {
			testQ.setAnswers(new ArrayList<TestAnswer>());
		}
		testQ.getAnswers().add(answer);
		redirect.addFlashAttribute("currentTest", test);
		return "redirect:/tests/detail/" + test.getId();
	}
	
	/**
	 * Guardar información del formulario de Test
	 * */
	@PostMapping("/save")
	public String saveTest(@ModelAttribute("test") Test test, HttpSession session) {

		List<Integer> listQuestionsDeleted = (List<Integer>)session.getAttribute("listQuestionsDeleted");
		List<Integer> listAnswersDeleted = (List<Integer>)session.getAttribute("listAnswersDeleted");

		for(int answerId : listAnswersDeleted) {
			if(testAnswerService.existsAnswer(answerId)) {
				testAnswerService.deleteAnswer(answerId);
			}
		}

		for(int questionId : listQuestionsDeleted) {
			if(testQuestionService.existsQuestion(questionId)) {
				testQuestionService.deleteQuestion(questionId);
			}
		}

		Test testToSave = Test.builder().sectionid(((SectionsMapping)session.getAttribute("currentSection")).getId()).titulo(test.getTitulo()).id(test.getId()).build();
		int savedTest = testService.saveTest(testToSave);
		if(test.getQuestions() != null) {
			for(TestQuestion question : test.getQuestions()) {
				TestQuestion questionToSave = TestQuestion.builder().test(test.withId(savedTest)).id(question.getId()).texto(question.getTexto()).build();
				TestQuestion savedQuestion = testQuestionService.saveQuestion(questionToSave);
				if(question.getAnswers() != null) {
					for(TestAnswer answer : question.getAnswers()) {
						TestAnswer answerToSave = TestAnswer.builder().correcta(answer.isCorrecta()).id(answer.getId()).texto(answer.getTexto()).pregunta(savedQuestion).build();
						testAnswerService.saveAnswer(answerToSave);
					}
				}
			}
		}
		return "redirect:/tests";
	}

	
	/**
	 * Borra un test por ID
	 * **/
	@GetMapping("/delete/{id}")
	public String deleteTest(@PathVariable("id") int id, HttpServletRequest request, Model model, HttpSession session) {
		if(testService.existsTest(id)) {
			testService.deleteTest(id);
		}
		return "redirect:/tests";
	}
	/**
	 * Borra una pregunta
	 */
	@PostMapping(path="/save", params= {"questionDel"})
	public String deleteQuestion(@RequestParam("questionPos") int pos, @ModelAttribute("test") Test test, RedirectAttributes redirect, HttpSession session) {
		int deletedQuestion = test.getQuestions().remove(pos).getId();
		((List<Integer>)session.getAttribute("listQuestionsDeleted")).add(deletedQuestion);
		redirect.addFlashAttribute("currentTest", test);
		return "redirect:/tests/detail/" + test.getId();
	}
	
	//TODO si borro una pregunta (y sus respuestas primero? asi es como lo hice) y guardo vuelve a tener dos preguntas pero con el texto de la que no he borrado... no se estan borrando bien de la BD creo

	/**
	 * Eliminar una respuesta de una pregunta
	 * */
	@PostMapping(path="/save", params= {"answerDel"})
	public String deleteAnswer(@RequestParam("questionPos") int questionPos, @RequestParam("answerPos") int answerPos, @ModelAttribute("test") Test test, RedirectAttributes redirect, HttpSession session) {
		int deletedAnswer = test.getQuestions().get(questionPos).getAnswers().remove(answerPos).getId();
		((List<Integer>)session.getAttribute("listAnswersDeleted")).add(deletedAnswer);
		redirect.addFlashAttribute("currentTest", test);
		return "redirect:/tests/detail/" + test.getId();
	}
	
}
