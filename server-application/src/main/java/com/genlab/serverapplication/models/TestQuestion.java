package com.genlab.serverapplication.models;

import java.util.List;

public class TestQuestion {
	
	private long id;
	private String statement;
	private List<TestAnswer> answers;
	
	
	public TestQuestion(String statement, List<TestAnswer> answers) {
		super();
		this.statement = statement;
		this.answers = answers;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public List<TestAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<TestAnswer> answers) {
		this.answers = answers;
	}
	
	
}
