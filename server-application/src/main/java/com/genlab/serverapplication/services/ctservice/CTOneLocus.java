package com.genlab.serverapplication.services.ctservice;

import com.genlab.serverapplication.viewobjects.CTResult;

import java.util.HashMap;
import java.util.Map;

public class CTOneLocus {

	public CTResult testcross(int obsCapitalA, int obsLowerCasea) {
		//Cosas que haríamos en el controlador:
		//Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
		//Validar que sea > 5
		//Sumar para poner total en el propio controlador
		//Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
		double expectedValue = (obsCapitalA + obsLowerCasea) / 2, chi; //TODO Comprobar que se guarda con decimales
		Map<String, Double> expValues = new HashMap<>();
		expValues.put("a", expectedValue);
		expValues.put("A", expectedValue);
		CTResult result = CTResult.builder().expectedValues(expValues).build();
		if(expectedValue > 10) {
			chi = (Math.pow(obsCapitalA - expectedValue, 2) + Math.pow(obsLowerCasea - expectedValue, 2))/expectedValue;
		} else {
			chi = (Math.pow((obsCapitalA - expectedValue) - 0.5, 2) + Math.pow((obsLowerCasea - expectedValue) - 0.5, 2))/expectedValue;
		}
		result.setValue(chi);
		return result;
	}
	
	public CTResult f2Dominance(int obsCapitalA, int obsLowerCasea) {
		//Cosas que haríamos en el controlador:
		//Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
		//Validar que sea > 5
		//Sumar para poner total en el propio controlador
		//Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
		double expectedValueCapitalA = (obsCapitalA + obsLowerCasea) *(3/4);
		double expectedValueLowerCaseA = (obsCapitalA + obsLowerCasea) /4;
		double chi; //TODO Comprobar que se guarda con decimales
		Map<String, Double> expValues = new HashMap<>();
		expValues.put("a", expectedValueLowerCaseA);
		expValues.put("A", expectedValueCapitalA);
		CTResult result = CTResult.builder().expectedValues(expValues).build();
		if(expectedValueLowerCaseA > 10 || expectedValueCapitalA > 10) {
			chi = (Math.pow((obsCapitalA - expectedValueCapitalA), 2)/expectedValueCapitalA) + (Math.pow(obsLowerCasea - expectedValueLowerCaseA, 2)/expectedValueLowerCaseA);
		} else {
			chi = (Math.pow((obsCapitalA - expectedValueCapitalA) - 0.5, 2)/expectedValueCapitalA) + (Math.pow((obsLowerCasea - expectedValueLowerCaseA)-0.5, 2)/expectedValueLowerCaseA);
		}
		result.setValue(chi);
		return result;
	}
	
}
