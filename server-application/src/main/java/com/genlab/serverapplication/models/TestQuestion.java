package com.genlab.serverapplication.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

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
