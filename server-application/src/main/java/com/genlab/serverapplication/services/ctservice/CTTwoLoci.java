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
                            }
                        }
                        double chiCont = (Math.pow(obsA1A1B1B1 - expectedContA1B1, 2) / expectedContA1B1) + (Math.pow(obsA1A1B2B2 - expectedContA1B2, 2) / expectedContA1B2) + (Math.pow(obsA1A1B1B2 - expectedContA1B1B2, 2) / expectedContA1B1B2) + (Math.pow(obsA2A2B1B1 - expectedContA2B1, 2) / expectedContA2B1) + (Math.pow(obsA2A2B2B2 - expectedContA2B2, 2) / expectedContA2B2) + (Math.pow(obsA2A2B1B2 - expectedContA2B1B2, 2) / expectedContA2B1B2) + (Math.pow(obsA1A2B1B1 - expectedContA1A2B1, 2) / expectedContA1A2B1) + (Math.pow(obsA1A2B2B2 - expectedContA1A2B2, 2) / expectedContA1A2B2) + (Math.pow(obsA1A2B1B2 - expectedContA1A2B1B2, 2) / expectedContA1A2B1B2);
                    }
                }
                //AQUI ME HE QUEDADO, TENGO QUE GUARDAR LOS EXPIND
                expValues.put("expectedIndA1B1", expectedIndA1B1);
                expValues.put("expectedIndA1B2", expectedIndA1B2);
                expValues.put("expectedIndA1B1B2", expectedIndA1B1B2);
                expValues.put("expectedIndA2B1", expectedIndA2B1);
                expValues.put("expectedIndA2B2", expectedIndA2B2);
                expValues.put("expectedIndA2B1B2", expectedIndA2B1B2);
                expValues.put("expectedIndA1A2B1", expectedIndA1A2B1);
                expValues.put("expectedIndA1A2B2", expectedIndA1A2B2);
                expValues.put("expectedIndA1A2B1B2", expectedIndA1A2B1B2);
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
}
