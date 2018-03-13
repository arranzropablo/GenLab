package com.genlab.serverapplication.models;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CTResult {
	
	private Map<String,Double> expectedValues;
	private Map<String, Double> resultValues;
	private boolean cleanInputs;
	private String feedbackMessage;
	private Map<String,String> agree;
	private Map<String,Integer> observed;
	private String result;
	private Map<String,String> phase;

}
