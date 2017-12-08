package com.genlab.serverapplication.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tests")
public class Test implements Serializable{
	
	private static final long serialVersionUID = -2999200171545460302L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int sectionid;
	@Column(name="titulo")
	private String name;
	@Column(name="fecha")
	private String date;
	//
	//private CalculationTool calculation;
	//
	@OneToMany(targetEntity=TestQuestion.class, mappedBy="test", cascade=CascadeType.ALL)
	private List<TestQuestion> questions;
	
	
	public Test(String name, ArrayList<TestQuestion> questions, int sectionId) {
		super();
		this.name = name;
		this.sectionid= sectionId;
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		this.date = f.format(new Date());
		this.questions = questions;
	}
	
	public void setDate(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		this.date = f.format(date);
	}
}
