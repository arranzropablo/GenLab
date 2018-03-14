package com.genlab.serverapplication.models;

import java.util.List;

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
public class FeedbackObject {
	private String user;
	private List<FeedbackItem> right;
	private List<FeedbackItem> wrong;

}
