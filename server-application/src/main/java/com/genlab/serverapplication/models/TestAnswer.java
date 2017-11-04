package com.genlab.serverapplication.models;

public class TestAnswer {
	
	private long id;
	private String answer;
	private boolean right;
	
	
	public TestAnswer(String answer, boolean right) {
		super();
		this.answer = answer;
		this.right = right;
	}
		
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	
}
