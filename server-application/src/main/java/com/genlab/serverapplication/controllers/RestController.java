package com.genlab.serverapplication.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genlab.serverapplication.models.Book;
import com.genlab.serverapplication.models.CTResult;
import com.genlab.serverapplication.models.FeedbackObject;
import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.models.Test;
import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.bookService.BookService;
import com.genlab.serverapplication.services.problemsService.ProblemsService;
import com.genlab.serverapplication.services.testsService.TestService;
import com.genlab.serverapplication.services.theoryService.TheoryService;
import com.genlab.serverapplication.services.userService.UserService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin
public class RestController {

    @Autowired
    private ProblemsService problemsService;

    @Autowired
    private TheoryService theoryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TestService testService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CalculationToolsController calcController;

    @GetMapping("/problems")
    public @ResponseBody List<Problem> getAllProblems(@RequestParam("sectionid") int section){
        return problemsService.getProblemsBySection(section);
    }

    @GetMapping("/theory/titles")
    public @ResponseBody List<String> getTheoryTitles(@RequestParam("sectionid") int section){
        return theoryService.getTheoryBySection(section).stream().map(Theory::getTitulo).collect(Collectors.toList());
    }

    @GetMapping("/theory")
    public @ResponseBody Theory getTheory(@RequestParam("id") int id){
        return theoryService.getTheory(id);
    }

    @GetMapping("/books")
    public @ResponseBody List<Book> getBooks(@RequestParam("sectionid") int section){
        return bookService.getBooksBySection(section);
    }

    private Test mapTest(Test test){
        test.getQuestions().forEach(question -> {
            question.setTest(null);
            question.getAnswers().forEach(answer -> {
                answer.setPregunta(null);
            });
        });
        return test;
    }

    @GetMapping("/tests")
    public @ResponseBody List<Test> getTests(@RequestParam("sectionid") int section){
        List<Test> testsToReturn = testService.getTestBySection(section);
        testsToReturn.stream().forEach(test -> mapTest(test));
        return testsToReturn;
    }

    @GetMapping("/tests/detail")
    public @ResponseBody Test getTest(@RequestParam("id") int id){
        Test testToReturn = testService.getTest(id);
        return mapTest(testToReturn);
    }

    @PostMapping("/calctool")
    public @ResponseBody CTResult getResult(@RequestParam("CTid") String CTid, @RequestBody HashMap<String, Integer> values){
        return calcController.getCalcResult(CTid, values);
    }

	@GetMapping("/feedback")
	public @ResponseBody boolean addFeedback(@RequestParam("usu") String usuario,
			@RequestParam("feedback") String feedback) {
		boolean dev = false;
		User u = userService.getUser(usuario);
		if (u != null) {
			Gson gson = new Gson();
			FeedbackObject clientFb = gson.fromJson(feedback, FeedbackObject.class);
			String servFeed = u.getFeedback();
			if( servFeed == null || servFeed.equals("")) {
				servFeed = "{user: '', right: [], wrong: []}";
			}
			FeedbackObject serverFb = gson.fromJson(servFeed, FeedbackObject.class);
			serverFb.getRight().addAll(clientFb.getRight());
			serverFb.getWrong().addAll(clientFb.getWrong());
			u.setFeedback(gson.toJson(serverFb));
			userService.addUser(u);
			dev = true;
		}
		return dev;
	}

}
