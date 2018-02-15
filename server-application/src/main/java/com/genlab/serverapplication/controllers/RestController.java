package com.genlab.serverapplication.controllers;

import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.services.problemsService.ProblemsService;
import com.genlab.serverapplication.services.theoryService.TheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

//TODO me da miedo que al llamar a la API me redirija a /home/twoloci aunque creo qe eso es cosa
//TODO del controlador pero hay qe hacer pruebas de que la API no redirija a sitios raros
@Controller("/api/v1")
public class RestController {

    @Autowired
    private ProblemsService problemsService;

    @Autowired
    private TheoryService theoryService;

    @GetMapping("/problems")
    public @ResponseBody List<Problem> getAllProblems(@RequestParam("sectionid") int section){
        //hay qe hacer una prueba a enviar un string desde el cliente a ver qe hace cuando no pueda castearlo a int, la solucion sería que fuera un object
        //hacer tambien una prueba de pasarle una seccion qe no exista a ver si devuelve vacio
        return problemsService.getProblemsBySection(section);
    }

    @GetMapping("/theory/titles")
    public @ResponseBody List<String> getTheoryTitles(@RequestParam("sectionid") int section){
        return theoryService.getTheoryBySection(section).stream().map(Theory::getTitulo).collect(Collectors.toList());
    }

    @GetMapping("/theory/:id")
    public @ResponseBody Theory getTheory(@PathVariable("id") int id){
        return theoryService.getTheory(id);
    }

}
