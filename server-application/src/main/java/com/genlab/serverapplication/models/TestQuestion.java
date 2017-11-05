package com.genlab.serverapplication.models;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TestQuestion {
	
	private long id;
	@NonNull
	private String statement;
	@NonNull
	private List<TestAnswer> answers;	
	
}
