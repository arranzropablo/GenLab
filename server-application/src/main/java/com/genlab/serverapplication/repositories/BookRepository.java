package com.genlab.serverapplication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.genlab.serverapplication.models.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{

	public List<Book> findBySectionid(int sectionid);
}
