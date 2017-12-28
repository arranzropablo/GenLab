package com.genlab.serverapplication.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class Book implements Serializable{
	
	private static final long serialVersionUID = -8183613288342946013L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int sectionid;
	@Column
	private String name;
	@Column
	private String author;
	@Column
	private String editorial;
	@Column
	private String isbn;
	@Column
	private String link;
	
}
