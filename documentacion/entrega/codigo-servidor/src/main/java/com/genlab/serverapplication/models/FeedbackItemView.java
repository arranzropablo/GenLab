package com.genlab.serverapplication.models;

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
public class FeedbackItemView {
	private String testName;
	private int numRightAnswers;
	private int numWrongAnswers;

}
