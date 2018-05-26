package com.genlab.serverapplication.services.bookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.Book;
import com.genlab.serverapplication.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository booksRepository;
	
	@Override
	public List<Book> getAllBooks() {
		return (List<Book>) this.booksRepository.findAll();
	}

	@Override
	public Book getBook(int id) {
		return this.booksRepository.findOne(id);
	}

	@Override
	public int saveBook(Book b) {
		return this.booksRepository.save(b).getId();
	}

	@Override
	public void deleteBook(int id) {
		this.booksRepository.delete(id);
		
	}

	@Override
	public List<Book> getBooksBySection(int sectionid) {
		return booksRepository.findBySectionid(sectionid);
	}

	@Override
	public boolean existsBook(int id) {
		return booksRepository.exists(id);
	}

}
