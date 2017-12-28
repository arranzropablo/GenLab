package com.genlab.serverapplication.services.bookService;

import java.util.List;

import com.genlab.serverapplication.models.Book;

public interface BookService {
	public List<Book> getAllBooks();
	
	public Book getBook(int id);
	
	public int saveBook(Book b);
	
	public void deleteBook(int id);
	
	public List<Book> getBooksBySection(int sectionid);
	
	public boolean existsBook(int id);
}
