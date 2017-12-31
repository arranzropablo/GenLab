package com.genlab.serverapplication.services.ctservice;

import com.genlab.serverapplication.viewobjects.CTResult;

import java.util.HashMap;
import java.util.Map;

public class CTTwoLoci {

    public CTResult testcross(int obsAB, int obsAb, int obsaB, int obsab) {
        //Cosas que haríamos en el controlador:
        //Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
        //Validar que sea > 5
        //Sumar para poner total en el propio controlador
        //Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
        int totalobs = obsAB + obsAb + obsaB + obsab,
            totalA = obsAB + obsAb,
            totala = obsaB + obsab,
            totalB = obsAB + obsaB,
            totalb = obsAb + obsab;

        //TODO Comprobar que se guarda con decimales (estos con un decimal)
        double expectedAlelos = totalobs / 2;

        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A", (double) totalA);
        expValues.put("B", (double) totalB);
        expValues.put("a", (double) totala);
        expValues.put("b", (double) totalb);
        expValues.put("expectedglobal", expectedAlelos);

        double chiAa = (Math.pow(totalA - expectedAlelos, 2)/expectedAlelos) + (Math.pow(totala - expectedAlelos, 2)/expectedAlelos);
        double chiBb = (Math.pow(totalB - expectedAlelos, 2)/expectedAlelos) + (Math.pow(totalb - expectedAlelos, 2)/expectedAlelos);

        if(chiAa < 3.84 && chiBb < 3.84) {
            int expectedInd = totalobs / 4;
            if(expectedInd < 5) {
                //warning y borrar
            } else if (expectedInd >= 5 && expectedInd <= 10){
                //warning
                double chiInd = (Math.pow((obsAB - expectedInd) - 0.5, 2)/expectedInd) + (Math.pow((obsaB - expectedInd) - 0.5, 2)/expectedInd) + (Math.pow((obsAb - expectedInd) - 0.5, 2)/expectedInd) + (Math.pow((obsab - expectedInd) - 0.5, 2)/expectedInd);
            } else {
                double chiInd = (Math.pow(obsAB - expectedInd, 2)/expectedInd) + (Math.pow(obsaB - expectedInd, 2)/expectedInd) + (Math.pow(obsAb - expectedInd, 2)/expectedInd) + (Math.pow(obsab - expectedInd, 2)/expectedInd);
                if (chiInd > 7.81) {
                    int expectedContAB = totalA * totalB / totalobs,
                        expectedContAb = totalA * totalb / totalobs,
                        expectedContaB = totala * totalB / totalobs,
                        expectedContab = totala * totalb / totalobs;
                    expValues.put("contAB", (double) expectedContAB);
                    expValues.put("contAb", (double) expectedContAb);
                    expValues.put("contaB", (double) expectedContaB);
                    expValues.put("contab", (double) expectedContab);
                    //TODO HAY QUE COMPROBAR EN TODOS LOS CT QUE SE HAGA EL CLEAN CUANDO DEBE
                    if(expectedContAB < 5 || expectedContAb < 5 || expectedContaB < 5 || expectedContab < 5){
                        //warning y clean
                    } else if((expectedContAB >= 5 && expectedContAB <= 10) || (expectedContAb >= 5 && expectedContAb <= 10) || (expectedContaB >= 5 && expectedContaB <= 10) || (expectedContab >= 5 && expectedContab <= 10)){
                        //warning
                        double chiCont = (Math.pow((obsAB - expectedContAB) - 0.5, 2)/expectedContAB) + (Math.pow((obsaB - expectedContaB) - 0.5, 2)/expectedContaB) + (Math.pow((obsAb - expectedContAb) - 0.5, 2)/expectedContAb) + (Math.pow((obsab - expectedContab) - 0.5, 2)/expectedContab);
                    } else {
                        double chiCont = (Math.pow(obsAB - expectedContAB, 2)/expectedContAB) + (Math.pow(obsaB - expectedContaB, 2)/expectedContaB) + (Math.pow(obsAb - expectedContAb, 2)/expectedContAb) + (Math.pow(obsab - expectedContab, 2)/expectedContab);
                    }
                }
            }
            expValues.put("expectedInd", (double) expectedInd);
        } else {
            int expectedContAB = totalA * totalB / totalobs,
                expectedContAb = totalA * totalb / totalobs,
                expectedContaB = totala * totalB / totalobs,
                expectedContab = totala * totalb / totalobs;
            expValues.put("contAB", (double) expectedContAB);
            expValues.put("contAb", (double) expectedContAb);
            expValues.put("contaB", (double) expectedContaB);
            expValues.put("contab", (double) expectedContab);
            if(expectedContAB < 5 || expectedContAb < 5 || expectedContaB < 5 || expectedContab < 5){
                //warning y clean
            } else if((expectedContAB >= 5 && expectedContAB <= 10) || (expectedContAb >= 5 && expectedContAb <= 10) || (expectedContaB >= 5 && expectedContaB <= 10) || (expectedContab >= 5 && expectedContab <= 10)){
                //warning
                double chiCont = (Math.pow((obsAB - expectedContAB) - 0.5, 2)/expectedContAB) + (Math.pow((obsaB - expectedContaB) - 0.5, 2)/expectedContaB) + (Math.pow((obsAb - expectedContAb) - 0.5, 2)/expectedContAb) + (Math.pow((obsab - expectedContab) - 0.5, 2)/expectedContab);
            } else {
                double chiCont = (Math.pow(obsAB - expectedContAB, 2)/expectedContAB) + (Math.pow(obsaB - expectedContaB, 2)/expectedContaB) + (Math.pow(obsAb - expectedContAb, 2)/expectedContAb) + (Math.pow(obsab - expectedContab, 2)/expectedContab);
            }
        }

        return null;
    }
}
