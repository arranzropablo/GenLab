package com.genlab.serverapplication.services.ctservice.Onelocus;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.CTResult;

@Service
public class CTOneLocusImp implements CTOneLocus{

	public CTResult testcross(int obsA, int obsa) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		String result = "";
		if(obsA > 5000 || obsa > 5000){
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
		} else {
			double expectedValue = (obsA + obsa) / 2.0, chi;
			expectedValues.put("expA", expectedValue);
			expectedValues.put("expa", expectedValue);

			if (expectedValue < 5) {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
 			} else if (expectedValue > 10) {
				chi = (Math.pow(obsA - expectedValue, 2) + Math.pow(obsa - expectedValue, 2)) / expectedValue;
			} else {
				chi = (Math.pow((obsA - expectedValue) - 0.5, 2) + Math.pow((obsa - expectedValue) - 0.5, 2)) / expectedValue;
			}
			resultValues.put("chi", chi);
			agree.put("chi", (chi > 3.841 ? "No" : "Yes"));
			result = (chi > 3.841 ? "This locus is not segregating correctly" : "This locus is segregating correctly");
		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).expectedValues(expectedValues).result(result).agree(agree).build();
	}
	
	public CTResult f2Dominance(int obsA, int obsa) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		String result = "";
		if(obsA > 5000 || obsa > 5000){
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
		} else {
			double expectedValueA = (obsA + obsa) * 3 / 4.0;
			double expectedValuea = (obsA + obsa) / 4.0;
			double chi;
			expectedValues.put("expa", expectedValuea);
			expectedValues.put("expA", expectedValueA);

			if (expectedValueA < 5 || expectedValuea < 5) {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			} else if (expectedValueA > 10 || expectedValueA > 10) {
				chi = (Math.pow((obsA - expectedValueA), 2) / expectedValueA) + (Math.pow(obsa - expectedValueA, 2) / expectedValueA);
			} else {
				chi = (Math.pow((obsA - expectedValueA) - 0.5, 2) / expectedValueA) + (Math.pow((obsa - expectedValueA) - 0.5, 2) / expectedValueA);
			}
			resultValues.put("chi", chi);
			agree.put("chi", (chi > 3.841 ? "No" : "Yes"));
			result = (chi > 3.841 ? "This locus is not segregating correctly" : "This locus is segregating correctly");
		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).expectedValues(expectedValues).result(result).agree(agree).build();
	}
	
	public CTResult f2CoDominance(int obsA1A1, int obsA1A2, int obsA2A2) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		String result = "";
		if(obsA1A1 > 5000 || obsA1A2 > 5000 || obsA2A2 > 5000){
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
		} else {
			int total = obsA1A1 + obsA1A2 + obsA2A2;
			double expectedValueAA = total / 4.0;
			double expectedValueAa = total / 2.0;
			double expectedValueaa = total / 4.0;
			double chi;
			expectedValues.put("expA1A1", expectedValueAA);
			expectedValues.put("expA1A2", expectedValueAa);
			expectedValues.put("expA2A2", expectedValueaa);

			if (expectedValueAA < 5 || expectedValueAa < 5 || expectedValueaa < 5) {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			} else if (expectedValueAA > 10 || expectedValueAa > 10 || expectedValueaa > 10) {
				chi = (Math.pow((obsA1A1 - expectedValueAA), 2) / expectedValueAA) + (Math.pow(obsA1A2 - expectedValueAa, 2) / expectedValueAa) + (Math.pow(obsA2A2 - expectedValueaa, 2) / expectedValueaa);
			} else {
				chi = (Math.pow((obsA1A1 - expectedValueAA) - 0.5, 2) / expectedValueAA) + (Math.pow(obsA1A2 - expectedValueAa - 0.5, 2) / expectedValueAa) + (Math.pow(obsA2A2 - expectedValueaa - 0.5, 2) / expectedValueaa);
			}
			resultValues.put("chi", chi);
			agree.put("chi", (chi > 5.991 ? "No" : "Yes"));
			result = (chi > 5.991 ? "This locus is not segregating correctly" : "This locus is segregating correctly");
		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).expectedValues(expectedValues).result(result).agree(agree).build();
	}
	
	public CTResult coDominance3Alleles(int obsA1A1, int obsA1A2, int obsA1A3, int obsA2A3) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		String result = "";

		if(obsA1A1 > 5000 || obsA1A2 > 5000 || obsA1A3 > 5000 || obsA2A3 > 5000){
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
		} else {
			int total = obsA1A1 + obsA1A2 + obsA1A3 + obsA2A3;
			double expectedValue = total / 4.0;
			double chi;
			expectedValues.put("expA1A1", expectedValue);
			expectedValues.put("expA1A2", expectedValue);
			expectedValues.put("expA1A3", expectedValue);
			expectedValues.put("expA2A3", expectedValue);

			if (expectedValue < 5) {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			} else if (expectedValue > 10) {
				chi = (Math.pow((obsA1A1 - expectedValue), 2) / expectedValue) + (Math.pow((obsA1A2 - expectedValue), 2) / expectedValue) + (Math.pow((obsA1A3 - expectedValue), 2) / expectedValue) + (Math.pow((obsA2A3 - expectedValue), 2) / expectedValue);
			} else {
				chi = (Math.pow((obsA1A1 - expectedValue) - 0.5, 2) / expectedValue) + (Math.pow((obsA1A2 - expectedValue) - 0.5, 2) / expectedValue) + (Math.pow((obsA1A3 - expectedValue) - 0.5, 2) / expectedValue) + (Math.pow((obsA2A3 - expectedValue) - 0.5, 2) / expectedValue);
			}
			resultValues.put("chi", chi);
			agree.put("chi", (chi > 7.815 ? "No" : "Yes"));
			result = (chi > 7.815 ? "This locus is not segregating correctly" : "This locus is segregating correctly");
		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).expectedValues(expectedValues).result(result).agree(agree).build();
	}
	
	public CTResult coDominance4Alleles(int obsA1A3, int obsA1A4, int obsA2A3, int obsA2A4) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		String result = "";

		if(obsA2A4 > 5000 || obsA1A4 > 5000 || obsA1A3 > 5000 || obsA2A3 > 5000){
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
		} else {
			int total = obsA1A3 + obsA1A4 + obsA2A3 + obsA2A4;
			double expectedValue = total / 4.0;
			double chi;
			expectedValues.put("expA2A4", expectedValue);
			expectedValues.put("expA2A3", expectedValue);
			expectedValues.put("expA1A3", expectedValue);
			expectedValues.put("expA1A4", expectedValue);

			if (expectedValue < 5) {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			} else if (expectedValue > 10) {
				chi = (Math.pow((obsA1A3 - expectedValue), 2) / expectedValue) + (Math.pow((obsA1A4 - expectedValue), 2) / expectedValue) + (Math.pow((obsA2A3 - expectedValue), 2) / expectedValue) + (Math.pow((obsA2A4 - expectedValue), 2) / expectedValue);
			} else {
				chi = (Math.pow((obsA1A3 - expectedValue) - 0.5, 2) / expectedValue) + (Math.pow((obsA1A4 - expectedValue) - 0.5, 2) / expectedValue) + (Math.pow((obsA2A3 - expectedValue) - 0.5, 2) / expectedValue) + (Math.pow((obsA2A4 - expectedValue) - 0.5, 2) / expectedValue);
			}
			resultValues.put("chi", chi);
			agree.put("chi", (chi > 7.815 ? "No" : "Yes"));
			result = (chi > 7.815 ? "This locus is not segregating correctly" : "This locus is segregating correctly");
		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).expectedValues(expectedValues).result(result).agree(agree).build();
	}
	
	public CTResult lethalGenes(int obsAA, int obsAa) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		String result = "";

		if(obsAA > 5000 || obsAa > 5000){
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
		} else {
			int total = obsAA + obsAa;
			double expectedValueAA = total / 3.0;
			double expectedValueAa = total * 2 / 3.0;
			double chi;
			expectedValues.put("expAA", expectedValueAA);
			expectedValues.put("expAa", expectedValueAa);

			if (expectedValueAA < 5 || expectedValueAa < 5) {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			} else if (expectedValueAA > 10 || expectedValueAa > 10) {
				chi = (Math.pow((obsAA - expectedValueAA), 2) / expectedValueAA) + (Math.pow((obsAa - expectedValueAa), 2) / expectedValueAa);
			} else {
				chi = (Math.pow((obsAA - expectedValueAA) - 0.5, 2) / expectedValueAA) + (Math.pow((obsAa - expectedValueAa) - 0.5, 2) / expectedValueAa);
			}
			resultValues.put("chi", chi);
			agree.put("chi", (chi > 3.841 ? "No" : "Yes"));
			result = (chi > 3.841 ? "This locus is not segregating correctly" : "This locus is segregating correctly");
		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).expectedValues(expectedValues).result(result).agree(agree).build();
	}
	
}
