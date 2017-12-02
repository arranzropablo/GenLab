package com.genlab.serverapplication.viewobjects;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CTResult {
	
	private Map<String , Double> expectedValues;
	private double value;
	
}
