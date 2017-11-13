package com.genlab.serverapplication.models;

import com.genlab.serverapplication.models.Problem.ProblemBuilder;

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
public class Theory {
	private int id;
	private String title;
	private String content;
}
