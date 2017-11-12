package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genlab.serverapplication.models.Book;

@Controller
@RequestMapping("recommendedbooks")
public class RecommendedBooksController {
	
	@GetMapping("")
	public String getBookList(Model model) {
		
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
	public String getEditBook(Model model, @PathVariable int id) {
		Book b = Book.builder().name("Fahrenheit 451").id(1).isbn("123123123").link("www.google.es").author("pablo").build();
		model.addAttribute("book", b);
		return "bookEdit";
	}
	
	@PostMapping("/edit/{id}")
	public String postEditBook(Model model, @PathVariable int id, @ModelAttribute Book book) {
		
		return "bookEdit";
	}
	
}
