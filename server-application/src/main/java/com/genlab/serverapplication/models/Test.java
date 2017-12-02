package com.genlab.serverapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Test {
	
	private long id;
	private String name;
	private String date;
	//private CalculationTool calculation;
	private boolean enable;
	private List<TestQuestion> questions;
	
	
	public Test(String name, List<TestQuestion> questions) {
		super();
		this.name = name;
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		this.date = f.format(new Date());
		this.enable = true;
		this.questions = questions;
	}
	
	public void setDate(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		this.date = f.format(date);
	}
}
