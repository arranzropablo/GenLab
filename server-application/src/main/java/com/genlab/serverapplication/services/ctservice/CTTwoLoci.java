package com.genlab.serverapplication.services.ctservice;

import com.genlab.serverapplication.models.CTResult;

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
                    double expectedContAB = totalA * totalB / totalobs,
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
            double expectedContAB = totalA * totalB / totalobs,
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

    public CTResult f2Dominance(int obsAB, int obsAb, int obsaB, int obsab) {
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

        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A", (double) totalobs * 3/4);
        expValues.put("B", (double) totalobs * 3/4);
        expValues.put("a", (double) totalobs / 4);
        expValues.put("b", (double) totalobs / 4);

        double chiAa = (Math.pow(totalA - totalobs * 3/4, 2)/ (totalobs * 3/4)) + (Math.pow(totala - totalobs / 4, 2)/ (totalobs / 4));
        double chiBb = (Math.pow(totalB - totalobs * 3/4, 2)/ (totalobs * 3/4)) + (Math.pow(totalb - totalobs / 4, 2)/ (totalobs / 4));

        if(chiAa < 3.84 && chiBb < 3.84) {
            double expectedIndAB = totalobs * 9/16,
                expectedIndAb = totalobs * 3/16,
                expectedIndaB = totalobs * 3/16,
                expectedIndab = totalobs * 1/16;
            if(expectedIndAB < 5 || expectedIndAb < 5 || expectedIndaB < 5 || expectedIndab < 5) {
                //warning y borrar
            } else if ((expectedIndAB >= 5 && expectedIndAB <= 10) ||
                        (expectedIndAb >= 5 && expectedIndAb <= 10) ||
                        (expectedIndaB >= 5 && expectedIndaB <= 10) ||
                        (expectedIndab >= 5 && expectedIndab <= 10)){
                //warning
                double chiInd = (Math.pow((obsAB - expectedIndAB) - 0.5, 2)/expectedIndAB) + (Math.pow((obsaB - expectedIndAb) - 0.5, 2)/expectedIndAb) + (Math.pow((obsAb - expectedIndaB) - 0.5, 2)/expectedIndaB) + (Math.pow((obsab - expectedIndab) - 0.5, 2)/expectedIndab);
            } else {
                double chiInd = (Math.pow(obsAB - expectedIndAB, 2)/expectedIndAB) + (Math.pow(obsaB - expectedIndAb, 2)/expectedIndAb) + (Math.pow(obsAb - expectedIndaB, 2)/expectedIndaB) + (Math.pow(obsab - expectedIndab, 2)/expectedIndab);
                if (chiInd > 7.81) {
                    double expectedContAB = totalA * totalB / totalobs,
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
            }
            expValues.put("expectedIndAB", (double) expectedIndAB);
            expValues.put("expectedIndAb", (double) expectedIndAb);
            expValues.put("expectedIndaB", (double) expectedIndaB);
            expValues.put("expectedIndab", (double) expectedIndab);
        } else {
            double expectedContAB = totalA * totalB / totalobs,
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

    public CTResult f2coDominance(int obsA1A1B1B1, int obsA1A1B1B2, int obsA1A1B2B2, int obsA1A2B1B1, int obsA1A2B1B2, int obsA1A2B2B2, int obsA2A2B1B1, int obsA2A2B1B2, int obsA2A2B2B2) {
        //Cosas que haríamos en el controlador:
        //Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
        //Validar que sea > 5
        //Sumar para poner total en el propio controlador
        //Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
        int totalobs = obsA1A1B1B1 + obsA1A1B1B2 + obsA1A1B2B2 + obsA1A2B1B1 + obsA1A2B1B2 + obsA1A2B2B2 + obsA2A2B1B1 + obsA2A2B1B2 + obsA2A2B2B2,
                totalA1 = obsA1A1B1B1 + obsA1A1B1B2 + obsA1A1B2B2,
                totalA2 = obsA2A2B1B1 + obsA2A2B1B2 + obsA2A2B2B2,
                totalA1A2 = obsA1A2B1B1 + obsA1A2B1B2 + obsA1A2B2B2,
                totalB1 = obsA1A1B1B1 + obsA1A2B1B1 + obsA2A2B1B1,
                totalB2 = obsA1A1B2B2 + obsA1A2B2B2 + obsA2A2B2B2,
                totalB1B2 = obsA1A1B1B2 + obsA1A2B1B2 + obsA2A2B1B2;

        int totalloci = totalA1 + totalA2 + totalA1A2;
        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A1", (double) totalobs /4);
        expValues.put("A2", (double) totalobs /4);
        expValues.put("A1A2", (double) totalobs * 2/4);
        expValues.put("B1", (double) totalobs /4);
        expValues.put("B2", (double) totalobs /4);
        expValues.put("B1B2", (double) totalobs * 2/4);

        double chiA = (Math.pow(totalA1 - totalobs /4, 2)/ (totalobs /4)) + (Math.pow(totalA2 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalA1A2 - totalobs * 2/4, 2) / (totalobs * 2 / 4));
        double chiB = (Math.pow(totalB1 - totalobs /4, 2)/ (totalobs /4)) + (Math.pow(totalB2 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalB1B2 - totalobs * 2/4, 2) / (totalobs * 2 / 4));

        if(chiA < 5.99 && chiB < 5.99) {
            double expectedIndA1B1 = totalobs * 1 / 16,
                    expectedIndA1B2 = totalobs * 1 / 16,
                    expectedIndA1B1B2 = totalobs * 2 / 16,
                    expectedIndA2B1 = totalobs * 1 / 16,
                    expectedIndA2B2 = totalobs * 1 / 16,
                    expectedIndA2B1B2 = totalobs * 2 / 16,
                    expectedIndA1A2B1 = totalobs * 2 / 16,
                    expectedIndA1A2B2 = totalobs * 2 / 16,
                    expectedIndA1A2B1B2 = totalobs * 4 / 16;

            expValues.put("expectedIndA1B1", expectedIndA1B1);
            expValues.put("expectedIndA1B2", expectedIndA1B2);
            expValues.put("expectedIndA1B1B2", expectedIndA1B1B2);
            expValues.put("expectedIndA2B1", expectedIndA2B1);
            expValues.put("expectedIndA2B2", expectedIndA2B2);
            expValues.put("expectedIndA2B1B2", expectedIndA2B1B2);
            expValues.put("expectedIndA1A2B1", expectedIndA1A2B1);
            expValues.put("expectedIndA1A2B2", expectedIndA1A2B2);
            expValues.put("expectedIndA1A2B1B2", expectedIndA1A2B1B2);

            if (expectedIndA1B1 < 5 || expectedIndA1B2 < 5 || expectedIndA1B1B2 < 5 || expectedIndA2B1 < 5 || expectedIndA2B2 < 5 || expectedIndA2B1B2 < 5 || expectedIndA1A2B1 < 5 || expectedIndA1A2B2 < 5 || expectedIndA1A2B1B2 < 5) {
                //warning y borrar
            } else {
                double chiInd = (Math.pow(obsA1A1B1B1 - expectedIndA1B1, 2) / expectedIndA1B1) + (Math.pow(obsA1A1B2B2 - expectedIndA1B2, 2) / expectedIndA1B2) + (Math.pow(obsA1A1B1B2 - expectedIndA1B1B2, 2) / expectedIndA1B1B2) + (Math.pow(obsA2A2B1B1 - expectedIndA2B1, 2) / expectedIndA2B1) + (Math.pow(obsA2A2B2B2 - expectedIndA2B2, 2) / expectedIndA2B2) + (Math.pow(obsA2A2B1B2 - expectedIndA2B1B2, 2) / expectedIndA2B1B2) + (Math.pow(obsA1A2B1B1 - expectedIndA1A2B1, 2) / expectedIndA1A2B1) + (Math.pow(obsA1A2B2B2 - expectedIndA1A2B2, 2) / expectedIndA1A2B2) + (Math.pow(obsA1A2B1B2 - expectedIndA1A2B1B2, 2) / expectedIndA1A2B1B2);
                if (chiInd > 15.51) {
                    double expectedContA1B1 = totalA1 * totalB1 / totalobs,
                            expectedContA1B2 = totalA1 * totalB2 / totalobs,
                            expectedContA1B1B2 = totalA1 * totalB1B2 / totalobs,
                            expectedContA2B1 = totalA2 * totalB1 / totalobs,
                            expectedContA2B2 = totalA2 * totalB2 / totalobs,
                            expectedContA2B1B2 = totalA2 * totalB1B2 / totalobs,
                            expectedContA1A2B1 = totalA1A2 * totalB1 / totalobs,
                            expectedContA1A2B2 = totalA1A2 * totalB2 / totalobs,
                            expectedContA1A2B1B2 = totalA1A2 * totalB1B2 / totalobs;

                    expValues.put("contA1B1", expectedContA1B1);
                    expValues.put("contA1B2", expectedContA1B2);
                    expValues.put("contA1B1B2", expectedContA1B1B2);
                    expValues.put("contA2B1", expectedContA2B1);
                    expValues.put("contA2B2", expectedContA2B2);
                    expValues.put("contA2B1B2", expectedContA2B1B2);
                    expValues.put("contA1A2B1", expectedContA1A2B1);
                    expValues.put("contA1A2B2", expectedContA1A2B2);
                    expValues.put("contA1A2B1B2", expectedContA1A2B1B2);

                    if (expectedContA1B1 < 5 || expectedContA1B2 < 5 || expectedContA1B1B2 < 5 || expectedContA2B1 < 5 || expectedContA2B2 < 5 || expectedContA2B1B2 < 5 || expectedContA1A2B1 < 5 || expectedContA1A2B2 < 5 || expectedContA1A2B1B2 < 5) {
                        //warning y clean
                    } else {
                        if ((expectedIndA1B1 >= 5 && expectedIndA1B1 <= 10) || (expectedIndA1B2 >= 5 && expectedIndA1B2 <= 10) || (expectedIndA1B1B2 >= 5 && expectedIndA1B1B2 <= 10) || (expectedIndA1A2B1 >= 5 && expectedIndA1A2B1 <= 10) || (expectedIndA1A2B1B2 >= 5 && expectedIndA1A2B1B2 <= 10) || (expectedIndA1A2B2 >= 5 && expectedIndA1A2B2 <= 10) || (expectedIndA2B1 >= 5 && expectedIndA2B1 <= 10) || (expectedIndA2B1B2 >= 5 && expectedIndA2B1B2 <= 10) || (expectedIndA2B2 >= 5 && expectedIndA2B2 <= 10)) {
                            //warning
                            chiInd = (Math.pow((obsA1A1B1B1 - expectedIndA1B1) - 0.5, 2) / expectedIndA1B1) + (Math.pow((obsA1A1B2B2 - expectedIndA1B2) - 0.5, 2) / expectedIndA1B2) + (Math.pow((obsA1A1B1B2 - expectedIndA1B1B2) - 0.5, 2) / expectedIndA1B1B2) + (Math.pow((obsA2A2B1B1 - expectedIndA2B1) - 0.5, 2) / expectedIndA2B1) + (Math.pow((obsA2A2B2B2 - expectedIndA2B2) - 0.5, 2) / expectedIndA2B2) + (Math.pow((obsA2A2B1B2 - expectedIndA2B1B2) - 0.5, 2) / expectedIndA2B1B2) + (Math.pow((obsA1A2B1B1 - expectedIndA1A2B1) - 0.5, 2) / expectedIndA1A2B1) + (Math.pow((obsA1A2B2B2 - expectedIndA1A2B2) - 0.5, 2) / expectedIndA1A2B2) + (Math.pow((obsA1A2B1B2 - expectedIndA1A2B1B2) - 0.5, 2) / expectedIndA1A2B1B2);

                        } else {
                            chiInd = (Math.pow(obsA1A1B1B1 - expectedIndA1B1, 2) / expectedIndA1B1) + (Math.pow(obsA1A1B2B2 - expectedIndA1B2, 2) / expectedIndA1B2) + (Math.pow(obsA1A1B1B2 - expectedIndA1B1B2, 2) / expectedIndA1B1B2) + (Math.pow(obsA2A2B1B1 - expectedIndA2B1, 2) / expectedIndA2B1) + (Math.pow(obsA2A2B2B2 - expectedIndA2B2, 2) / expectedIndA2B2) + (Math.pow(obsA2A2B1B2 - expectedIndA2B1B2, 2) / expectedIndA2B1B2) + (Math.pow(obsA1A2B1B1 - expectedIndA1A2B1, 2) / expectedIndA1A2B1) + (Math.pow(obsA1A2B2B2 - expectedIndA1A2B2, 2) / expectedIndA1A2B2) + (Math.pow(obsA1A2B1B2 - expectedIndA1A2B1B2, 2) / expectedIndA1A2B1B2);
                            if (chiInd > 14.07) {
                                if (expectedContA1B1 < 5 || expectedContA1B2 < 5 || expectedContA1B1B2 < 5 || expectedContA2B1 < 5 || expectedContA2B2 < 5 || expectedContA2B1B2 < 5 || expectedContA1A2B1 < 5 || expectedContA1A2B2 < 5 || expectedContA1A2B1B2 < 5) {
                                    //warning y clean (esto no esta repetido??!?!?!?!?)
                                } else {
                                    chiInd = (Math.pow((obsA1A1B1B1 - expectedIndA1B1) - 0.5, 2) / expectedIndA1B1) + (Math.pow((obsA1A1B2B2 - expectedIndA1B2) - 0.5, 2) / expectedIndA1B2) + (Math.pow((obsA1A1B1B2 - expectedIndA1B1B2) - 0.5, 2) / expectedIndA1B1B2) + (Math.pow((obsA2A2B1B1 - expectedIndA2B1) - 0.5, 2) / expectedIndA2B1) + (Math.pow((obsA2A2B2B2 - expectedIndA2B2) - 0.5, 2) / expectedIndA2B2) + (Math.pow((obsA2A2B1B2 - expectedIndA2B1B2) - 0.5, 2) / expectedIndA2B1B2) + (Math.pow((obsA1A2B1B1 - expectedIndA1A2B1) - 0.5, 2) / expectedIndA1A2B1) + (Math.pow((obsA1A2B2B2 - expectedIndA1A2B2) - 0.5, 2) / expectedIndA1A2B2) + (Math.pow((obsA1A2B1B2 - expectedIndA1A2B1B2) - 0.5, 2) / expectedIndA1A2B1B2);
                                }
                            } else {
                                //limpiar to lo de cont
                            }
                        }
                        double chiCont = (Math.pow(obsA1A1B1B1 - expectedContA1B1, 2) / expectedContA1B1) + (Math.pow(obsA1A1B2B2 - expectedContA1B2, 2) / expectedContA1B2) + (Math.pow(obsA1A1B1B2 - expectedContA1B1B2, 2) / expectedContA1B1B2) + (Math.pow(obsA2A2B1B1 - expectedContA2B1, 2) / expectedContA2B1) + (Math.pow(obsA2A2B2B2 - expectedContA2B2, 2) / expectedContA2B2) + (Math.pow(obsA2A2B1B2 - expectedContA2B1B2, 2) / expectedContA2B1B2) + (Math.pow(obsA1A2B1B1 - expectedContA1A2B1, 2) / expectedContA1A2B1) + (Math.pow(obsA1A2B2B2 - expectedContA1A2B2, 2) / expectedContA1A2B2) + (Math.pow(obsA1A2B1B2 - expectedContA1A2B1B2, 2) / expectedContA1A2B1B2);
                    }
                } else {
                    //limpiar to lo de cont
                }

            }
        } else {
            double expectedContA1B1 = totalA1 * totalB1 / totalobs,
                    expectedContA1B2 = totalA1 * totalB2 / totalobs,
                    expectedContA1B1B2 = totalA1 * totalB1B2 / totalobs,
                    expectedContA2B1 = totalA2 * totalB1 / totalobs,
                    expectedContA2B2 = totalA2 * totalB2 / totalobs,
                    expectedContA2B1B2 = totalA2 * totalB1B2 / totalobs,
                    expectedContA1A2B1 = totalA1A2 * totalB1 / totalobs,
                    expectedContA1A2B2 = totalA1A2 * totalB2 / totalobs,
                    expectedContA1A2B1B2 = totalA1A2 * totalB1B2 / totalobs;

            expValues.put("contA1B1", expectedContA1B1);
            expValues.put("contA1B2", expectedContA1B2);
            expValues.put("contA1B1B2", expectedContA1B1B2);
            expValues.put("contA2B1", expectedContA2B1);
            expValues.put("contA2B2", expectedContA2B2);
            expValues.put("contA2B1B2", expectedContA2B1B2);
            expValues.put("contA1A2B1", expectedContA1A2B1);
            expValues.put("contA1A2B2", expectedContA1A2B2);
            expValues.put("contA1A2B1B2", expectedContA1A2B1B2);

            if (expectedContA1B1 < 5 || expectedContA1B2 < 5 || expectedContA1B1B2 < 5 || expectedContA2B1 < 5 || expectedContA2B2 < 5 || expectedContA2B1B2 < 5 || expectedContA1A2B1 < 5 || expectedContA1A2B2 < 5 || expectedContA1A2B1B2 < 5) {
                //warning y clean
            } else if ((expectedContA1B1 >= 5 && expectedContA1B1 <= 10) || (expectedContA1B2 >= 5 && expectedContA1B2 <= 10) || (expectedContA1B1B2 >= 5 && expectedContA1B1B2 <= 10) || (expectedContA1A2B1 >= 5 && expectedContA1A2B1 <= 10) || (expectedContA1A2B1B2 >= 5 && expectedContA1A2B1B2 <= 10) || (expectedContA1A2B2 >= 5 && expectedContA1A2B2 <= 10) || (expectedContA2B1 >= 5 && expectedContA2B1 <= 10) || (expectedContA2B1B2 >= 5 && expectedContA2B1B2 <= 10) || (expectedContA2B2 >= 5 && expectedContA2B2 <= 10)) {
                //warning
                double chiCont = (Math.pow((obsA1A1B1B1 - expectedContA1B1) - 0.5, 2) / expectedContA1B1) + (Math.pow((obsA1A1B2B2 - expectedContA1B2) - 0.5, 2) / expectedContA1B2) + (Math.pow((obsA1A1B1B2 - expectedContA1B1B2) - 0.5, 2) / expectedContA1B1B2) + (Math.pow((obsA2A2B1B1 - expectedContA2B1) - 0.5, 2) / expectedContA2B1) + (Math.pow((obsA2A2B2B2 - expectedContA2B2) - 0.5, 2) / expectedContA2B2) + (Math.pow((obsA2A2B1B2 - expectedContA2B1B2) - 0.5, 2) / expectedContA2B1B2) + (Math.pow((obsA1A2B1B1 - expectedContA1A2B1) - 0.5, 2) / expectedContA1A2B1) + (Math.pow((obsA1A2B2B2 - expectedContA1A2B2) - 0.5, 2) / expectedContA1A2B2) + (Math.pow((obsA1A2B1B2 - expectedContA1A2B1B2) - 0.5, 2) / expectedContA1A2B1B2);
            } else {
                double chiCont = (Math.pow(obsA1A1B1B1 - expectedContA1B1, 2) / expectedContA1B1) + (Math.pow(obsA1A1B2B2 - expectedContA1B2, 2) / expectedContA1B2) + (Math.pow(obsA1A1B1B2 - expectedContA1B1B2, 2) / expectedContA1B1B2) + (Math.pow(obsA2A2B1B1 - expectedContA2B1, 2) / expectedContA2B1) + (Math.pow(obsA2A2B2B2 - expectedContA2B2, 2) / expectedContA2B2) + (Math.pow(obsA2A2B1B2 - expectedContA2B1B2, 2) / expectedContA2B1B2) + (Math.pow(obsA1A2B1B1 - expectedContA1A2B1, 2) / expectedContA1A2B1) + (Math.pow(obsA1A2B2B2 - expectedContA1A2B2, 2) / expectedContA1A2B2) + (Math.pow(obsA1A2B1B2 - expectedContA1A2B1B2, 2) / expectedContA1A2B1B2);
            }
        }
        return null;
    }

    public CTResult f2coDom2dom(int obsA1A1B, int obsA1A2B, int obsA2A2B, int obsA1A1b, int obsA1A2b, int obsA2A2b) {
        //Cosas que haríamos en el controlador:
        //Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
        //Validar que sea > 5
        //Sumar para poner total en el propio controlador
        //Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
        int totalobs = obsA1A1B + obsA1A2B + obsA2A2B + obsA1A1b + obsA1A2b + obsA2A2b,
                totalA1 = obsA1A1B + obsA1A1b,
                totalA2 = obsA2A2B + obsA2A2b,
                totalA1A2 = obsA1A2B + obsA1A2b,
                totalB = obsA1A1B + obsA1A2B + obsA2A2B,
                totalb = obsA1A1b + obsA1A2b + obsA2A2b;

        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A1", (double) totalobs / 4);
        expValues.put("A2", (double) totalobs / 4);
        expValues.put("A1A2", (double) totalobs * 2/4);
        expValues.put("B", (double) totalobs * 3/4);
        expValues.put("b", (double) totalobs /4);

        double chiA1A2 = (Math.pow(totalA1 - totalobs /4, 2)/ (totalobs /4)) + (Math.pow(totalA2 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalA1A2 - totalobs * 2/4, 2) / (totalobs * 2 / 4));
        double chiBb = (Math.pow(totalB - totalobs * 3/4, 2)/ (totalobs * 3/4)) + (Math.pow(totalb - totalobs / 4, 2)/ (totalobs /4));

        if(chiA1A2 < 5.99 && chiBb < 5.99) {
            double expectedIndA1B = totalobs * 3 / 16,
                    expectedIndA1b = totalobs * 1 / 16,
                    expectedIndA2B = totalobs * 3 / 16,
                    expectedIndA2b = totalobs * 1 / 16,
                    expectedIndA1A2B = totalobs * 6 / 16,
                    expectedIndA1A2b = totalobs * 2 / 16;

            expValues.put("expectedIndA1B", expectedIndA1B);
            expValues.put("expectedIndA1b", expectedIndA1b);
            expValues.put("expectedIndA2B", expectedIndA2B);
            expValues.put("expectedIndA2b", expectedIndA2b);
            expValues.put("expectedIndA1A2B", expectedIndA1A2B);
            expValues.put("expectedIndA1A2b", expectedIndA1A2b);

            if (expectedIndA1B < 5 || expectedIndA1b < 5 || expectedIndA2B < 5 || expectedIndA2b < 5 || expectedIndA1A2B < 5 || expectedIndA1A2b < 5) {
                //warning y borrar
            } else {
                double chiInd = (Math.pow(obsA1A1B - expectedIndA1B, 2) / expectedIndA1B) + (Math.pow(obsA1A1b - expectedIndA1b, 2) / expectedIndA1b) + (Math.pow(obsA2A2B - expectedIndA2B, 2) / expectedIndA2B) + (Math.pow(obsA2A2b - expectedIndA2b, 2) / expectedIndA2b) + (Math.pow(obsA1A2B - expectedIndA1A2B, 2) / expectedIndA1A2B) + (Math.pow(obsA1A2b - expectedIndA1A2b, 2) / expectedIndA1A2b);
                if (chiInd > 11.07) {
                    double expectedContA1B = totalA1 * totalB / totalobs,
                            expectedContA1b = totalA1 * totalb / totalobs,
                            expectedContA2B = totalA2 * totalB / totalobs,
                            expectedContA2b = totalA2 * totalb / totalobs,
                            expectedContA1A2B = totalA1A2 * totalB / totalobs,
                            expectedContA1A2b = totalA1A2 * totalb / totalobs;

                    expValues.put("contA1B", expectedContA1B);
                    expValues.put("contA1b", expectedContA1b);
                    expValues.put("contA2B", expectedContA2B);
                    expValues.put("contA2b", expectedContA2b);
                    expValues.put("contA1A2B", expectedContA1A2B);
                    expValues.put("contA1A2b", expectedContA1A2b);

                    if (expectedContA1B < 5 || expectedContA1b < 5 || expectedContA2B < 5 || expectedContA2b < 5 || expectedContA1A2B < 5 || expectedContA1A2b < 5){
                        //warning y clean
                    } else {
                        if ((expectedContA1B >= 5 && expectedContA1B <= 10) || (expectedContA1b >= 5 && expectedContA1b <= 10) || (expectedContA2B >= 5 && expectedContA2B <= 10) || (expectedContA2b >= 5 && expectedContA2b <= 10) || (expectedContA1A2B >= 5 && expectedContA1A2B <= 10) || (expectedContA1A2b >= 5 && expectedContA1A2b <= 10)) {
                            //warning
                            chiInd = (Math.pow((obsA1A1B - expectedIndA1B) - 0.5, 2) / expectedIndA1B) + (Math.pow((obsA1A1b - expectedIndA1b) - 0.5, 2) / expectedIndA1b) + (Math.pow((obsA2A2B - expectedIndA2B) - 0.5, 2) / expectedIndA2B) + (Math.pow((obsA2A2b - expectedIndA2b) - 0.5, 2) / expectedIndA2b) + (Math.pow((obsA1A2B - expectedIndA1A2B) - 0.5, 2) / expectedIndA1A2B) + (Math.pow((obsA1A2b - expectedIndA1A2b) - 0.5, 2) / expectedIndA1A2b);

                        } else {
                            //esto ya esta calculado arriba???
                            chiInd = (Math.pow(obsA1A1B - expectedIndA1B, 2) / expectedIndA1B) + (Math.pow(obsA1A1b - expectedIndA1b, 2) / expectedIndA1b) + (Math.pow(obsA2A2B - expectedIndA2B, 2) / expectedIndA2B) + (Math.pow(obsA2A2b - expectedIndA2b, 2) / expectedIndA2b) + (Math.pow(obsA1A2B - expectedIndA1A2B, 2) / expectedIndA1A2B) + (Math.pow(obsA1A2b - expectedIndA1A2b, 2) / expectedIndA1A2b);
                            if (chiInd > 14.07) {
                                if (expectedContA1B < 5 || expectedContA1b < 5 || expectedContA2B < 5 || expectedContA2b < 5 || expectedContA1A2B < 5 || expectedContA1A2b < 5){
                                    //warning y clean (esto no esta repetido??!?!?!?!?)
                                } else {
                                    chiInd = (Math.pow((obsA1A1B - expectedIndA1B) - 0.5, 2) / expectedIndA1B) + (Math.pow((obsA1A1b - expectedIndA1b) - 0.5, 2) / expectedIndA1b) + (Math.pow((obsA2A2B - expectedIndA2B) - 0.5, 2) / expectedIndA2B) + (Math.pow((obsA2A2b - expectedIndA2b) - 0.5, 2) / expectedIndA2b) + (Math.pow((obsA1A2B - expectedIndA1A2B) - 0.5, 2) / expectedIndA1A2B) + (Math.pow((obsA1A2b - expectedIndA1A2b) - 0.5, 2) / expectedIndA1A2b);
                                }
                            } else {
                                //limpiar to lo de cont
                            }
                        }
                        double chiCont = (Math.pow(obsA1A1B - expectedContA1B, 2) / expectedContA1B) + (Math.pow(obsA1A1b - expectedContA1b, 2) / expectedContA1b) + (Math.pow(obsA2A2B - expectedContA2B, 2) / expectedContA2B) + (Math.pow(obsA2A2b - expectedContA2b, 2) / expectedContA2b) + (Math.pow(obsA1A2B - expectedContA1A2B, 2) / expectedContA1A2B) + (Math.pow(obsA1A2b - expectedContA1A2b, 2) / expectedContA1A2b);
                    }
                } else {
                    //limpiar to lo de cont
                }

            }
        } else {
            double expectedContA1B = totalA1 * totalB / totalobs,
                    expectedContA1b = totalA1 * totalb / totalobs,
                    expectedContA2B = totalA2 * totalB / totalobs,
                    expectedContA2b = totalA2 * totalb / totalobs,
                    expectedContA1A2B = totalA1A2 * totalB / totalobs,
                    expectedContA1A2b = totalA1A2 * totalb / totalobs;

            expValues.put("contA1B", expectedContA1B);
            expValues.put("contA1b", expectedContA1b);
            expValues.put("contA2B", expectedContA2B);
            expValues.put("contA2b", expectedContA2b);
            expValues.put("contA1A2B", expectedContA1A2B);
            expValues.put("contA1A2b", expectedContA1A2b);

            if (expectedContA1B < 5 || expectedContA1b < 5 || expectedContA2B < 5 || expectedContA2b < 5 || expectedContA1A2B < 5 || expectedContA1A2b < 5){
                //warning y clean
            } else{
                if ((expectedContA1B >= 5 && expectedContA1B <= 10) || (expectedContA1b >= 5 && expectedContA1b <= 10) || (expectedContA2B >= 5 && expectedContA2B <= 10) || (expectedContA2b >= 5 && expectedContA2b <= 10) || (expectedContA1A2B >= 5 && expectedContA1A2B <= 10) || (expectedContA1A2b >= 5 && expectedContA1A2b <= 10)) {
                    //warning
                    double chiCont = (Math.pow((obsA1A1B - expectedContA1B) - 0.5, 2) / expectedContA1B) + (Math.pow((obsA1A1b - expectedContA1b) - 0.5, 2) / expectedContA1b) + (Math.pow((obsA2A2B - expectedContA2B) - 0.5, 2) / expectedContA2B) + (Math.pow((obsA2A2b - expectedContA2b) - 0.5, 2) / expectedContA2b) + (Math.pow((obsA1A2B - expectedContA1A2B) - 0.5, 2) / expectedContA1A2B) + (Math.pow((obsA1A2b - expectedContA1A2b) - 0.5, 2) / expectedContA1A2b);
                } else {
                    double chiCont = (Math.pow(obsA1A1B - expectedContA1B, 2) / expectedContA1B) + (Math.pow(obsA1A1b - expectedContA1b, 2) / expectedContA1b) + (Math.pow(obsA2A2B - expectedContA2B, 2) / expectedContA2B) + (Math.pow(obsA2A2b - expectedContA2b, 2) / expectedContA2b) + (Math.pow(obsA1A2B - expectedContA1A2B, 2) / expectedContA1A2B) + (Math.pow(obsA1A2b - expectedContA1A2b, 2) / expectedContA1A2b);
                }
            }
        }
        return null;
    }

    public CTResult f2coDom4dom(int obsA1A3B, int obsA1A3b, int obsA1A4B, int obsA1A4b, int obsA2A3B, int obsA2A3b, int obsA2A4B, int obsA2A4b) {
        //Cosas que haríamos en el controlador:
        //Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
        //Validar que sea > 5
        //Sumar para poner total en el propio controlador
        //Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
        int totalobs = obsA1A3B + obsA1A3b + obsA1A4B + obsA1A4b + obsA2A3B + obsA2A3b + obsA2A4B + obsA2A4b,
                totalA1A3 = obsA1A3B + obsA1A3b,
                totalA1A4 = obsA1A4B + obsA1A4b,
                totalA2A3 = obsA2A3B + obsA2A3b,
                totalA2A4 = obsA2A4B + obsA2A4b,
                totalA1A2A3A4 = totalA1A3 + totalA1A4 + totalA2A3 + totalA2A4,
                totalB = obsA1A3B + obsA1A4B + obsA2A3B + obsA2A4B,
                totalb = obsA1A3b + obsA1A4b + obsA2A3b + obsA2A4b;

        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A1A3", (double) totalobs / 4);
        expValues.put("A1A4", (double) totalobs / 4);
        expValues.put("A2A3", (double) totalobs /4);
        expValues.put("A2A4", (double) totalobs /4);
        expValues.put("B", (double) totalobs * 3/4);
        expValues.put("b", (double) totalobs /4);

        double chiA1A2A3A4 = (Math.pow(totalA1A3 - totalobs /4, 2)/ (totalobs /4)) + (Math.pow(totalA1A4 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalA2A3 - totalobs * 2/4, 2) / (totalobs * 2 / 4)) + (Math.pow(totalA2A4 - totalobs * 2/4, 2) / (totalobs * 2 / 4));
        double chiBb = (Math.pow(totalB - totalobs * 3/4, 2)/ (totalobs * 3/4)) + (Math.pow(totalb - totalobs / 4, 2)/ (totalobs /4));

        if(chiA1A2A3A4 < 7.82 && chiBb < 3.84) {
            double expectedIndA1A3B = totalobs * 3 / 16,
                    expectedIndA1A3b = totalobs * 1 / 16,
                    expectedIndA1A4B = totalobs * 3 / 16,
                    expectedIndA1A4b = totalobs * 1 / 16,
                    expectedIndA2A3B = totalobs * 3 / 16,
                    expectedIndA2A3b = totalobs * 1 / 16,
                    expectedIndA2A4B = totalobs * 3 / 16,
                    expectedIndA2A4b = totalobs * 1 / 16;

            expValues.put("expectedIndA1A3B", expectedIndA1A3B);
            expValues.put("expectedIndA1A3b", expectedIndA1A3b);
            expValues.put("expectedIndA1A4B", expectedIndA1A4B);
            expValues.put("expectedIndA1A4b", expectedIndA1A4b);
            expValues.put("expectedIndA2A3B", expectedIndA2A3B);
            expValues.put("expectedIndA2A3b", expectedIndA2A3b);
            expValues.put("expectedIndA2A4B", expectedIndA2A4B);
            expValues.put("expectedIndA2A4b", expectedIndA2A4b);

            if (expectedIndA1A3B < 5 || expectedIndA1A3b < 5 || expectedIndA1A4B < 5 || expectedIndA1A4b < 5 || expectedIndA2A3B < 5 || expectedIndA2A3b < 5 || expectedIndA2A4B < 5 || expectedIndA2A4b < 5) {
                //warning y borrar
            } else {
                double chiInd = (Math.pow(obsA1A3B - expectedIndA1A3B, 2) / expectedIndA1A3B) + (Math.pow(obsA1A3b - expectedIndA1A3b, 2) / expectedIndA1A3b) + (Math.pow(obsA1A4B - expectedIndA1A4B, 2) / expectedIndA1A4B) + (Math.pow(obsA1A4b - expectedIndA1A4b, 2) / expectedIndA1A4b) + (Math.pow(obsA2A3B - expectedIndA2A3B, 2) / expectedIndA2A3B) + (Math.pow(obsA2A3b - expectedIndA2A3b, 2) / expectedIndA2A3b) + (Math.pow(obsA2A4B - expectedIndA2A4B, 2) / expectedIndA2A4B) + (Math.pow(obsA2A4b - expectedIndA2A4b, 2) / expectedIndA2A4b);
                if (chiInd > 14.07) {
                    double expectedContA1A3B = totalA1A3 * totalB / totalobs,
                            expectedContA1A3b = totalA1A3 * totalb / totalobs,
                            expectedContA1A4B = totalA1A4 * totalB / totalobs,
                            expectedContA1A4b = totalA1A4 * totalb / totalobs,
                            expectedContA2A3B = totalA2A3 * totalB / totalobs,
                            expectedContA2A3b = totalA2A3 * totalb / totalobs,
                            expectedContA2A4B = totalA2A4 * totalB / totalobs,
                            expectedContA2A4b = totalA2A4 * totalb / totalobs;

                    expValues.put("contA1A3B", expectedContA1A3B);
                    expValues.put("contA1A3b", expectedContA1A3b);
                    expValues.put("contA1A4B", expectedContA1A4B);
                    expValues.put("contA1A4b", expectedContA1A4b);
                    expValues.put("contA2A3B", expectedContA2A3B);
                    expValues.put("contA2A3b", expectedContA2A3b);
                    expValues.put("contA2A4B", expectedContA2A4B);
                    expValues.put("contA2A4b", expectedContA2A4b);

                    if (expectedContA1A3B < 5 || expectedContA1A3b < 5 || expectedContA1A4B < 5 || expectedContA1A4b < 5 || expectedContA2A3B < 5 || expectedContA2A3b < 5 || expectedContA2A4B < 5 || expectedContA2A4b < 5){
                        //warning y clean
                    } else {
                        if ((expectedContA1A3B >= 5 && expectedContA1A3B <= 10) || (expectedContA1A3b >= 5 && expectedContA1A3b <= 10) || (expectedContA1A4B >= 5 && expectedContA1A4B <= 10) || (expectedContA1A4b >= 5 && expectedContA1A4b <= 10) || (expectedContA2A3B >= 5 && expectedContA2A3B <= 10) || (expectedContA2A3b >= 5 && expectedContA2A3b <= 10) || (expectedContA2A4B >= 5 && expectedContA2A4B <= 10) || (expectedContA2A4b >= 5 && expectedContA2A4b <= 10)) {
                            //warning
                            chiInd = (Math.pow((obsA1A3B - expectedIndA1A3B) - 0.5, 2) / expectedIndA1A3B) + (Math.pow((obsA1A3b - expectedIndA1A3b) - 0.5, 2) / expectedIndA1A3b) + (Math.pow((obsA1A4B - expectedIndA1A4B) - 0.5, 2) / expectedIndA1A4B) + (Math.pow((obsA1A4b - expectedIndA1A4b) - 0.5, 2) / expectedIndA1A4b) + (Math.pow((obsA2A3B - expectedIndA2A3B) - 0.5, 2) / expectedIndA2A3B) + (Math.pow((obsA2A3b - expectedIndA2A3b) - 0.5, 2) / expectedIndA2A3b) + (Math.pow((obsA2A4B - expectedIndA2A4B) - 0.5, 2) / expectedIndA2A4B) + (Math.pow((obsA2A4b - expectedIndA2A4b) - 0.5, 2) / expectedIndA2A4b);
                        } else {
                            //esto ya esta calculado arriba???
                            chiInd = (Math.pow(obsA1A3B - expectedIndA1A3B, 2) / expectedIndA1A3B) + (Math.pow(obsA1A3b - expectedIndA1A3b, 2) / expectedIndA1A3b) + (Math.pow(obsA1A4B - expectedIndA1A4B, 2) / expectedIndA1A4B) + (Math.pow(obsA1A4b - expectedIndA1A4b, 2) / expectedIndA1A4b) + (Math.pow(obsA2A3B - expectedIndA2A3B, 2) / expectedIndA2A3B) + (Math.pow(obsA2A3b - expectedIndA2A3b, 2) / expectedIndA2A3b) + (Math.pow(obsA2A4B - expectedIndA2A4B, 2) / expectedIndA2A4B) + (Math.pow(obsA2A4b - expectedIndA2A4b, 2) / expectedIndA2A4b);
                            if (chiInd > 14.07) {
                                if (expectedContA1A3B < 5 || expectedContA1A3b < 5 || expectedContA1A4B < 5 || expectedContA1A4b < 5 || expectedContA2A3B < 5 || expectedContA2A3b < 5 || expectedContA2A4B < 5 || expectedContA2A4b < 5){
                                    //warning y clean (esto no esta repetido??!?!?!?!?)
                                } else {
                                    chiInd = (Math.pow((obsA1A3B - expectedIndA1A3B) - 0.5, 2) / expectedIndA1A3B) + (Math.pow((obsA1A3b - expectedIndA1A3b) - 0.5, 2) / expectedIndA1A3b) + (Math.pow((obsA1A4B - expectedIndA1A4B) - 0.5, 2) / expectedIndA1A4B) + (Math.pow((obsA1A4b - expectedIndA1A4b) - 0.5, 2) / expectedIndA1A4b) + (Math.pow((obsA2A3B - expectedIndA2A3B) - 0.5, 2) / expectedIndA2A3B) + (Math.pow((obsA2A3b - expectedIndA2A3b) - 0.5, 2) / expectedIndA2A3b) + (Math.pow((obsA2A4B - expectedIndA2A4B) - 0.5, 2) / expectedIndA2A4B) + (Math.pow((obsA2A4b - expectedIndA2A4b) - 0.5, 2) / expectedIndA2A4b);
                                }
                            } else {
                                //limpiar to lo de cont
                            }
                        }
                        double chiCont = (Math.pow(obsA1A3B - expectedContA1A3B, 2) / expectedContA1A3B) + (Math.pow(obsA1A3b - expectedContA1A3b, 2) / expectedContA1A3b) + (Math.pow(obsA1A4B - expectedContA1A4B, 2) / expectedContA1A4B) + (Math.pow(obsA1A4b - expectedContA1A4b, 2) / expectedContA1A4b) + (Math.pow(obsA2A3B - expectedContA2A3B, 2) / expectedContA2A3B) + (Math.pow(obsA2A3b - expectedContA2A3b, 2) / expectedContA2A3b) + (Math.pow(obsA2A4B - expectedContA2A4B, 2) / expectedContA2A4B) + (Math.pow(obsA2A4b - expectedContA2A4b, 2) / expectedContA2A4b);
                    }
                } else {
                    //limpiar to lo de cont
                }

            }
        } else {
            double expectedContA1A3B = totalA1A3 * totalB / totalobs,
                    expectedContA1A3b = totalA1A3 * totalb / totalobs,
                    expectedContA1A4B = totalA1A4 * totalB / totalobs,
                    expectedContA1A4b = totalA1A4 * totalb / totalobs,
                    expectedContA2A3B = totalA2A3 * totalB / totalobs,
                    expectedContA2A3b = totalA2A3 * totalb / totalobs,
                    expectedContA2A4B = totalA2A4 * totalB / totalobs,
                    expectedContA2A4b = totalA2A4 * totalb / totalobs;

            expValues.put("contA1A3B", expectedContA1A3B);
            expValues.put("contA1A3b", expectedContA1A3b);
            expValues.put("contA1A4B", expectedContA1A4B);
            expValues.put("contA1A4b", expectedContA1A4b);
            expValues.put("contA2A3B", expectedContA2A3B);
            expValues.put("contA2A3b", expectedContA2A3b);
            expValues.put("contA2A4B", expectedContA2A4B);
            expValues.put("contA2A4b", expectedContA2A4b);

            if (expectedContA1A3B < 5 || expectedContA1A3b < 5 || expectedContA1A4B < 5 || expectedContA1A4b < 5 || expectedContA2A3B < 5 || expectedContA2A3b < 5 || expectedContA2A4B < 5 || expectedContA2A4b < 5){
                //warning y clean
            } else{
                if ((expectedContA1A3B >= 5 && expectedContA1A3B <= 10) || (expectedContA1A3b >= 5 && expectedContA1A3b <= 10) || (expectedContA1A4B >= 5 && expectedContA1A4B <= 10) || (expectedContA1A4b >= 5 && expectedContA1A4b <= 10) || (expectedContA2A3B >= 5 && expectedContA2A3B <= 10) || (expectedContA2A3b >= 5 && expectedContA2A3b <= 10) || (expectedContA2A4B >= 5 && expectedContA2A4B <= 10) || (expectedContA2A4b >= 5 && expectedContA2A4b <= 10)) {
                    //warning
                    double chiCont = (Math.pow((obsA1A3B - expectedContA1A3B) - 0.5, 2) / expectedContA1A3B) + (Math.pow((obsA1A3b - expectedContA1A3b) - 0.5, 2) / expectedContA1A3b) + (Math.pow((obsA1A4B - expectedContA1A4B) - 0.5, 2) / expectedContA1A4B) + (Math.pow((obsA1A4b - expectedContA1A4b) - 0.5, 2) / expectedContA1A4b) + (Math.pow((obsA2A3B - expectedContA2A3B) - 0.5, 2) / expectedContA2A3B) + (Math.pow((obsA2A3b - expectedContA2A3b) - 0.5, 2) / expectedContA2A3b) + (Math.pow((obsA2A4B - expectedContA2A4B) - 0.5, 2) / expectedContA2A4B) + (Math.pow((obsA2A4b - expectedContA2A4b) - 0.5, 2) / expectedContA2A4b);
                } else {
                    double chiCont = (Math.pow(obsA1A3B - expectedContA1A3B, 2) / expectedContA1A3B) + (Math.pow(obsA1A3b - expectedContA1A3b, 2) / expectedContA1A3b) + (Math.pow(obsA1A4B - expectedContA1A4B, 2) / expectedContA1A4B) + (Math.pow(obsA1A4b - expectedContA1A4b, 2) / expectedContA1A4b) + (Math.pow(obsA2A3B - expectedContA2A3B, 2) / expectedContA2A3B) + (Math.pow(obsA2A3b - expectedContA2A3b, 2) / expectedContA2A3b) + (Math.pow(obsA2A4B - expectedContA2A4B, 2) / expectedContA2A4B) + (Math.pow(obsA2A4b - expectedContA2A4b, 2) / expectedContA2A4b);
                }
            }
        }
        return null;
    }

    public CTResult f2TestcrossDom(int obsAB, int obsAb, int obsaB, int obsab) {
        //Cosas que haríamos en el controlador:
        //Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
        //Validar que sea > 5
        //Sumar para poner total en el propio controlador
        //Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
        int totalobs = obsAB + obsAb + obsaB + obsab,
                totalA = obsAB + obsAb,
                totala = obsaB + obsab,
                totalAa = totalA + totala,
                totalB = obsAB + obsaB,
                totalb = obsAb + obsab,
                totalBb = totalB + totalb;

        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A", (double) totalobs * 3 / 4);
        expValues.put("a", (double) totalobs / 4);
        expValues.put("B", (double) totalobs * 2 / 4);
        expValues.put("b", (double) totalobs * 2 / 4);

        double chiAa = (Math.pow(totalA - totalobs * 3 / 4, 2)/ (totalobs * 3/4)) + (Math.pow(totala - totalobs / 4, 2)/ (totalobs /4));
        double chiBb = (Math.pow(totalB - totalobs * 2/4, 2)/ (totalobs * 2/4)) + (Math.pow(totalb - totalobs * 2 / 4, 2)/ (totalobs * 2/4));

        if(chiAa < 3.84 && chiBb < 3.84) {
            double expectedIndAB = totalobs * 3 / 8,
                    expectedIndAb = totalobs * 3 / 8,
                    expectedIndaB = totalobs * 1 / 8,
                    expectedIndab = totalobs * 1 / 8;

            expValues.put("expectedIndAB", expectedIndAB);
            expValues.put("expectedIndAb", expectedIndAb);
            expValues.put("expectedIndaB", expectedIndaB);
            expValues.put("expectedIndab", expectedIndab);

            if (expectedIndAB < 5 || expectedIndAb < 5 || expectedIndaB < 5 || expectedIndab < 5) {
                //warning y borrar
            } else if ((expectedIndAB >= 5 && expectedIndAB <= 10) || (expectedIndAb >= 5 && expectedIndAb <= 10) || (expectedIndaB >= 5 && expectedIndaB <= 10) || (expectedIndab >= 5 && expectedIndab <= 10)) {
                double chiInd = (Math.pow((obsAB - expectedIndAB) - 0.5, 2) / expectedIndAB) + (Math.pow((obsAb - expectedIndAb) - 0.5, 2) / expectedIndAb) + (Math.pow((obsaB - expectedIndaB) - 0.5, 2) / expectedIndaB) + (Math.pow((obsab - expectedIndab) - 0.5, 2) / expectedIndab);
            } else{
                double chiInd = (Math.pow(obsAB - expectedIndAB, 2) / expectedIndAB) + (Math.pow(obsAb - expectedIndAb, 2) / expectedIndAb) + (Math.pow(obsaB - expectedIndaB, 2) / expectedIndaB) + (Math.pow(obsab - expectedIndab, 2) / expectedIndab);
                if (chiInd > 7.81) {
                    double expectedContAB = totalA * totalB / totalobs,
                            expectedContAb = totalA * totalb / totalobs,
                            expectedContaB = totala * totalB / totalobs,
                            expectedContab = totala * totalb / totalobs;

                    expValues.put("contAB", expectedContAB);
                    expValues.put("contAb", expectedContAb);
                    expValues.put("contaB", expectedContaB);
                    expValues.put("contab", expectedContab);

                    if (expectedContAB < 5 || expectedContAb < 5 || expectedContaB < 5 || expectedContab < 5){
                        //warning y clean
                    } else {
                        if ((expectedContAB >= 5 && expectedContAB <= 10) || (expectedContAb >= 5 && expectedContAb <= 10) || (expectedContaB >= 5 && expectedContaB <= 10) || (expectedContab >= 5 && expectedContab <= 10)) {
                            //warning
                            double chiCont = (Math.pow((obsAB - expectedContAB) - 0.5, 2) / expectedContAB) + (Math.pow((obsAb - expectedContAb) - 0.5, 2) / expectedContAb) + (Math.pow((obsaB - expectedContaB) - 0.5, 2) / expectedContaB) + (Math.pow((obsab - expectedContab) - 0.5, 2) / expectedContab);
                        } else {
                            double chiCont = (Math.pow(obsAB - expectedContAB, 2) / expectedContAB) + (Math.pow(obsAb - expectedContAb, 2) / expectedContAb) + (Math.pow(obsaB - expectedContaB, 2) / expectedContaB) + (Math.pow(obsab - expectedContab, 2) / expectedContab);
                        }
                    }
                } else {
                    //limpiar to lo de cont
                }
            }
        } else {
            double expectedContAB = totalA * totalB / totalobs,
                    expectedContAb = totalA * totalb / totalobs,
                    expectedContaB = totala * totalB / totalobs,
                    expectedContab = totala * totalb / totalobs;

            expValues.put("contAB", expectedContAB);
            expValues.put("contAb", expectedContAb);
            expValues.put("contaB", expectedContaB);
            expValues.put("contab", expectedContab);

            if (expectedContAB < 5 || expectedContAb < 5 || expectedContaB < 5 || expectedContab < 5){
                //warning y clean
            } else {
                if ((expectedContAB >= 5 && expectedContAB <= 10) || (expectedContAb >= 5 && expectedContAb <= 10) || (expectedContaB >= 5 && expectedContaB <= 10) || (expectedContab >= 5 && expectedContab <= 10)) {
                    //warning
                    double chiCont = (Math.pow((obsAB - expectedContAB) - 0.5, 2) / expectedContAB) + (Math.pow((obsAb - expectedContAb) - 0.5, 2) / expectedContAb) + (Math.pow((obsaB - expectedContaB) - 0.5, 2) / expectedContaB) + (Math.pow((obsab - expectedContab) - 0.5, 2) / expectedContab);
                } else {
                    double chiCont = (Math.pow(obsAB - expectedContAB, 2) / expectedContAB) + (Math.pow(obsAb - expectedContAb, 2) / expectedContAb) + (Math.pow(obsaB - expectedContaB, 2) / expectedContaB) + (Math.pow(obsab - expectedContab, 2) / expectedContab);
                }
            }
        }
        return null;
    }

    public CTResult f2Testcross2Dom(int obsA1A1B, int obsA1A2B, int obsA2A2B, int obsA1A1b, int obsA1A2b, int obsA2A2b) {
        //Cosas que haríamos en el controlador:
        //Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
        //Validar que sea > 5
        //Sumar para poner total en el propio controlador
        //Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
        int totalobs = obsA1A1B + obsA1A2B + obsA2A2B + obsA1A1b + obsA1A2b + obsA2A2b,
                totalA1 = obsA1A1B + obsA1A1b,
                totalA2 = obsA2A2B + obsA2A2b,
                totalA1A2 = obsA1A2B + obsA1A2b,
                totalA = totalA1 + totalA2 + totalA1A2,
                totalB = obsA1A1B + obsA1A2B + obsA2A2B,
                totalb = obsA1A1b + obsA1A2b + obsA2A2b;

        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A1", (double) totalobs * 1 / 4);
        expValues.put("A2", (double) totalobs * 1 / 4);
        expValues.put("A1A2", (double) totalobs * 2 / 4);
        expValues.put("B", (double) totalobs * 2 / 4);
        expValues.put("b", (double) totalobs * 2 / 4);

        double chiA1A2 = (Math.pow(totalA1 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalA2 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalA1A2 - totalobs * 2/4, 2)/ (totalobs * 2/4));
        double chiBb = (Math.pow(totalB - totalobs * 2/4, 2)/ (totalobs * 2/4)) + (Math.pow(totalb - totalobs * 2 / 4, 2)/ (totalobs * 2/4));

        if(chiA1A2 < 5.99 && chiBb < 5.99) {
            double expectedIndA1B = totalobs / 8,
                    expectedIndA1b = totalobs / 8,
                    expectedIndA2B = totalobs / 8,
                    expectedIndA2b = totalobs / 8,
                    expectedIndA1A2B = totalobs * 2 / 8,
                    expectedIndA1A2b = totalobs * 2 / 8;

            expValues.put("expectedIndA1B", expectedIndA1B);
            expValues.put("expectedIndA1b", expectedIndA1b);
            expValues.put("expectedIndA2B", expectedIndA2B);
            expValues.put("expectedIndA2b", expectedIndA2b);
            expValues.put("expectedIndA1A2B", expectedIndA1A2B);
            expValues.put("expectedIndA1A2b", expectedIndA1A2b);

            if (expectedIndA1B < 5 || expectedIndA1b < 5 || expectedIndA2B < 5 || expectedIndA2b < 5 || expectedIndA1A2B < 5 || expectedIndA1A2b < 5) {
                //warning y borrar
            } else {
                double chiInd = (Math.pow(obsA1A1B - expectedIndA1B, 2) / expectedIndA1B) + (Math.pow(obsA1A1b - expectedIndA1b, 2) / expectedIndA1b) + (Math.pow(obsA2A2B - expectedIndA2B, 2) / expectedIndA2B) + (Math.pow(obsA2A2b - expectedIndA2b, 2) / expectedIndA2b) + (Math.pow(obsA1A2B - expectedIndA1A2B, 2) / expectedIndA1A2B) + (Math.pow(obsA1A2b - expectedIndA1A2b, 2) / expectedIndA1A2b);
                if (chiInd > 9.49) {
                    double expectedContA1B = totalA1 * totalB / totalobs,
                            expectedContA1b = totalA1 * totalb / totalobs,
                            expectedContA2B = totalA2 * totalB / totalobs,
                            expectedContA2b = totalA2 * totalb / totalobs,
                            expectedContA1A2B = totalA1A2 * totalB / totalobs,
                            expectedContA1A2b = totalA1A2 * totalb / totalobs;

                    expValues.put("contA1B", expectedContA1B);
                    expValues.put("contA2B", expectedContA2B);
                    expValues.put("contA1b", expectedContA1b);
                    expValues.put("contA2b", expectedContA2b);
                    expValues.put("contA1A2B", expectedContA1A2B);
                    expValues.put("contA1A2b", expectedContA1A2b);

                    if (expectedContA1B < 5 || expectedContA2B < 5 || expectedContA1b < 5 || expectedContA2b < 5 || expectedContA1A2B < 5 || expectedContA1A2b < 5) {
                        //warning y clean
                    } else {
                        if ((expectedIndA1B >= 5 && expectedIndA1B <= 10) || (expectedIndA2B >= 5 && expectedIndA2B <= 10) || (expectedIndA1b >= 5 && expectedIndA1b <= 10) || (expectedIndA2b >= 5 && expectedIndA2b <= 10) || (expectedIndA1A2B >= 5 && expectedIndA1A2B <= 10) || (expectedIndA1A2b >= 5 && expectedIndA1A2b <= 10)) {
                            //warning
                            chiInd = (Math.pow((obsA1A1B - expectedIndA1B) - 0.5, 2) / expectedIndA1B) + (Math.pow((obsA1A1b - expectedIndA1b) - 0.5, 2) / expectedIndA1b) + (Math.pow((obsA2A2B - expectedIndA2B) - 0.5, 2) / expectedIndA2B) + (Math.pow((obsA2A2b - expectedIndA2b) - 0.5, 2) / expectedIndA2b) + (Math.pow((obsA1A2B - expectedIndA1A2B) - 0.5, 2) / expectedIndA1A2B) + (Math.pow((obsA1A2b - expectedIndA1A2b) - 0.5, 2) / expectedIndA1A2b);
                        } else {
                            chiInd = (Math.pow(obsA1A1B - expectedIndA1B, 2) / expectedIndA1B) + (Math.pow(obsA1A1b - expectedIndA1b, 2) / expectedIndA1b) + (Math.pow(obsA2A2B - expectedIndA2B, 2) / expectedIndA2B) + (Math.pow(obsA2A2b - expectedIndA2b, 2) / expectedIndA2b) + (Math.pow(obsA1A2B - expectedIndA1A2B, 2) / expectedIndA1A2B) + (Math.pow(obsA1A2b - expectedIndA1A2b, 2) / expectedIndA1A2b);
                            if (chiInd > 14.07){
                                if (expectedContA1B < 5 || expectedContA2B < 5 || expectedContA1b < 5 || expectedContA2b < 5 || expectedContA1A2B < 5 || expectedContA1A2b < 5) {
                                    //warning y clean
                                } else {
                                    chiInd = (Math.pow((obsA1A1B - expectedIndA1B) - 0.5, 2) / expectedIndA1B) + (Math.pow((obsA1A1b - expectedIndA1b) - 0.5, 2) / expectedIndA1b) + (Math.pow((obsA2A2B - expectedIndA2B) - 0.5, 2) / expectedIndA2B) + (Math.pow((obsA2A2b - expectedIndA2b) - 0.5, 2) / expectedIndA2b) + (Math.pow((obsA1A2B - expectedIndA1A2B) - 0.5, 2) / expectedIndA1A2B) + (Math.pow((obsA1A2b - expectedIndA1A2b) - 0.5, 2) / expectedIndA1A2b);
                                }
                            } else {
                                //limpiar to lo de cont
                            }
                        }
                        double chiCont = (Math.pow(obsA1A1B - expectedContA1B, 2) / expectedContA1B) + (Math.pow(obsA1A1b - expectedContA1b, 2) / expectedContA1b) + (Math.pow(obsA2A2B - expectedContA2B, 2) / expectedContA2B) + (Math.pow(obsA2A2b - expectedContA2b, 2) / expectedContA2b) + (Math.pow(obsA1A2B - expectedContA1A2B, 2) / expectedContA1A2B) + (Math.pow(obsA1A2b - expectedContA1A2b, 2) / expectedContA1A2b);
                    }
                } else {
                    //limpiar to lo de cont
                }
            }
        } else {
            double expectedContA1B = totalA1 * totalB / totalobs,
                    expectedContA1b = totalA1 * totalb / totalobs,
                    expectedContA2B = totalA2 * totalB / totalobs,
                    expectedContA2b = totalA2 * totalb / totalobs,
                    expectedContA1A2B = totalA1A2 * totalB / totalobs,
                    expectedContA1A2b = totalA1A2 * totalb / totalobs;

            expValues.put("contA1B", expectedContA1B);
            expValues.put("contA2B", expectedContA2B);
            expValues.put("contA1b", expectedContA1b);
            expValues.put("contA2b", expectedContA2b);
            expValues.put("contA1A2B", expectedContA1A2B);
            expValues.put("contA1A2b", expectedContA1A2b);

            if (expectedContA1B < 5 || expectedContA2B < 5 || expectedContA1b < 5 || expectedContA2b < 5 || expectedContA1A2B < 5 || expectedContA1A2b < 5) {
                //warning y clean
            } else {
                if ((expectedContA1B >= 5 && expectedContA1B <= 10) || (expectedContA2B >= 5 && expectedContA2B <= 10) || (expectedContA1b >= 5 && expectedContA1b <= 10) || (expectedContA2b >= 5 && expectedContA2b <= 10) || (expectedContA1A2B >= 5 && expectedContA1A2B <= 10) || (expectedContA1A2b >= 5 && expectedContA1A2b <= 10)) {
                    //warning
                    double chiCont = (Math.pow((obsA1A1B - expectedContA1B) - 0.5, 2) / expectedContA1B) + (Math.pow((obsA1A1b - expectedContA1b) - 0.5, 2) / expectedContA1b) + (Math.pow((obsA2A2B - expectedContA2B) - 0.5, 2) / expectedContA2B) + (Math.pow((obsA2A2b - expectedContA2b) - 0.5, 2) / expectedContA2b) + (Math.pow((obsA1A2B - expectedContA1A2B) - 0.5, 2) / expectedContA1A2B) + (Math.pow((obsA1A2b - expectedContA1A2b) - 0.5, 2) / expectedContA1A2b);
                } else {
                    double chiCont = (Math.pow(obsA1A1B - expectedContA1B, 2) / expectedContA1B) + (Math.pow(obsA1A1b - expectedContA1b, 2) / expectedContA1b) + (Math.pow(obsA2A2B - expectedContA2B, 2) / expectedContA2B) + (Math.pow(obsA2A2b - expectedContA2b, 2) / expectedContA2b) + (Math.pow(obsA1A2B - expectedContA1A2B, 2) / expectedContA1A2B) + (Math.pow(obsA1A2b - expectedContA1A2b, 2) / expectedContA1A2b);
                }
            }
        }
        return null;
    }

    public CTResult f2Testcross4Dom(int obsA1A3B, int obsA1A3b, int obsA1A4B, int obsA1A4b, int obsA2A3B, int obsA2A3b, int obsA2A4B, int obsA2A4b) {
        //Cosas que haríamos en el controlador:
        //Validar que sea <5000 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
        //Validar que sea > 5
        //Sumar para poner total en el propio controlador
        //Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
        int totalobs = obsA1A3B + obsA1A3b + obsA1A4B + obsA1A4b + obsA2A3B + obsA2A3b + obsA2A4B + obsA2A4b,
                totalA1A3 = obsA1A3B + obsA1A3b,
                totalA1A4 = obsA1A4B + obsA1A4b,
                totalA2A3 = obsA2A3B + obsA2A3b,
                totalA2A4 = obsA2A4B + obsA2A4b,
                totalA = totalA1A3 + totalA1A4 + totalA2A3 + totalA2A4,
                totalB = obsA1A3B + obsA1A4B + obsA2A3B + obsA2A4B,
                totalb = obsA1A3b + obsA1A4b + obsA2A3b + obsA2A4b;

        Map<String, Double> expValues = new HashMap<>();
        expValues.put("A1A3", (double) totalobs * 1 / 4);
        expValues.put("A1A4", (double) totalobs * 1 / 4);
        expValues.put("A2A3", (double) totalobs * 1 / 4);
        expValues.put("A2A4", (double) totalobs * 1 / 4);
        expValues.put("B", (double) totalobs * 2 / 4);
        expValues.put("b", (double) totalobs * 2 / 4);

        double chiA1A2A3A4 = (Math.pow(totalA1A3 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalA1A4 - totalobs / 4, 2)/ (totalobs /4)) + (Math.pow(totalA2A3 - totalobs /4, 2)/ (totalobs /4)) + (Math.pow(totalA2A4 - totalobs /4, 2)/ (totalobs /4));
        double chiBb = (Math.pow(totalB - totalobs /2, 2) / (totalobs /2)) + (Math.pow(totalb - totalobs / 2, 2)/ (totalobs /2));

        if(chiA1A2A3A4 < 7.82 && chiBb < 3.84) {
            double expectedIndA1A3B = totalobs / 8,
                    expectedIndA1A3b = totalobs / 8,
                    expectedIndA1A4B = totalobs / 8,
                    expectedIndA1A4b = totalobs / 8,
                    expectedIndA2A3B = totalobs / 8,
                    expectedIndA2A3b = totalobs / 8,
                    expectedIndA2A4B = totalobs / 8,
                    expectedIndA2A4b = totalobs / 8;

            expValues.put("expectedIndA1A3B", expectedIndA1A3B);
            expValues.put("expectedIndA1A3b", expectedIndA1A3b);
            expValues.put("expectedIndA1A4B", expectedIndA1A4B);
            expValues.put("expectedIndA1A4b", expectedIndA1A4b);
            expValues.put("expectedIndA2A3B", expectedIndA2A3B);
            expValues.put("expectedIndA2A3b", expectedIndA2A3b);
            expValues.put("expectedIndA2A4B", expectedIndA2A4B);
            expValues.put("expectedIndA2A4b", expectedIndA2A4b);

            if (expectedIndA1A3B < 5 || expectedIndA1A3b < 5 || expectedIndA1A4B < 5 || expectedIndA1A4b < 5 || expectedIndA2A3B < 5 || expectedIndA2A3b < 5 || expectedIndA2A4B < 5 || expectedIndA2A4b < 5) {
                //warning y borrar
            } else {
                double chiInd = (Math.pow(obsA1A3B - expectedIndA1A3B, 2) / expectedIndA1A3B) + (Math.pow(obsA1A3b - expectedIndA1A3b, 2) / expectedIndA1A3b) + (Math.pow(obsA1A4B - expectedIndA1A4B, 2) / expectedIndA1A4B) + (Math.pow(obsA1A4b - expectedIndA1A4b, 2) / expectedIndA1A4b) + (Math.pow(obsA2A3B - expectedIndA2A3B, 2) / expectedIndA2A3B) + (Math.pow(obsA2A3b - expectedIndA2A3b, 2) / expectedIndA2A3b) + (Math.pow(obsA2A4B - expectedIndA2A4B, 2) / expectedIndA2A4B) + (Math.pow(obsA2A4b - expectedIndA2A4b, 2) / expectedIndA2A4b);
                if (chiInd > 14.07) {
                    double expectedContA1A3B = totalA1A3 * totalB / totalobs,
                            expectedContA1A3b = totalA1A3 * totalb / totalobs,
                            expectedContA1A4B = totalA1A4 * totalB / totalobs,
                            expectedContA1A4b = totalA1A4 * totalb / totalobs,
                            expectedContA2A3B = totalA2A3 * totalB / totalobs,
                            expectedContA2A3b = totalA2A3 * totalb / totalobs,
                            expectedContA2A4B = totalA2A4 * totalB / totalobs,
                            expectedContA2A4b = totalA2A4 * totalb / totalobs;

                    expValues.put("contA1A3B", expectedContA1A3B);
                    expValues.put("contA1A3b", expectedContA1A3b);
                    expValues.put("contA1A4B", expectedContA1A4B);
                    expValues.put("contA1A4b", expectedContA1A4b);
                    expValues.put("contA2A3B", expectedContA2A3B);
                    expValues.put("contA2A3b", expectedContA2A3b);
                    expValues.put("contA2A4B", expectedContA2A4B);
                    expValues.put("contA2A4b", expectedContA2A4b);

                    if (expectedContA1A3B < 5 || expectedContA1A3b < 5 || expectedContA1A4B < 5 || expectedContA1A4b < 5 || expectedContA2A3B < 5 || expectedContA2A3b < 5 || expectedContA2A4B < 5 || expectedContA2A4b < 5) {
                        //warning y clean
                    } else {
                        if ((expectedIndA1A3B >= 5 && expectedIndA1A3B <= 10) || (expectedIndA1A3b >= 5 && expectedIndA1A3b <= 10) || (expectedIndA1A4B >= 5 && expectedIndA1A4B <= 10) || (expectedIndA1A4b >= 5 && expectedIndA1A4b <= 10) || (expectedIndA2A3B >= 5 && expectedIndA2A3B <= 10) || (expectedIndA2A3b >= 5 && expectedIndA2A3b <= 10) || (expectedIndA2A4B >= 5 && expectedIndA2A4B <= 10) || (expectedIndA2A4b >= 5 && expectedIndA2A4b <= 10)) {
                            //warning
                            chiInd = (Math.pow((obsA1A3B - expectedIndA1A3B) - 0.5, 2) / expectedIndA1A3B) + (Math.pow((obsA1A3b - expectedIndA1A3b) - 0.5, 2) / expectedIndA1A3b) + (Math.pow((obsA2A3B - expectedIndA2A3B) - 0.5, 2) / expectedIndA2A3B) + (Math.pow((obsA2A3b - expectedIndA2A3b) - 0.5, 2) / expectedIndA2A3b) + (Math.pow((obsA1A4B - expectedIndA1A4B) - 0.5, 2) / expectedIndA1A4B) + (Math.pow((obsA1A4b - expectedIndA1A4b) - 0.5, 2) / expectedIndA1A4b) + (Math.pow((obsA2A4B - expectedIndA2A4B) - 0.5, 2) / expectedIndA2A4B) + (Math.pow((obsA2A4b - expectedIndA2A4b) - 0.5, 2) / expectedIndA2A4b);
                        } else {
                            chiInd = (Math.pow(obsA1A3B - expectedIndA1A3B, 2) / expectedIndA1A3B) + (Math.pow(obsA1A3b - expectedIndA1A3b, 2) / expectedIndA1A3b) + (Math.pow(obsA2A3B - expectedIndA2A3B, 2) / expectedIndA2A3B) + (Math.pow(obsA2A3b - expectedIndA2A3b, 2) / expectedIndA2A3b) + (Math.pow(obsA1A4B - expectedIndA1A4B, 2) / expectedIndA1A4B) + (Math.pow(obsA1A4b - expectedIndA1A4b, 2) / expectedIndA1A4b) + (Math.pow(obsA2A4B - expectedIndA2A4B, 2) / expectedIndA2A4B) + (Math.pow(obsA2A4b - expectedIndA2A4b, 2) / expectedIndA2A4b);
                            if (chiInd > 14.07){
                                if (expectedContA1A3B < 5 || expectedContA1A3b < 5 || expectedContA1A4B < 5 || expectedContA1A4b < 5 || expectedContA2A3B < 5 || expectedContA2A3b < 5 || expectedContA2A4B < 5 || expectedContA2A4b < 5) {
                                    //warning y clean
                                } else {
                                    chiInd = (Math.pow((obsA1A3B - expectedIndA1A3B) - 0.5, 2) / expectedIndA1A3B) + (Math.pow((obsA1A3b - expectedIndA1A3b) - 0.5, 2) / expectedIndA1A3b) + (Math.pow((obsA2A3B - expectedIndA2A3B) - 0.5, 2) / expectedIndA2A3B) + (Math.pow((obsA2A3b - expectedIndA2A3b) - 0.5, 2) / expectedIndA2A3b) + (Math.pow((obsA1A4B - expectedIndA1A4B) - 0.5, 2) / expectedIndA1A4B) + (Math.pow((obsA1A4b - expectedIndA1A4b) - 0.5, 2) / expectedIndA1A4b) + (Math.pow((obsA2A4B - expectedIndA2A4B) - 0.5, 2) / expectedIndA2A4B) + (Math.pow((obsA2A4b - expectedIndA2A4b) - 0.5, 2) / expectedIndA2A4b);
                                }
                            } else {
                                //limpiar to lo de cont
                            }
                        }
                        double chiCont = (Math.pow(obsA1A3B - expectedContA1A3B, 2) / expectedContA1A3B) + (Math.pow(obsA1A3b - expectedContA1A3b, 2) / expectedContA1A3b) + (Math.pow(obsA1A4B - expectedContA1A4B, 2) / expectedContA1A4B) + (Math.pow(obsA1A4b - expectedContA1A4b, 2) / expectedContA1A4b) + (Math.pow(obsA2A3B - expectedContA2A3B, 2) / expectedContA2A3B) + (Math.pow(obsA2A3b - expectedContA2A3b, 2) / expectedContA2A3b) + (Math.pow(obsA2A4B - expectedContA2A4B, 2) / expectedContA2A4B) + (Math.pow(obsA2A4b - expectedContA2A4b, 2) / expectedContA2A4b);
                    }
                } else {
                    //limpiar to lo de cont
                }
            }
        } else {
            double expectedContA1A3B = totalA1A3 * totalB / totalobs,
                    expectedContA1A3b = totalA1A3 * totalb / totalobs,
                    expectedContA1A4B = totalA1A4 * totalB / totalobs,
                    expectedContA1A4b = totalA1A4 * totalb / totalobs,
                    expectedContA2A3B = totalA2A3 * totalB / totalobs,
                    expectedContA2A3b = totalA2A3 * totalb / totalobs,
                    expectedContA2A4B = totalA2A4 * totalB / totalobs,
                    expectedContA2A4b = totalA2A4 * totalb / totalobs;

            expValues.put("contA1A3B", expectedContA1A3B);
            expValues.put("contA1A3b", expectedContA1A3b);
            expValues.put("contA1A4B", expectedContA1A4B);
            expValues.put("contA1A4b", expectedContA1A4b);
            expValues.put("contA2A3B", expectedContA2A3B);
            expValues.put("contA2A3b", expectedContA2A3b);
            expValues.put("contA2A4B", expectedContA2A4B);
            expValues.put("contA2A4b", expectedContA2A4b);

            if (expectedContA1A3B < 5 || expectedContA1A3b < 5 || expectedContA1A4B < 5 || expectedContA1A4b < 5 || expectedContA2A3B < 5 || expectedContA2A3b < 5 || expectedContA2A4B < 5 || expectedContA2A4b < 5) {
                //warning y clean
            } else {
                if ((expectedContA1A3B >= 5 && expectedContA1A3B <= 10) || (expectedContA1A3b >= 5 && expectedContA1A3b <= 10) || (expectedContA1A4B >= 5 && expectedContA1A4B <= 10) || (expectedContA1A4b >= 5 && expectedContA1A4b <= 10) || (expectedContA2A3B >= 5 && expectedContA2A3B <= 10) || (expectedContA2A3b >= 5 && expectedContA2A3b <= 10) || (expectedContA2A4B >= 5 && expectedContA2A4B <= 10) || (expectedContA2A4b >= 5 && expectedContA2A4b <= 10)) {
                    //warning
                    double chiCont = (Math.pow((obsA1A3B - expectedContA1A3B) - 0.5, 2) / expectedContA1A3B) + (Math.pow((obsA1A3b - expectedContA1A3b) - 0.5, 2) / expectedContA1A3b) + (Math.pow((obsA1A4B - expectedContA1A4B) - 0.5, 2) / expectedContA1A4B) + (Math.pow((obsA1A4b - expectedContA1A4b) - 0.5, 2) / expectedContA1A4b) + (Math.pow((obsA2A3B - expectedContA2A3B) - 0.5, 2) / expectedContA2A3B) + (Math.pow((obsA2A3b - expectedContA2A3b) - 0.5, 2) / expectedContA2A3b) + (Math.pow((obsA2A4B - expectedContA2A4B) - 0.5, 2) / expectedContA2A4B) + (Math.pow((obsA2A4b - expectedContA2A4b) - 0.5, 2) / expectedContA2A4b);
                } else {
                    double chiCont = (Math.pow(obsA1A3B - expectedContA1A3B, 2) / expectedContA1A3B) + (Math.pow(obsA1A3b - expectedContA1A3b, 2) / expectedContA1A3b) + (Math.pow(obsA1A4B - expectedContA1A4B, 2) / expectedContA1A4B) + (Math.pow(obsA1A4b - expectedContA1A4b, 2) / expectedContA1A4b) + (Math.pow(obsA2A3B - expectedContA2A3B, 2) / expectedContA2A3B) + (Math.pow(obsA2A3b - expectedContA2A3b, 2) / expectedContA2A3b) + (Math.pow(obsA2A4B - expectedContA2A4B, 2) / expectedContA2A4B) + (Math.pow(obsA2A4b - expectedContA2A4b, 2) / expectedContA2A4b);
                }
            }
        }
        return null;
    }

}
