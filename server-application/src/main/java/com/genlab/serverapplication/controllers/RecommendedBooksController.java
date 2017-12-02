package com.genlab.serverapplication.controllers;

import com.genlab.serverapplication.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recommendedbooks")
public class RecommendedBooksController {
	
	@GetMapping("")
	public String getBookList(Model model, HttpSession session) {
		
		List<Book> books = new ArrayList<>();
		books.add(Book.builder().name("Fahrenheit 451").id(1).isbn("123123123").link("www.google.es").author("pablo").build());
		
		model.addAttribute("books", books);
		
		return "bookList";
	}
	
	@GetMapping("/add")
	public String addNewBook(Model model) {
		int nuevoID = 0;
		//hacer un findAll del servicio y crear uno nuevo asignandole el ID, luego redirigir a edit/{id}
		return "redirect:/recommendedbooks/edit/" + nuevoID;
	}
	
	@GetMapping("/edit/{id}")
	public String getEditBook(Model model, @PathVariable("id") int id) {
		Book b = Book.builder().name("Fahrenheit 451").id(1).isbn("123123123").link("www.google.es").author("pablo").build();
		model.addAttribute("book", b);
		return "bookEdit";
	}
	
	@PostMapping("/edit/{id}")
	public String postEditBook(Model model, @PathVariable("id") int id, @ModelAttribute Book book) {
		
		return "bookEdit";
	}
	
}
