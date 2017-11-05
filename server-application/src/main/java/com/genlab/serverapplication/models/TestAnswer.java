package com.genlab.serverapplication.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TestAnswer {
	
	//RequiredArgsConstructor hace un constructor para los atributos anotados como @NonNull (es el constructor que tenias)
	
	private long id;
	@NonNull
	private String answer;
	@NonNull
	private boolean right;
	
}
