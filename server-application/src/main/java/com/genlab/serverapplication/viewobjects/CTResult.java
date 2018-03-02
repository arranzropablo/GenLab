package com.genlab.serverapplication.viewobjects;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CTResult {
	
	private Map<String,Double> expectedValues;
	private Map<String,Double> values;
	private Map<String,Double> observed;
	private Map<String,String> agree;
	private Map<String,String> phase;
	private Map<String,String> valuesString;
	private String result;
	
}
