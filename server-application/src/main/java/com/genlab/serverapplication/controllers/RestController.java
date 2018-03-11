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
import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.models.Test;
import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.services.bookService.BookService;
import com.genlab.serverapplication.services.ctservice.Epistasia.CTEpistasia;
import com.genlab.serverapplication.services.ctservice.Linkage.CTLinkage;
import com.genlab.serverapplication.services.ctservice.Onelocus.CTOneLocus;
import com.genlab.serverapplication.services.ctservice.Polyhybrid.CTPolyHybrid;
import com.genlab.serverapplication.services.ctservice.Twoloci.CTTwoLoci;
import com.genlab.serverapplication.services.problemsService.ProblemsService;
import com.genlab.serverapplication.services.testsService.TestService;
import com.genlab.serverapplication.services.theoryService.TheoryService;

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

    @Autowired
    private CTOneLocus oneLocusService;

    @Autowired
    private CTLinkage linkageService;

    @Autowired
    private CTPolyHybrid polyhybridService;

    @Autowired
    private CTEpistasia epistasiaService;

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
        CTResult result = CTResult.builder().feedbackMessage("Error").cleanInputs(true).build();;
        switch(Integer.parseInt(String.valueOf(CTid.toCharArray()[0]))){
            case 0:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = twoLociService.testcross(values.get("AB"),
                                                    values.get("Ab"),
                                                    values.get("aB"),
                                                    values.get("ab"));
                        break;
                    case 1:
                        result = twoLociService.f2Dominance(values.get("AB"),
                                                    values.get("Ab"),
                                                    values.get("aB"),
                                                    values.get("ab"));
                        break;
                    case 2:
                        result = twoLociService.f2coDominance(values.get("A1A1B1B1"),
                                                        values.get("A1A1B1B2"),
                                                        values.get("A1A1B2B2"),
                                                        values.get("A1A2B1B1"),
                                                        values.get("A1A2B1B2"),
                                                        values.get("A1A2B2B2"),
                                                        values.get("A2A2B1B1"),
                                                        values.get("A2A2B1B2"),
                                                        values.get("A2A2B2B2"));
                        break;
                    case 3:
                        result = twoLociService.f2coDom2dom(values.get("A1A1B"),
                                                    values.get("A1A2B"),
                                                    values.get("A2A2B"),
                                                    values.get("A1A1b"),
                                                    values.get("A1A2b"),
                                                    values.get("A2A2b"));
                        break;
                    case 4:
                        result = twoLociService.f2coDom4dom(values.get("A1A3B"),
                                                    values.get("A1A3b"),
                                                    values.get("A1A4B"),
                                                    values.get("A1A4b"),
                                                    values.get("A2A3B"),
                                                    values.get("A2A3b"),
                                                    values.get("A2A4B"),
                                                    values.get("A2A4b"));
                        break;
                    case 5:
                        result = twoLociService.f2TestcrossDom(values.get("AB"),
                                                        values.get("Ab"),
                                                        values.get("aB"),
                                                        values.get("ab"));
                        break;
                    case 6:
                        result = twoLociService.f2Testcross2Dom(values.get("A1A1B"),
                                                        values.get("A1A2B"),
                                                        values.get("A2A2B"),
                                                        values.get("A1A1b"),
                                                        values.get("A1A2b"),
                                                        values.get("A2A2b"));
                        break;
                    case 7:
                        result = twoLociService.f2Testcross4Dom(values.get("A1A3B"),
                                                        values.get("A1A3b"),
                                                        values.get("A1A4B"),
                                                        values.get("A1A4b"),
                                                        values.get("A2A3B"),
                                                        values.get("A2A3b"),
                                                        values.get("A2A4B"),
                                                        values.get("A2A4b"));
                        break;
                }
                break;
            case 1:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = oneLocusService.testcross(values.get("A"),
                                                    values.get("a"));
                        break;
                    case 1:
                        result = oneLocusService.f2Dominance(values.get("A"),
                                                    values.get("a"));
                        break;
                    case 2:
                        result = oneLocusService.f2CoDominance(values.get("AA"),
                                                        values.get("Aa"),
                                                        values.get("aa"));
                        break;
                    case 3:
                        result = oneLocusService.coDominance3Alleles(values.get("A1A1"),
                                                            values.get("A1A2"),
                                                            values.get("A1A3"),
                                                            values.get("A2A3"));
                        break;
                    case 4:
                        result = oneLocusService.coDominance4Alleles(values.get("A1A3"),
                                                            values.get("A1A4"),
                                                            values.get("A2A3"),
                                                            values.get("A2A4"));
                        break;
                    case 5:
                        result = oneLocusService.lethalGenes(values.get("AA"),
                                                    values.get("Aa"));
                        break;
                }
                break;
            case 2:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = linkageService.testcross2Loci(values.get("AB"),
                                                        values.get("Ab"),
                                                        values.get("aB"),
                                                        values.get("ab"));
                        break;
                    case 1:
                        result = linkageService.f22LociDominance(values.get("AB"),
                                                        values.get("Ab"),
                                                        values.get("aB"),
                                                        values.get("ab"));
                        break;
                    case 2:
                        result = linkageService.f22LociCodominance(values.get("AABB"),
                                                            values.get("AABb"),
                                                            values.get("AAbb"),
                                                            values.get("AaBB"),
                                                            values.get("AaBb"),
                                                            values.get("Aabb"),
                                                            values.get("aaBB"),
                                                            values.get("aaBb"),
                                                            values.get("aabb"));
                        break;
                    case 3:
                        result = linkageService.testcross3Loci(values.get("ABC"),
                                                        values.get("abc"),
                                                        values.get("ABc"),
                                                        values.get("abC"),
                                                        values.get("aBC"),
                                                        values.get("Abc"),
                                                        values.get("AbC"),
                                                        values.get("aBc"));
                        break;
                    case 4:
                        result = linkageService.testcrossDM(values.get("r1"),
                                                    values.get("r2"),
                                                    values.get("cOc"),
                                                    values.get("tOs"));
                        break;
                    case 5:
                        result = linkageService.dominanceDM(values.get("r1"),
                                                    values.get("tOs"));
                        break;
                    case 6:
                        result = linkageService.codominanceDM(values.get("r1"),
                                                        values.get("tOs"));
                        break;
                    case 7:
                        result = linkageService.testcross3Loci(values.get("ABC"),
                                                        values.get("abc"),
                                                        values.get("ABc"),
                                                        values.get("abC"),
                                                        values.get("aBC"),
                                                        values.get("Abc"),
                                                        values.get("AbC"),
                                                        values.get("aBc"));
                        break;
                }
                break;
            case 3:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = epistasiaService.whithoutModif(values.get("AB"),
                                values.get("aB"),
                                values.get("Ab"),
                                values.get("ab"));
                        break;
                    case 1:
                        result = epistasiaService.singleRecessive(values.get("AB"),
                                values.get("Ab"),
                                values.get("aBab"));
                        break;
                    case 2:
                        result = epistasiaService.singleDominant(values.get("aB"),
                                values.get("ab"),
                                values.get("ABAb"));
                        break;
                    case 3:
                        result = epistasiaService.singleAdditive(values.get("AB"),
                                values.get("ab"),
                                values.get("ABaB"));
                        break;
                    case 4:
                        result = epistasiaService.doubleRecessive(values.get("AB"),
                                values.get("AaaBab"));
                        break;
                    case 5:
                        result = epistasiaService.doubleDominant(values.get("ab"),
                                values.get("ABAbaB"));
                        break;
                    case 6:
                        result = epistasiaService.doubleDominantRecessive(values.get("aB"),
                                values.get("ABAbab"));
                        break;
                    case 7:
                        result = epistasiaService.segregation6334(values.get("6"),
                                values.get("3a"),
                                values.get("3b"),
                                values.get("4"));
                        break;
                    case 8:
                        result = epistasiaService.segregation1033(values.get("10"),
                                values.get("3a"),
                                values.get("3b"));
                        break;
                }
                break;
            case 4:
                switch (Integer.parseInt(String.valueOf(CTid.toCharArray()[1]))){
                    case 0:
                        result = polyhybridService.polyhybrid(values.get("n"),
                                                        values.get("h"),
                                                        values.get("d"),
                                                        values.get("r"),
                                                        values.get("D"),
                                                        values.get("R"),
                                                        values.get("T"));
                        break;
                    case 1:
                        result = polyhybridService.multiplealleles(values.get("locus1"),
                                                            values.get("locus2"),
                                                            values.get("locus3"),
                                                            values.get("locus4"),
                                                            values.get("locus5"));
                        break;
                }
                break;
        }
        return result;
    }

}
