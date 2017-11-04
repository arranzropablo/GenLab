package com.genlab.serverapplication.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		this.date = f.format(date);
	}
	/*public CalculationTool getCalculation() {
		return calculation;
	}
	public void setCalculation(CalculationTool calculation) {
		this.calculation = calculation;
	}*/
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public List<TestQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<TestQuestion> questions) {
		this.questions = questions;
	}

}
