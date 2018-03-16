package com.genlab.serverapplication.services.ctservice.Epistasia;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.CTResult;

@Service
public class CTEpistasiaImp implements CTEpistasia{

    public CTResult whithoutModif(int obsAB, int obsaB, int obsAb, int obsab) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obsAB<=5000 && obsaB<=5000 && obsAb<=5000 && obsab<=5000){
            double total = obsAB+obsaB+obsAb+obsab;
            int obsA = obsAB+obsAb;
            int obsa = obsaB+obsab;
            int obsB = obsAB+obsaB;
            int obsb = obsAb+obsab;
            double expA = total * 3 / 4.0;
            double expa = total /4.0;
            double expB = total * 3/4.0;
            double expb = total /4.0;
            double expAB = total * 9/16.0;
            double expAb = total * 3/16.0;
            double expaB = total * 3/16.0;
            double expab = total /16.0;
            double expConAB = (obsA * obsB )/(double)total;
            double expConAb = (obsA * obsb )/(double)total;
            double expConaB = (obsa * obsB )/(double)total;
            double expConab = (obsa * obsb )/(double)total;
            double chiA = 0, chiB= 0, chiInd= 0, chiCon= 0;
            if(expAB>10 && expAb>10 && expaB>10 && expab>10 && expConAB>10 && expConAb>10 &&expConaB>10 && expConab>10){
                chiA = ((Math.pow((obsA - expA),2))/expA) + ((Math.pow((obsa - expa),2))/expa);
                chiB = ((Math.pow((obsB - expB),2))/expB) + ((Math.pow((obsb - expb),2))/expb);
                chiInd = ((Math.pow((obsAB - expAB),2))/expAB) + ((Math.pow((obsAb - expAb),2))/expAb)+((Math.pow((obsaB - expaB),2))/expaB)+((Math.pow((obsab - expab),2))/expab);
                chiCon = ((Math.pow((obsAB - expConAB),2))/expConAB) + ((Math.pow((obsAb - expConAb),2))/expConAb)+((Math.pow((obsaB - expConaB),2))/expConaB)+((Math.pow((obsab - expConab),2))/expConab);
            }
            if(expAB <5 || expaB <5 || expAb <5 || expab <5 || expConAB <5 || expConaB <5 || expConAb <5 || expConab <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expAB>=5 && expAB<=10) || (expAb>=5 && expAb<=10) || (expaB>=5 && expaB<=10) || (expab>=5 && expab<=10) || (expConAB>=5 && expConAB<=10) || (expConAb>=5 && expConAb<=10) || (expConaB>=5 && expConaB<=10) || (expConab>=5 && expConab<=10)){
                chiInd = ((Math.pow((obsAB - expAB)-0.5,2))/expAB) + ((Math.pow((obsAb - expAb)-0.5,2))/expAb)+((Math.pow((obsaB - expaB)-0.5,2))/expaB)+((Math.pow((obsab - expab)-0.5,2))/expab);
                chiCon = ((Math.pow((obsAB - expConAB)-0.5,2))/expConAB) + ((Math.pow((obsAb - expConAb)-0.5,2))/expConAb)+((Math.pow((obsaB - expConaB)-0.5,2))/expConaB)+((Math.pow((obsab - expConab)-0.5,2))/expConab);
            }
            if(expA <5 || expa <5 || expb <5 || expB <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expA>=5 && expA<=10) || (expb>=5 && expb<=10) || (expB>=5 && expB<=10) || (expa>=5 && expa<=10)){
                chiA = ((Math.pow((obsA - expA)-0.5,2))/expA) + ((Math.pow((obsa - expa)-0.5,2))/expa);
                chiB = ((Math.pow((obsb - expb)-0.5,2))/expb) + ((Math.pow((obsb - expb)-0.5,2))/expb);
            }

            resultValues.put("total" , total);
            observedValues.put("obsA"  , obsA );
            observedValues.put("obsa"  , obsa );
            observedValues.put("obsB"  , obsB );
            observedValues.put("obsb"  , obsb );
            expectedValues.put("expA"  , expA );
            expectedValues.put("expa"  , expa );
            expectedValues.put("expB"  , expB );
            expectedValues.put("expb"  , expb );
            expectedValues.put("expAB" , expAB);
            expectedValues.put("expAb" , expAb);
            expectedValues.put("expaB" , expaB);
            expectedValues.put("expab" , expab);
            expectedValues.put("contAB" , expConAB);
            expectedValues.put("contAb" , expConAb);
            expectedValues.put("contaB" , expConaB);
            expectedValues.put("contab" , expConab);
            resultValues.put("chiA" , chiA);
            resultValues.put("chiB" , chiB);
            resultValues.put("chiCon" , chiCon);
            resultValues.put("chiInd" , chiInd);

            agree.put("chiA", (chiA >= 3.841 ? "No" : "Yes"));
            agree.put("chiB", (chiB >= 3.841 ? "No" : "Yes"));
            agree.put("chiCon", (chiCon >= 3.841 ? "No" : "Yes"));
            agree.put("chiInd", ((chiA < 3.841) && (chiB < 3.841) && (chiInd<=7.82)) ? "Yes" : "No");

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}

    public CTResult singleRecessive(int obsAB, int obsAb, int obsaBab) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obsAB<=5000 && obsAb<=5000 && obsaBab<=5000){
            double total = obsAB+obsAb+obsaBab;
            double expAB = total * 9/16.0;
            double expAb = total * 3/16.0;
            double expaBab = total * 4/16.0;
            double chiInd= 0;
            if(expAB>10 && expAb>10 && expaBab>10){
                chiInd = ((Math.pow((obsAB - expAB),2))/expAB) + ((Math.pow((obsAb - expAb),2))/expAb)+((Math.pow((obsaBab - expaBab),2))/expaBab);
            }
            if(expAB <5 || expAb <5 || expaBab <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expAB>=5 && expAB<=10) || (expAb>=5 && expAb<=10) || (expaBab>=5 && expaBab<=10)){
                chiInd = ((Math.pow((obsAB - expAB)-0.5,2))/expAB) + ((Math.pow((obsAb - expAb)-0.5,2))/expAb)+((Math.pow((obsaBab - expaBab)-0.5,2))/expaBab);
            }
            agree.put("chiInd", (chiInd >= 5.99 ? "No" : "Yes"));

            resultValues.put("total" , total);
            expectedValues.put("expAB" , expAB);
            expectedValues.put("expAb" , expAb);
            expectedValues.put("expaBab" , expaBab);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}

    public CTResult singleDominant(int obsaB, int obsab, int obsABAb) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obsaB<=5000 && obsab<=5000 && obsABAb<=5000){
            double total = obsaB+obsab+obsABAb;
            double expABAb = total * 12/16.0;
            double expaB = total * 3/16.0;
            double expab = total /16.0;
            double chiInd= 0;
            if(expab>10 && expaB>10 && expABAb>10){
                chiInd = ((Math.pow((obsABAb - expABAb),2))/expABAb) + ((Math.pow((obsaB - expaB),2))/expaB)+((Math.pow((obsab - expab),2))/expab);
            }
            if(expab <5 || expaB <5 || expABAb <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expab>=5 && expab<=10) || (expaB>=5 && expaB<=10) || (expABAb>=5 && expABAb<=10)){
                chiInd = ((Math.pow((obsABAb - expABAb)-0.5,2))/expABAb) + ((Math.pow((obsaB - expaB)-0.5,2))/expaB)+((Math.pow((obsab - expab)-0.5,2))/expab);
            }
            agree.put("chiInd", (chiInd >= 5.99 ? "No" : "Yes"));

            resultValues.put("total" , total);
            expectedValues.put("expABAb" , expABAb);
            expectedValues.put("expaB" , expaB);
            expectedValues.put("expab" , expab);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}

    public CTResult singleAdditive(int obsAB, int obsab, int obsAbaB) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obsAB<=5000 && obsab<=5000 && obsAbaB<=5000){
            double total = obsAB+obsab+obsAbaB;
            double expAbaB = total * 6/16.0;
            double expAB = total * 9/16.0;
            double expab = total /16.0;
            double chiInd= 0;
            if(expab>10 && expAB>10 && expAbaB>10){
                chiInd = ((Math.pow((obsAbaB - expAbaB),2))/expAbaB) + ((Math.pow((obsAB - expAB),2))/expAB)+((Math.pow((obsab - expab),2))/expab);
            }
            if(expab <5 || expAB <5 || expAbaB <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expab>=5 && expab<=10) || (expAB>=5 && expAB<=10) || (expAbaB>=5 && expAbaB<=10)){
                chiInd = ((Math.pow((obsAbaB - expAbaB)-0.5,2))/expAbaB) + ((Math.pow((obsAB - expAB)-0.5,2))/expAB)+((Math.pow((obsab - expab)-0.5,2))/expab);
            }
            agree.put("chiInd", (chiInd >= 5.99 ? "No" : "Yes"));

            resultValues.put("total" , total);
            expectedValues.put("expAbaB" , expAbaB);
            expectedValues.put("expAB" , expAB);
            expectedValues.put("expab" , expab);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}


    public CTResult doubleRecessive(int obsAB, int obsAaaBab) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obsAB<=5000 && obsAaaBab<=5000){
            double total = obsAB+obsAaaBab;
            double expAB = total * 9/16.0;
            double expAaaBab = total * 7/16.0;
            double chiInd= 0;
            if(expAB>10 && expAaaBab>10){
                chiInd = ((Math.pow((obsAB - expAB),2))/expAB)+((Math.pow((obsAaaBab - expAaaBab),2))/expAaaBab);
            }
            if(expAB <5 || expAaaBab <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expAB>=5 && expAB<=10) || (expAaaBab>=5 && expAaaBab<=10)){
                chiInd = ((Math.pow((obsAB - expAB)-0.5,2))/expAB)+((Math.pow((obsAaaBab - expAaaBab)-0.5,2))/expAaaBab);
            }
            agree.put("chiInd", (chiInd > 3.841 ? "No" : "Yes"));

            resultValues.put("total" , (double) total);
            expectedValues.put("expAB" , expAB);
            expectedValues.put("expAaaBab" , expAaaBab);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}

    public CTResult doubleDominant(int obsab, int obsABAbaB) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obsab<=5000 && obsABAbaB<=5000){
            double total = obsab+obsABAbaB;
            double expab = total /16.0;
            double expABAbaB = total * 15/16.0;
            double chiInd= 0;
            if(expab>10 && expABAbaB>10){
                chiInd = ((Math.pow((obsab - expab),2))/expab)+((Math.pow((obsABAbaB - expABAbaB),2))/expABAbaB);
            }
            if(expab <5 || expABAbaB <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expab>=5 && expab<=10) || (expABAbaB>=5 && expABAbaB<=10)){
                chiInd = ((Math.pow((obsab - expab)-0.5,2))/expab)+((Math.pow((obsABAbaB - expABAbaB)-0.5,2))/expABAbaB);
            }
            agree.put("chiInd", (chiInd > 3.841 ? "No" : "Yes"));

            resultValues.put("total" , total);
            expectedValues.put("expab" , expab);
            expectedValues.put("expABAbaB" , expABAbaB);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}

    public CTResult doubleDominantRecessive(int obsaB, int obsABAbab) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obsaB<=5000 && obsABAbab<=5000){
            double total = obsaB+obsABAbab;
            double expaB = total * 13/16.0;
            double expABAbab = total * 3/16.0;
            double chiInd= 0;
            if(expaB>10 && expABAbab>10){
                chiInd = ((Math.pow((obsaB - expaB),2))/expaB)+((Math.pow((obsABAbab - expABAbab),2))/expABAbab);
            }
            if(expaB <5 || expABAbab <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((expaB>=5 && expaB<=10) || (expABAbab>=5 && expABAbab<=10)){
                chiInd = ((Math.pow((obsaB - expaB)-0.5,2))/expaB)+((Math.pow((obsABAbab - expABAbab)-0.5,2))/expABAbab);
            }
            agree.put("chiInd", (chiInd >= 3.841 ? "No" : "Yes"));

            resultValues.put("total" , total);
            expectedValues.put("expaB" , expaB);
            expectedValues.put("expABAbab" , expABAbab);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}

    public CTResult segregation6334(int obs6,int obs3a,int obs3b, int obs4) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obs6<=5000 && obs3a<=5000 && obs3b<=5000 && obs4<=5000){
            double total = obs6+obs3a+obs3b+obs4;
            double exp6 = total * 6/16.0;
            double exp3a = total * 3/16.0;
            double exp3b = total * 3/16.0;
            double exp4 = total * 4/16.0;
            double chiInd= 0;
            if(exp6>10 && exp3a>10 && exp3b>10 && exp4>10){
                chiInd = ((Math.pow((obs6 - exp6),2))/exp6)+((Math.pow((obs3a - exp3a),2))/exp3a)+((Math.pow((obs3b - exp3b),2))/exp3b)+((Math.pow((obs4 - exp4),2))/exp4);
            }
            if(exp6 <5 || exp3a <5 || exp3b <5 || exp4 <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((exp6>=5 && exp6<=10) || (exp3a>=5 && exp3a<=10) || (exp3b>=5 && exp3b<=10) || (exp4>=5 && exp4<=10)){
                chiInd = ((Math.pow((obs6 - exp6)-0.5,2))/exp6)+((Math.pow((obs3a - exp3a)-0.5,2))/exp3a)+((Math.pow((obs3b - exp3b)-0.5,2))/exp3b)+((Math.pow((obs4 - exp4)-0.5,2))/exp4);
            }
            agree.put("chiInd", (chiInd >= 7.82 ? "No" : "Yes"));

            resultValues.put("total" , total);
            expectedValues.put("exp6" , exp6);
            expectedValues.put("exp3a" , exp3a);
            expectedValues.put("exp3b" , exp3b);
            expectedValues.put("exp4" , exp4);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}

    public CTResult segregation1033(int obs10,int obs3a,int obs3b) {
       Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if(obs10<=5000 && obs3a<=5000 && obs3b<=5000){
            double total = obs10+obs3a+obs3b;
            double exp10 = total * 10/16.0;
            double exp3a = total * 3/16.0;
            double exp3b = total * 3/16.0;
            double chiInd= 0;
            if(exp10>10 && exp3a>10 && exp3b>10){
                chiInd = ((Math.pow((obs10 - exp10),2))/exp10)+((Math.pow((obs3a - exp3a),2))/exp3a)+((Math.pow((obs3b - exp3b),2))/exp3b);
            }
            if(exp10 <5 || exp3a <5 || exp3b <5){
                return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
            }
            if((exp10>=5 && exp10<=10) || (exp3a>=5 && exp3a<=10) || (exp3b>=5 && exp3b<=10)){
                chiInd = ((Math.pow((obs10 - exp10)-0.5,2))/exp10)+((Math.pow((obs3a - exp3a)-0.5,2))/exp3a)+((Math.pow((obs3b - exp3b)-0.5,2))/exp3b);
            }
            agree.put("chiInd", (chiInd >= 5.99 ? "No" : "Yes"));

            resultValues.put("total" , total);
            expectedValues.put("exp10" , exp10);
            expectedValues.put("exp3a" , exp3a);
            expectedValues.put("exp3b" , exp3b);
            resultValues.put("chiInd" , chiInd);

        } else {
            return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 5000").build();
        }
        return CTResult.builder()
				.cleanInputs(false)
				.expectedValues(expectedValues)
				.resultValues(resultValues)
				.result(result)
				.agree(agree)
				.observed(observedValues)
				.phase(phases)
				.build();
	}
}
