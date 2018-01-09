package com.genlab.serverapplication.controllers;

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

import com.genlab.serverapplication.models.Book;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.services.bookService.BookService;


@Controller
@RequestMapping("/recommendedbooks")
public class RecommendedBooksController {
	
	@Autowired
	private BookService service;
	
	@GetMapping("")
	public String getBookList(HttpServletRequest request, Model model, HttpSession session) {
		
		List <Book> books = service.getBooksBySection(((SectionsMapping)session.getAttribute("currentSection")).getId());
		model.addAttribute("books", books);
		
		return "bookList";
	}
	
	@GetMapping("/add")
	public String addNewBook(HttpServletRequest request, Model model, HttpSession session ) {
		model.addAttribute("book", Book.builder().id(-1).build());
		return "bookEdit";
	}
	
	@GetMapping("/edit/{id}")
	public String getEditBook(HttpServletRequest request, Model model, @PathVariable("id") int id) {
		if(service.existsBook(id)) {
			Book b = service.getBook(id);
			model.addAttribute("book", b);
			return "bookEdit";
		}
		return "redirect:/recommendedbooks";
	}
	
	@PostMapping("/save/{id}")
	public String postSaveBook(@ModelAttribute("book") Book book, @PathVariable("id") int id, HttpServletRequest request, Model model, HttpSession session ) {
		Book newBook = Book.builder().name(book.getName()).author(book.getAuthor()).sectionid(((SectionsMapping)session.getAttribute("currentSection")).getId()).editorial(book.getEditorial()).isbn(book.getIsbn()).link(book.getLink()).build();
		if(id > 0) {
			newBook.setId(id);
		}
		int nuevoID = service.saveBook(newBook);
		return "redirect:/recommendedbooks";
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(HttpServletRequest request, Model model, @PathVariable("id") int id) {
		if(service.existsBook(id)) {
			service.deleteBook(id);
		}
		return "redirect:/recommendedbooks";
	}
}
