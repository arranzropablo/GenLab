package com.genlab.serverapplication.controllers;

import com.genlab.serverapplication.models.Book;
import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.bookService.BookService;
import com.genlab.serverapplication.services.problemsService.ProblemsService;
import com.genlab.serverapplication.services.theoryService.TheoryService;
import com.genlab.serverapplication.services.userService.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//TODO me da miedo que al llamar a la API me redirija a /home/twoloci aunque creo qe eso es cosa
//TODO del controlador pero hay qe hacer pruebas de que la API no redirija a sitios raros
@Controller
@RequestMapping("/api/v1")
public class RestController {

    @Autowired
    private ProblemsService problemsService;

    @Autowired
    private TheoryService theoryService;

    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public @ResponseBody boolean loginUser(@RequestParam("user") String user, @RequestParam("password") String pass){
    	User u = userService.getUser(user);
    	if(u != null) {
    		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    		return encoder.matches(pass, u.getPassword());
    	}
        return false;
    }
    
    @GetMapping("/problems")
    @CrossOrigin(origins = "*")
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

    @GetMapping("/books")
    public @ResponseBody List<Book> getBooks(@RequestParam("sectionid") int section){
        return bookService.getBooksBySection(section);
    }


}
