package com.genlab.serverapplication.viewobjects;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CTResult {
	
	private Map<String , Double> expectedValues;
	private double value;
	
}
