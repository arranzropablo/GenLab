package com.genlab.serverapplication.services.ctservice;

import java.util.HashMap;
import java.util.Map;

import com.genlab.serverapplication.models.CTResult;

public class CTOneLocus {

	public CTResult testcross(int obsA, int obsa) {
		//Cosas que haríamos en el controlador:
		//Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
		//Validar que sea > 5
		//Sumar para poner total en el propio controlador
		//Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
		double expectedValue = (obsA + obsa) / 2, chi; //TODO Comprobar que se guarda con decimales
		Map<String, Double> expValues = new HashMap<>();
		expValues.put("expectedglobal", expectedValue);
		CTResult result = CTResult.builder().expectedValues(expValues).build();
		if(expectedValue > 10) {
			chi = (Math.pow(obsA - expectedValue, 2) + Math.pow(obsa - expectedValue, 2))/expectedValue;
		} else {
			chi = (Math.pow((obsA - expectedValue) - 0.5, 2) + Math.pow((obsa - expectedValue) - 0.5, 2))/expectedValue;
		}
		result.getValues().put("chi", chi);
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
		result.getValues().put("chi", chi);
		return result;
	}
	
	public CTResult f2CoDominance(int obsAA, int obsAa, int obsaa) {
		int total = obsAA + obsAa + obsaa;
		double expectedValueAA = total/4;
		double expectedValueAa = total/2;
		double expectedValueaa = total/4;
		double chi;
		Map<String, Double> expValues = new HashMap<>();
		expValues.put("AA", expectedValueAA);
		expValues.put("Aa", expectedValueAa);
		expValues.put("aa", expectedValueaa);
		CTResult result = CTResult.builder().expectedValues(expValues).build();
		if(expectedValueAA>10 || expectedValueAa>10 || expectedValueaa>10) {
			chi = (Math.pow((obsAA - expectedValueAA), 2)/expectedValueAA) + (Math.pow(obsAa - expectedValueAa, 2)/expectedValueAa)+ (Math.pow(obsaa - expectedValueaa, 2)/expectedValueaa);
		}else {
			chi = (Math.pow((obsAA - expectedValueAA)-0.5, 2)/expectedValueAA) + (Math.pow(obsAa - expectedValueAa-0.5, 2)/expectedValueAa)+ (Math.pow(obsaa - expectedValueaa-0.5, 2)/expectedValueaa);
		}
		result.getValues().put("chi", chi);
		return result;
	}
	
	public CTResult coDominance3Alleles(int obsA1A1, int obsA1A2, int obsA1A3, int obsA2A3) {
		int total = obsA1A1 + obsA1A2 + obsA1A3+ obsA2A3;
		double expectedValue = total/4;
		double chi;
		Map<String, Double> expValues = new HashMap<>();
		expValues.put("expectedglobal", expectedValue);
		CTResult result = CTResult.builder().expectedValues(expValues).build();
		if(expectedValue>10) {
			chi = (Math.pow((obsA1A1 - expectedValue), 2)/expectedValue) + (Math.pow((obsA1A2 - expectedValue), 2)/expectedValue)+ (Math.pow((obsA1A3 - expectedValue), 2)/expectedValue)+ (Math.pow((obsA2A3 - expectedValue), 2)/expectedValue);
		}else {
			chi = (Math.pow((obsA1A1 - expectedValue)-0.5, 2)/expectedValue) + (Math.pow((obsA1A2 - expectedValue)-0.5, 2)/expectedValue)+ (Math.pow((obsA1A3 - expectedValue)-0.5, 2)/expectedValue)+ (Math.pow((obsA2A3 - expectedValue)-0.5, 2)/expectedValue);
		}
		result.getValues().put("chi", chi);
		return result;
	}
	
	public CTResult coDominance4Alleles(int obsA1A3, int obsA1A4, int obsA2A3, int obsA2A4) {
		int total = obsA1A3 + obsA1A4 + obsA2A3+ obsA2A4;
		double expectedvalue = total/4;
		double chi;
		Map<String, Double> expValues = new HashMap<>();
		expValues.put("expectedglobal", expectedvalue);
		CTResult result = CTResult.builder().expectedValues(expValues).build();
		if(expectedvalue>10) {
			chi = (Math.pow((obsA1A3 - expectedvalue), 2)/expectedvalue) + (Math.pow((obsA1A4 - expectedvalue), 2)/expectedvalue)+ (Math.pow((obsA2A3 - expectedvalue), 2)/expectedvalue)+ (Math.pow((obsA2A4 - expectedvalue), 2)/expectedvalue);
		}else {
			chi = (Math.pow((obsA1A3 - expectedvalue)-0.5, 2)/expectedvalue) + (Math.pow((obsA1A4 - expectedvalue)-0.5, 2)/expectedvalue)+ (Math.pow((obsA2A3 - expectedvalue)-0.5, 2)/expectedvalue)+ (Math.pow((obsA2A4 - expectedvalue)-0.5, 2)/expectedvalue);
		}
		result.getValues().put("chi", chi);
		return result;
	}
	
	public CTResult lethalGenes(int obsAA, int obsAa) {
		int total = obsAA + obsAa;
		double expectedValueAA = total/3;
		double expectedValueAa = total*(2/3);
		double chi;
		Map<String, Double> expValues = new HashMap<>();
		expValues.put("AA", expectedValueAA);
		expValues.put("Aa", expectedValueAa);
		CTResult result = CTResult.builder().expectedValues(expValues).build();
		if(expectedValueAA>10 || expectedValueAa>10) {
			chi = (Math.pow((obsAA - expectedValueAA), 2)/expectedValueAA) + (Math.pow((obsAa - expectedValueAa), 2)/expectedValueAa);
		}else {
			chi = (Math.pow((obsAA - expectedValueAA)-0.5, 2)/expectedValueAA) + (Math.pow((obsAa - expectedValueAa)-0.5, 2)/expectedValueAa);
		}
		result.getValues().put("chi", chi);
		return result;
	}
	
}
