package com.genlab.serverapplication.controllers;

import com.genlab.serverapplication.models.*;
import com.genlab.serverapplication.services.bookService.BookService;
import com.genlab.serverapplication.services.ctservice.Twoloci.CTTwoLoci;
import com.genlab.serverapplication.services.problemsService.ProblemsService;
import com.genlab.serverapplication.services.testsService.TestService;
import com.genlab.serverapplication.services.theoryService.TheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private CTTwoLoci twoLociService;

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

    @GetMapping("/calctool")
    public @ResponseBody CTResult getResult(@RequestParam("AB") int AB, @RequestParam("Ab") int Ab, @RequestParam("aB") int aB, @RequestParam("ab") int ab){
        return twoLociService.testcross(AB, Ab, aB, ab);
    }

}
