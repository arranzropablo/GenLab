package com.genlab.serverapplication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
	
	private int id;
	private String name;
	private String author;
	private String isbn;
	private String link;
	
}
