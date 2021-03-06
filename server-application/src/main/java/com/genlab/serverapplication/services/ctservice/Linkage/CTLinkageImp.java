package com.genlab.serverapplication.services.ctservice.Linkage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.CTResult;

@Service
public class CTLinkageImp implements CTLinkage{

	private double getRffromQuadraticEquation(double a, double b, double c) {
		double root1, root2;
		double insideroot = Math.pow(b, 2) + 4 * a * c;
		
		root1 = (-b + Math.sqrt(insideroot)) / (2 * a);
		root2 = (-b - Math.sqrt(insideroot)) / (2 * a);
		if (root1 > 0) {
			return Math.sqrt(root1);
		} else {
			return Math.sqrt(root2);
		}
	}

	public CTResult testcross2Loci(int obsAB, int obsAb, int obsaB, int obsab) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if (obsAB <= 1000 && obsAb <= 1000 && obsaB <= 1000 && obsab <= 1000) {
			int total = obsAB + obsAb + obsaB + obsab;
			int obsA = obsAB + obsAb;
			int obsa = obsaB + obsab;
			int obsB = obsAB + obsaB;
			int obsb = obsAb + obsab;
			observedValues.put("obsA",  obsA);
			observedValues.put("obsa",  obsa);
			observedValues.put("obsB",  obsB);
			observedValues.put("obsb",  obsb);
			observedValues.put("total",  total);
			double expA = total / 2.0;
			double expa = total / 2.0;
			double expB = total / 2.0;
			double expb = total / 2.0;
			expectedValues.put("expA", expA);
			expectedValues.put("expa", expa);
			expectedValues.put("expB", expB);
			expectedValues.put("expb", expb);
			if (expA >= 5 && expa >= 5 && expB >= 5 && expb >= 5) {
				double chiA, chiB, chiInd, chiCont = 0, expInAB, expInAb, expInaB, expInab, expConAB = 0, expConAb = 0,
						expConaB = 0, expConab = 0;

				if ((expA <= 10 && expA >= 5) || (expa <= 10 && expa >= 5)) {
					chiA = Math.pow((obsA - expA) - 0.5, 2) / expA + Math.pow((obsa - expa) - 0.5, 2) / expa;
				} else {
					chiA = Math.pow((obsA - expA), 2) / expA + Math.pow((obsa - expa), 2) / expa;
				}
				resultValues.put("chiA", chiA);
				agree.put("chiA", (chiA > 3.841 ? "No" : "Yes"));
				////////////////////
				if ((expB <= 10 && expB >= 5) || (expb <= 10 && expb >= 5)) {
					chiB = Math.pow((obsB - expB) - 0.5, 2) / expB + Math.pow((obsb - expb) - 0.5, 2) / expb;
				} else {
					chiB = Math.pow((obsB - expB), 2) / expB + Math.pow((obsb - expb), 2) / expb;
				}
				resultValues.put("chiB", chiB);
				agree.put("chiB", (chiB > 3.841 ? "No" : "Yes"));
				////////////////////
				if (chiA <= 3.841 && chiB <= 3.841) {
					expInAB = total / 4.0;
					expInAb = total / 4.0;
					expInaB = total / 4.0;
					expInab = total / 4.0;
					expectedValues.put("expIndAB", expInAB);
					expectedValues.put("expIndAb", expInAb);
					expectedValues.put("expIndaB", expInaB);
					expectedValues.put("expIndab", expInab);
					if (expInAB < 5 || expInAb < 5 || expInaB < 5 || expInab < 5) {
						return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
					} else {
						if (chiA < 3.84 && chiB < 3.84) {
							if ((expInAB <= 10 && expInAB >= 5) || (expInAb <= 10 && expInAb >= 5)
									|| (expInaB <= 10 && expInaB >= 5) || (expInab <= 10 && expInab >= 5)) {
								chiInd = Math.pow((obsAB - expInAB) - 0.5, 2) / expInAB
										+ Math.pow((obsAb - expInAb) - 0.5, 2) / expInAb
										+ Math.pow((obsaB - expInaB) - 0.5, 2) / expInaB
										+ Math.pow((obsab - expInab) - 0.5, 2) / expInab;
							} else {
								chiInd = Math.pow((obsAB - expInAB), 2) / expInAB
										+ Math.pow((obsAb - expInAb), 2) / expInAb
										+ Math.pow((obsaB - expInaB), 2) / expInaB
										+ Math.pow((obsab - expInab), 2) / expInab;
							}
							resultValues.put("chiInd", chiInd);
							agree.put("chiInd", (chiInd > 7.82 ? "No" : "Yes"));

							double chiLink = chiInd - (chiA + chiB);
							resultValues.put("chiLink", chiLink);
							agree.put("chiLink", (chiInd > 3.841 ? "No" : "Yes"));
							if (chiInd < 7.82) { result = "The loci A,a and B,b are independent"; }
						}
						expConAB = (obsA * obsB) / (double)total;
						expConAb = (obsA * obsb) / (double)total;
						expConaB = (obsa * obsB) / (double)total;
						expConab = (obsa * obsb) / (double)total;
						expectedValues.put("contAB", expConAB);
						expectedValues.put("contAb", expConAb);
						expectedValues.put("contaB", expConaB);
						expectedValues.put("contab", expConab);
						if (expConAB >= 5 && expConAb >= 5 && expConaB >= 5 && expConab >= 5) {
							if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
									|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
								chiCont = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
										+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
										+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
										+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
							} else {
								chiCont = Math.pow((obsAB - expConAB), 2) / expConAB
										+ Math.pow((obsAb - expConAb), 2) / expConAb
										+ Math.pow((obsaB - expConaB), 2) / expConaB
										+ Math.pow((obsab - expConab), 2) / expConab;
							}
							resultValues.put("chiCont", chiCont);
							agree.put("chiCont", (chiCont > 3.841 ? "No" : "Yes"));
							if (chiCont < 3.841) {
								result = "The loci A,a and B,b are independent";
							} else {
								double rf, distance, lodZ;
								if ((chiCont >= 3.841) && (obsAB > obsAb)) {
									phases.put("phaseA", "Coupling");
									phases.put("phaseB", "AB/ab");
									rf = (obsAb + obsaB) / (double)total;
								} else {
									phases.put("phaseA", "Repulsion");
									phases.put("phaseB", "Ab/aB");
									rf = (obsAB + obsab) / (double)total;
								}
								distance = rf * 100;
								lodZ = Math.log((((Math.pow(1 - rf, (obsAB + obsab))) * (Math.pow(rf, (obsAb + obsaB)))
										/ Math.pow(0.5, total)))) / Math.log(10);
								result = "The loci A,a and B,b are linked";
								resultValues.put("rf", rf);
								resultValues.put("distance", distance);
								resultValues.put("lodZ", lodZ);
							}
						} else {
							return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
						}
					}
				} else {
					expConAB = (obsA * obsB) / (double)total;
					expConAb = (obsA * obsb) / (double)total;
					expConaB = (obsa * obsB) / (double)total;
					expConab = (obsa * obsb) / (double)total;
					expectedValues.put("contAB", expConAB);
					expectedValues.put("contAb", expConAb);
					expectedValues.put("contaB", expConaB);
					expectedValues.put("contab", expConab);
					if (expConAB >=  5 && expConAb >=  5 && expConaB >=  5 && expConab >=5) {
						if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
								|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
							chiCont = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
									+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
									+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
									+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
						} else {
							chiCont = Math.pow((obsAB - expConAB), 2) / expConAB
									+ Math.pow((obsAb - expConAb), 2) / expConAb
									+ Math.pow((obsaB - expConaB), 2) / expConaB
									+ Math.pow((obsab - expConab), 2) / expConab;
						}
						resultValues.put("chiCont", chiCont);
						agree.put("chiCont", (chiCont > 3.841 ? "No" : "Yes"));

						if (chiCont < 3.841) {
							result = "The loci A,a and B,b are independent";
						} else {
							double rf, distance, lodZ;
							if ((chiCont >= 3.841) && (obsAB > obsAb)) {
								phases.put("phaseA", "Coupling");
								phases.put("phaseB", "AB/ab");
								rf = (obsAb + obsaB) / (double)total;
							} else {
								phases.put("phaseA", "Repulsion");
								phases.put("phaseB", "Ab/aB");
								rf = (obsAB + obsab) / (double)total;
							}
							distance = rf * 100;
							lodZ = Math.log((((Math.pow(1 - rf, (obsAB + obsab))) * (Math.pow(rf, (obsAb + obsaB)))
									/ Math.pow(0.5, total))) / Math.log(10));
							result = "The loci A,a and B,b are linked";
							resultValues.put("rf", rf);
							resultValues.put("distance", distance);
							resultValues.put("lodZ", lodZ);
						}
					} else {
						return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
					}
				}
			} else {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			}
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 1000").build();
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

	public CTResult f22LociDominance(int obsAB, int obsAb, int obsaB, int obsab) {
		Map<String, String> agree = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		String result = "";
		if (obsAB < 1000 && obsAb < 1000 && obsaB < 1000 && obsab < 1000) {
			int total = obsAB + obsAb + obsaB + obsab;
			int obsA = obsAB + obsAb;
			int obsa = obsaB + obsab;
			int obsB = obsAB + obsaB;
			int obsb = obsAb + obsab;
			observedValues.put("obsA",  obsA);
			observedValues.put("obsa",  obsa);
			observedValues.put("obsB",  obsB);
			observedValues.put("obsb",  obsb);
			observedValues.put("total",  total);
			double expA = total * (3 / 4.0);
			double expa = total / 4.0;
			double expB = total * (3 / 4.0);
			double expb = total / 4.0;
			expectedValues.put("expA", expA);
			expectedValues.put("expa", expa);
			expectedValues.put("expB", expB);
			expectedValues.put("expb", expb);
			if (expA >= 5 && expa >= 5 && expB >= 5 && expb >= 5) {
				double chiA, chiB, chiInde, chiCont = 0, expInAB, expInAb, expInaB, expInab, expConAB = 0, expConAb = 0,
						expConaB = 0, expConab = 0;

				if ((expA <= 10 && expA >= 5) || (expa <= 10 && expa >= 5)) {
					chiA = Math.pow((obsA - expA) - 0.5, 2) / expA + Math.pow((obsa - expa) - 0.5, 2) / expa;
				} else {
					chiA = Math.pow((obsA - expA), 2) / expA + Math.pow((obsa - expa), 2) / expa;
				}
				resultValues.put("chiA", chiA);
				agree.put("chiA", (chiA > 3.841 ? "No" : "Yes"));

				////////////////////
				if ((expB <= 10 && expB >= 5) || (expb <= 10 && expb >= 5)) {
					chiB = Math.pow((obsB - expB) - 0.5, 2) / expB + Math.pow((obsb - expb) - 0.5, 2) / expb;
				} else {
					chiB = Math.pow((obsB - expB), 2) / expB + Math.pow((obsb - expb), 2) / expb;
				}
				resultValues.put("chiB", chiB);
				agree.put("chiB", (chiB > 3.841 ? "No" : "Yes"));

				////////////////////
				if (chiA <= 3.84 && chiB <= 3.84) {
					expInAB = total * (9 / 16.0);
					expInAb = total * (3 / 16.0);
					expInaB = total * (3 / 16.0);
					expInab = total * (1 / 16.0);
					expectedValues.put("expIndAB", expInAB);
					expectedValues.put("expIndAb", expInAb);
					expectedValues.put("expIndaB", expInaB);
					expectedValues.put("expIndab", expInab);
					if (expInAB >=  5 && expInAb >=  5 && expInaB >=  5 && expInab >= 5) {
						if ((expInAB <= 10 && expInAB >= 5) || (expInAb <= 10 && expInAb >= 5)
								|| (expInaB <= 10 && expInaB >= 5) || (expInab <= 10 && expInab >= 5)) {
							chiInde = Math.pow((obsAB - expInAB) - 0.5, 2) / expInAB
									+ Math.pow((obsAb - expInAb) - 0.5, 2) / expInAb
									+ Math.pow((obsaB - expInaB) - 0.5, 2) / expInaB
									+ Math.pow((obsab - expInab) - 0.5, 2) / expInab;
						} else {
							chiInde = Math.pow((obsAB - expInAB), 2) / expInAB
									+ Math.pow((obsAb - expInAb), 2) / expInAb
									+ Math.pow((obsaB - expInaB), 2) / expInaB
									+ Math.pow((obsab - expInab), 2) / expInab;
						}
						resultValues.put("chiInd", chiInde);
						agree.put("chiInd", (chiInde > 7.82 ? "No" : "Yes"));
						if(chiInde < 7.82){
							result = "The loci A,a and B,b are independent";
						}
						double chiLink = chiInde - (chiA + chiB);
						resultValues.put("chiLink", chiLink);
						agree.put("chiLink", (chiInde > 3.841 ? "No" : "Yes"));

						expConAB = (obsA * obsB) / (double)total;
						expConAb = (obsA * obsb) / (double)total;
						expConaB = (obsa * obsB) / (double)total;
						expConab = (obsa * obsb) / (double)total;
						expectedValues.put("contAB", expConAB);
						expectedValues.put("contAb", expConAb);
						expectedValues.put("contaB", expConaB);
						expectedValues.put("contab", expConab);
						if (expConAB >=  5 && expConAb >=  5 && expConaB >=  5 && expConab >= 5) {
							if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
									|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
								chiCont = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
										+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
										+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
										+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
							} else {
								chiCont = Math.pow((obsAB - expConAB), 2) / expConAB
										+ Math.pow((obsAb - expConAb), 2) / expConAb
										+ Math.pow((obsaB - expConaB), 2) / expConaB
										+ Math.pow((obsab - expConab), 2) / expConab;
							}
							resultValues.put("chiCont", chiCont);
							agree.put("chiCont", (chiCont > 3.841 ? "No" : "Yes"));

							if (chiCont < 3.841) {
								result = "The loci A,a and B,b are independent";
							}
							if (chiCont >= 3.841 && obsab > expConab) {
								double rf = 0, distance;
								phases.put("phaseA", "Coupling");
								phases.put("phaseB", "AB/ab");
								if (obsAb != 0 || obsaB != 0) {
									rf = 1 - getRffromQuadraticEquation(total,
											(2 * obsAb + ((2 * obsaB) + obsab)) - obsAB, obsab * 2);
								}
								distance = rf * 100;
								resultValues.put("rf", rf);
								resultValues.put("distance", distance);
								result = "The loci A,a and B,b are linked";
							}
							if (chiCont >= 3.841 && obsab > expConab) {
								double rf = 0, distance;
								phases.put("phaseA", "Repulsion");
								phases.put("phaseB", "Ab/aB");
								if (obsAb != 0 || obsaB != 0) {
									rf = getRffromQuadraticEquation(total, (2 * obsAb + ((2 * obsaB) + obsab)) - obsAB,
											obsab * 2);
								}
								distance = rf * 100;
								resultValues.put("rf", rf);
								resultValues.put("distance", distance);
								result = "The loci A,a and B,b are linked";
							}
						} else {
							return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
						}
					} else {
						return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
					}
				} else {
					expConAB = (obsA * obsB) / (double)total;
					expConAb = (obsA * obsb) / (double)total;
					expConaB = (obsa * obsB) / (double)total;
					expConab = (obsa * obsb) / (double)total;
					expectedValues.put("contAB", expConAB);
					expectedValues.put("contAb", expConAb);
					expectedValues.put("contaB", expConaB);
					expectedValues.put("contab", expConab);
					if (expConAB >=  5 && expConAb >=  5 && expConaB >=  5 && expConab >= 5) {
						if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
								|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
							chiCont = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
									+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
									+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
									+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
						} else {
							chiCont = Math.pow((obsAB - expConAB), 2) / expConAB
									+ Math.pow((obsAb - expConAb), 2) / expConAb
									+ Math.pow((obsaB - expConaB), 2) / expConaB
									+ Math.pow((obsab - expConab), 2) / expConab;
						}
						resultValues.put("chiCont", chiCont);
						agree.put("chiCont", (chiCont > 3.841 ? "No" : "Yes"));

						if (chiCont < 3.841) {
							result = "The loci A,a and B,b are independent";
						}
						if (chiCont >= 3.841 && obsab > expConab) {
							double rf = 0, distance;
							phases.put("phaseA", "Coupling");
							phases.put("phaseB", "AB/ab");
							if (obsAb != 0 || obsaB != 0) {
								rf = 1 - getRffromQuadraticEquation(total, (2 * obsAb + ((2 * obsaB) + obsab)) - obsAB,
										obsab * 2);
							}
							distance = rf * 100;
							resultValues.put("rf", rf);
							resultValues.put("distance", distance);
							result = "The loci A,a and B,b are linked";
						}
						if (chiCont >= 3.841 && obsab < expConab) {
							double rf = 0, distance;
							phases.put("phaseA", "Repulsion");
							phases.put("phaseB", "Ab/aB");
							if (obsAb != 0 || obsaB != 0) {
								rf = getRffromQuadraticEquation(total, (2 * obsAb + ((2 * obsaB) + obsab)) - obsAB,
										obsab * 2);
							}
							distance = rf * 100;
							resultValues.put("rf", rf);
							resultValues.put("distance", distance);
							result = "The loci A,a and B,b are linked";
						}
					}
				}
			} else {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			}
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 1000").build();
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

	public CTResult f22LociCodominance(int obsA1A1B1B1, int obsA1A1B1B2, int obsA1A1B2B2, int obsA1A2B1B1, int obsA1A2B1B2, int obsA1A2B2B2,
			int obsA2A2B1B1, int obsA2A2B1B2, int obsA2A2B2B2) {
		Map<String, String> agree = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		String result = "";
		if (obsA1A1B1B1 <= 1000 && obsA1A1B1B2 <= 1000 && obsA1A1B2B2 <= 1000 && obsA1A2B1B1 <= 1000 && obsA1A2B1B2 <= 1000
				&& obsA1A2B2B2 <= 1000 && obsA2A2B1B1 <= 1000 && obsA2A2B1B2 <= 1000 && obsA2A2B2B2 <= 1000) {
			int total = obsA1A1B1B1 + obsA1A1B1B2 + obsA1A1B2B2 + obsA1A2B1B1 + obsA1A2B1B2 + obsA1A2B2B2 + obsA2A2B1B1 + obsA2A2B1B2 + obsA2A2B2B2;
			int obsA1 = obsA1A1B1B1 + obsA1A1B1B2 + obsA1A1B2B2;
			int obsA1A2 = obsA1A2B1B1 + obsA1A2B1B2 + obsA1A2B2B2;
			int obsA2 = obsA2A2B1B1 + obsA2A2B1B2 + obsA2A2B2B2;
			double expA1 = total / 4.0;
			double expA1A2 = total / 2.0;
			double expA2 = total / 4.0;
			int obsB1 = obsA1A1B1B1 + obsA1A2B1B1 + obsA2A2B1B1;
			int obsB1B2 = obsA1A1B1B2 + obsA1A2B1B2 + obsA2A2B1B2;
			int obsB2 = obsA1A1B2B2 + obsA1A2B2B2 + obsA2A2B2B2;
			double expB1 = total / 4.0;
			double expB1B2 = total / 2.0;
			double expB2 = total / 4.0;

			observedValues.put("total",  total);
			observedValues.put("obsA1",  obsA1);
			observedValues.put("obsA1A2",  obsA1A2);
			observedValues.put("obsA2",  obsA2);
			expectedValues.put("expA1",  expA1);
			expectedValues.put("expA1A2",  expA1A2);
			expectedValues.put("expA2",  expA2);
			observedValues.put("obsB1",  obsB1);
			observedValues.put("obsB1B2",  obsB1B2);
			observedValues.put("obsB2",  obsB2);
			expectedValues.put("expB1",  expB1);
			expectedValues.put("expB1B2",  expB1B2);
			expectedValues.put("expB2",  expB2);

			double chiA, chiB, chiInde, chiCont, chiLink, expInA1B1, expInA1B1B2, expInA1B2, expInA2B1, expInA2B1B2,
					expInA2B2, expInA1A2B1, expInA1A2B2, expInA1A2B1B2, expConA1B1, expConA1B1B2, expConA1B2, expConA2B1,
					expConA2B1B2, expConA2B2, expConA1A2B1, expConA1A2B2, expConA1A2B1B2, rf, distance;

			if ((expA1 <= 10 && expA1 >= 5) || (expA1A2 <= 10 && expA1A2 >= 5) || (expA2 <= 10 && expA2 >= 5)) {
				chiA = Math.pow((obsA1 - expA1) - 0.5, 2) / expA1 + Math.pow((obsA2 - expA2) - 0.5, 2) / expA2
						+ Math.pow((obsA1A2 - expA1A2) - 0.5, 2) / expA1A2;
			} else {
				chiA = Math.pow((obsA1 - expA1), 2) / expA1 + Math.pow((obsA2 - expA2), 2) / expA2
						+ Math.pow((obsA1A2 - expA1A2), 2) / expA1A2;
			}
			resultValues.put("chiA", chiA);
			agree.put("chiA", (chiA > 5.99 ? "No" : "Yes"));

			if ((expB1 <= 10 && expB1 >= 5) || (expB1B2 <= 10 && expB1B2 >= 5) || (expB2 <= 10 && expB2 >= 5)) {
				chiB = Math.pow((obsB1 - expB1) - 0.5, 2) / expB1 + Math.pow((obsB2 - expB2) - 0.5, 2) / expB2
						+ Math.pow((obsB1B2 - expB1B2) - 0.5, 2) / expB1B2;
			} else {
				chiB = Math.pow((obsB1 - expB1), 2) / expB1 + Math.pow((obsB2 - expB2), 2) / expB2
						+ Math.pow((obsB1B2 - expB1B2), 2) / expB1B2;
			}
			resultValues.put("chiB", chiB);
			agree.put("chiB", (chiB > 5.99 ? "No" : "Yes"));

			if (chiA < 5.99 && chiB < 5.99) {
				expInA1B1 = (1 / 16.0) * total;
				expInA1B1B2 = (2 / 16.0) * total;
				expInA1B2 = (1 / 16.0) * total;
				expInA2B1 = (1 / 16.0) * total;
				expInA2B1B2 = (2 / 16.0) * total;
				expInA2B2 = (1 / 16.0) * total;
				expInA1A2B1 = (2 / 16.0) * total;
				expInA1A2B2 = (2 / 16.0) * total;
				expInA1A2B1B2 = (2 / 16.0) * total;
				resultValues.put("expIndA1B1", expInA1B1);
				resultValues.put("expIndA1B1B2", expInA1B1B2);
				resultValues.put("expIndA1B2", expInA1B2);
				resultValues.put("expIndA2B1", expInA2B1);
				resultValues.put("expIndA2B1B2", expInA2B1B2);
				resultValues.put("expIndA2B2", expInA2B2);
				resultValues.put("expIndA1A2B1", expInA1A2B1);
				resultValues.put("expIndA1A2B2", expInA1A2B2);
				resultValues.put("expIndA1A2B1B2", expInA1A2B1B2);
				if (expInA1B1 >= 5 && expInA1B1B2 >= 5 && expInA1B2 >= 5 && expInA2B1 >= 5 && expInA2B1B2 >= 5
						&& expInA2B2 >= 5 && expInA1A2B1 >= 5 && expInA1A2B2 >= 5 && expInA1A2B1B2 >= 5) {
					if ((expInA1B1 <= 10 && expInA1B1 >= 5) || (expInA1B1B2 <= 10 && expInA1B1B2 >= 5)
							|| (expInA1B2 <= 10 && expInA1B2 >= 5) || (expInA2B1 <= 10 && expInA2B1 >= 5)
							|| (expInA2B1B2 <= 10 && expInA2B1B2 >= 5) || (expInA2B2 <= 10 && expInA2B2 >= 5)
							|| (expInA1A2B1 <= 10 && expInA1A2B1 >= 5) || (expInA1A2B2 <= 10 && expInA1A2B2 >= 5)
							|| (expInA1A2B1B2 <= 10 && expInA1A2B1B2 >= 5)) {
						chiInde = Math.pow((obsA1A1B1B1 - expInA1B1) - 0.5, 2) / expInA1B1
								+ Math.pow((obsA1A1B2B2 - expInA1B2) - 0.5, 2) / expInA1B2
								+ Math.pow((obsA1A1B1B2 - expInA1B1B2) - 0.5, 2) / expInA1B1B2
								+ Math.pow((obsA2A2B1B1 - expInA2B1) - 0.5, 2) / expInA2B1
								+ Math.pow((obsA2A2B2B2 - expInA2B2) - 0.5, 2) / expInA2B2
								+ Math.pow((obsA2A2B1B2 - expInA2B1B2) - 0.5, 2) / expInA2B1B2
								+ Math.pow((obsA1A2B1B1 - expInA1A2B1) - 0.5, 2) / expInA1A2B1
								+ Math.pow((obsA1A2B2B2 - expInA1A2B2) - 0.5, 2) / expInA1A2B2
								+ Math.pow((obsA1A2B1B2 - expInA1A2B1B2) - 0.5, 2) / expInA1A2B1B2;
					} else {
						chiInde = Math.pow((obsA1A1B1B1 - expInA1B1), 2) / expInA1B1
								+ Math.pow((obsA1A1B2B2 - expInA1B2), 2) / expInA1B2
								+ Math.pow((obsA1A1B1B2 - expInA1B1B2), 2) / expInA1B1B2
								+ Math.pow((obsA2A2B1B1 - expInA2B1), 2) / expInA2B1
								+ Math.pow((obsA2A2B2B2 - expInA2B2), 2) / expInA2B2
								+ Math.pow((obsA2A2B1B2 - expInA2B1B2), 2) / expInA2B1B2
								+ Math.pow((obsA1A2B1B1 - expInA1A2B1), 2) / expInA1A2B1
								+ Math.pow((obsA1A2B2B2 - expInA1A2B2), 2) / expInA1A2B2
								+ Math.pow((obsA1A2B1B2 - expInA1A2B1B2), 2) / expInA1A2B1B2;
					}
					resultValues.put("chiInd", chiInde);
					agree.put("chiInd", (chiInde > 15.51 ? "No" : "Yes"));

					chiLink = chiInde - (chiA + chiB);
					agree.put("chiLink", (chiLink > 9.49 ? "No" : "Yes"));

					if (chiInde < 15.51) {
						result = "The loci A₁A₂ and B₁B₂ are independent";
					}
					expConA1B1 = (obsA1 * obsB1) / (double)total;
					expConA1B2 = (obsA1 * obsB2) / (double)total;
					expConA1B1B2 = (obsA1 * obsB1B2) / (double)total;
					expConA2B1 = (obsA2 * obsB1) / (double)total;
					expConA2B2 = (obsA2 * obsB2) / (double)total;
					expConA2B1B2 = (obsA2 * obsB1B2) / (double)total;
					expConA1A2B1 = (obsA1A2 * obsB1) / (double)total;
					expConA1A2B2 = (obsA1A2 * obsB2) / (double)total;
					expConA1A2B1B2 = (obsA1A2 * obsB1B2) / (double)total;
					resultValues.put("contA1B1", expConA1B1);
					resultValues.put("contA1B1B2", expConA1B1B2);
					resultValues.put("contA1B2", expConA1B2);
					resultValues.put("contA2B1", expConA2B1);
					resultValues.put("contA2B1B2", expConA2B1B2);
					resultValues.put("contA2B2", expConA2B2);
					resultValues.put("contA1A2B1", expConA1A2B1);
					resultValues.put("contA1A2B2", expConA1A2B2);
					resultValues.put("contA1A2B1B2", expConA1A2B1B2);
					if ((expConA1B1 <= 10 && expConA1B1 >= 5) || (expConA1B1B2 <= 10 && expConA1B1B2 >= 5)
							|| (expConA1B2 <= 10 && expConA1B2 >= 5) || (expConA2B1 <= 10 && expConA2B1 >= 5)
							|| (expConA2B1B2 <= 10 && expConA2B1B2 >= 5) || (expConA2B2 <= 10 && expConA2B2 >= 5)
							|| (expConA1A2B1 <= 10 && expConA1A2B1 >= 5) || (expConA1A2B2 <= 10 && expConA1A2B2 >= 5)
							|| (expConA1A2B1B2 <= 10 && expConA1A2B1B2 >= 5)) {
						chiCont = Math.pow((obsA1A1B1B1 - expConA1B1) - 0.5, 2) / expConA1B1
								+ Math.pow((obsA1A1B2B2 - expConA1B2) - 0.5, 2) / expConA1B2
								+ Math.pow((obsA1A1B1B2 - expConA1B1B2) - 0.5, 2) / expConA1B1B2
								+ Math.pow((obsA2A2B1B1 - expConA2B1) - 0.5, 2) / expConA2B1
								+ Math.pow((obsA2A2B2B2 - expConA2B2) - 0.5, 2) / expConA2B2
								+ Math.pow((obsA2A2B1B2 - expConA2B1B2) - 0.5, 2) / expConA2B1B2
								+ Math.pow((obsA1A2B1B1 - expConA1A2B1) - 0.5, 2) / expConA1A2B1
								+ Math.pow((obsA1A2B2B2 - expConA1A2B2) - 0.5, 2) / expConA1A2B2
								+ Math.pow((obsA1A2B1B2 - expConA1A2B1B2) - 0.5, 2) / expConA1A2B1B2;
					} else {
						chiCont = Math.pow((obsA1A1B1B1 - expConA1B1), 2) / expConA1B1
								+ Math.pow((obsA1A1B2B2 - expConA1B2), 2) / expConA1B2
								+ Math.pow((obsA1A1B1B2 - expConA1B1B2), 2) / expConA1B1B2
								+ Math.pow((obsA2A2B1B1 - expConA2B1), 2) / expConA2B1
								+ Math.pow((obsA2A2B2B2 - expConA2B2), 2) / expConA2B2
								+ Math.pow((obsA2A2B1B2 - expConA2B1B2), 2) / expConA2B1B2
								+ Math.pow((obsA1A2B1B1 - expConA1A2B1), 2) / expConA1A2B1
								+ Math.pow((obsA1A2B2B2 - expConA1A2B2), 2) / expConA1A2B2
								+ Math.pow((obsA1A2B1B2 - expConA1A2B1B2), 2) / expConA1A2B1B2;
					}

					resultValues.put("chiCont", chiCont);
					agree.put("chiCont", (chiCont > 9.49 ? "No" : "Yes"));

					if (chiCont < 9.49) {
						result = "The loci A₁A₂ and B₁B₂ are independent";
					} else {
						if (chiCont > 9.49 && obsA1A1B1B1 > obsA1A1B2B2 && obsA2A2B2B2 > obsA2A2B1B1) {
							rf = ((2 * (obsA1A1B2B2 + obsA2A2B1B1)) + ((obsA1A1B1B1 + obsA2A2B1B2 + obsA1A2B1B1 + obsA1A2B2B2) / 2)) / (double)total;
							phases.put("phaseA", "Coupling");
							phases.put("phaseB", "A₁B₁/A₂B₂");
						} else {
							rf = ((2 * (obsA1A1B1B1 + obsA2A2B2B2)) + ((obsA1A1B1B2 + obsA2A2B1B2 + obsA1A2B1B1 + obsA1A2B2B2) / 2)) / (double)total;
							phases.put("phaseA", "Repulsion");
							phases.put("phaseB", "A₁B₂/A₂B₁");
						}
						distance = rf * 100;
						result = "The loci A,A₂ and B₁B₂ are linked";
						resultValues.put("rf", rf);
						resultValues.put("distance", distance);
					}
				} else {
					return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
				}
			} else {
				expConA1B1 = (obsA1 * obsB1) / (double)total;
				expConA1B2 = (obsA1 * obsB2) / (double)total;
				expConA1B1B2 = (obsA1 * obsB1B2) / (double)total;
				expConA2B1 = (obsA2 * obsB1) / (double)total;
				expConA2B2 = (obsA2 * obsB2) / (double)total;
				expConA2B1B2 = (obsA2 * obsB1B2) / (double)total;
				expConA1A2B1 = (obsA1A2 * obsB1) / (double)total;
				expConA1A2B2 = (obsA1A2 * obsB2) / (double)total;
				expConA1A2B1B2 = (obsA1A2 * obsB1B2) / (double)total;
				resultValues.put("contA1B1", expConA1B1);
				resultValues.put("contA1B1B2", expConA1B1B2);
				resultValues.put("contA1B2", expConA1B2);
				resultValues.put("contA2B1", expConA2B1);
				resultValues.put("contA2B1B2", expConA2B1B2);
				resultValues.put("contA2B2", expConA2B2);
				resultValues.put("contA1A2B1", expConA1A2B1);
				resultValues.put("contA1A2B2", expConA1A2B2);
				resultValues.put("contA1A2B1B2", expConA1A2B1B2);
				if ((expConA1B1 <= 10 && expConA1B1 >= 5) || (expConA1B1B2 <= 10 && expConA1B1B2 >= 5)
						|| (expConA1B2 <= 10 && expConA1B2 >= 5) || (expConA2B1 <= 10 && expConA2B1 >= 5)
						|| (expConA2B1B2 <= 10 && expConA2B1B2 >= 5) || (expConA2B2 <= 10 && expConA2B2 >= 5)
						|| (expConA1A2B1 <= 10 && expConA1A2B1 >= 5) || (expConA1A2B2 <= 10 && expConA1A2B2 >= 5)
						|| (expConA1A2B1B2 <= 10 && expConA1A2B1B2 >= 5)) {
					chiCont = Math.pow((obsA1A1B1B1 - expConA1B1) - 0.5, 2) / expConA1B1
							+ Math.pow((obsA1A1B2B2 - expConA1B2) - 0.5, 2) / expConA1B2
							+ Math.pow((obsA1A1B1B2 - expConA1B1B2) - 0.5, 2) / expConA1B1B2
							+ Math.pow((obsA2A2B1B1 - expConA2B1) - 0.5, 2) / expConA2B1
							+ Math.pow((obsA2A2B2B2 - expConA2B2) - 0.5, 2) / expConA2B2
							+ Math.pow((obsA2A2B1B2 - expConA2B1B2) - 0.5, 2) / expConA2B1B2
							+ Math.pow((obsA1A2B1B1 - expConA1A2B1) - 0.5, 2) / expConA1A2B1
							+ Math.pow((obsA1A2B2B2 - expConA1A2B2) - 0.5, 2) / expConA1A2B2
							+ Math.pow((obsA1A2B1B2 - expConA1A2B1B2) - 0.5, 2) / expConA1A2B1B2;
				} else {
					chiCont = Math.pow((obsA1A1B1B1 - expConA1B1), 2) / expConA1B1
							+ Math.pow((obsA1A1B2B2 - expConA1B2), 2) / expConA1B2
							+ Math.pow((obsA1A1B1B2 - expConA1B1B2), 2) / expConA1B1B2
							+ Math.pow((obsA2A2B1B1 - expConA2B1), 2) / expConA2B1
							+ Math.pow((obsA2A2B2B2 - expConA2B2), 2) / expConA2B2
							+ Math.pow((obsA2A2B1B2 - expConA2B1B2), 2) / expConA2B1B2
							+ Math.pow((obsA1A2B1B1 - expConA1A2B1), 2) / expConA1A2B1
							+ Math.pow((obsA1A2B2B2 - expConA1A2B2), 2) / expConA1A2B2
							+ Math.pow((obsA1A2B1B2 - expConA1A2B1B2), 2) / expConA1A2B1B2;
				}

				resultValues.put("chiCont", chiCont);
				agree.put("chiCont", (chiCont > 9.49 ? "No" : "Yes"));

				if (chiCont < 9.49) {
					result = "The loci A₁A₂ and B₁B₂ are independent";
				} else {
					if (chiCont > 9.49 && obsA1A1B1B1 > obsA1A1B2B2 && obsA2A2B2B2 > obsA2A2B1B1) {
						rf = ((2 * (obsA1A1B2B2 + obsA2A2B1B1)) + ((obsA1A1B1B1 + obsA2A2B1B2 + obsA1A2B1B1 + obsA1A2B2B2) / 2)) / (double)total;
						phases.put("phaseA", "Coupling");
						phases.put("phaseB", "A₁B₁/A₂B₂");
					} else {
						rf = ((2 * (obsA1A1B1B1 + obsA2A2B2B2)) + ((obsA1A1B1B2 + obsA2A2B1B2 + obsA1A2B1B1 + obsA1A2B2B2) / 2)) / (double)total;
						phases.put("phaseA", "Repulsion");
						phases.put("phaseB", "A₁B₂/A₂B₁");
					}
					distance = rf * 100;
					result = "The loci A,A₂ and B₁B₂ are linked";
					resultValues.put("rf", rf);
					resultValues.put("distance", distance);
				}
			}
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 1000").build();
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

	public CTResult testcross3Loci(int obsABC, int obsabc, int obsABc, int obsabC, int obsaBC, int obsAbc, int obsAbC,
			int obsaBc) {
		Map<String, String> agree = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Integer> observedValues = new HashMap<>();
		String result = "";
		if (obsABC <= 1000 && obsabc <= 1000 && obsABc <= 1000 && obsabC <= 1000 && obsaBC <= 1000 && obsAbc <= 1000
				&& obsAbC <= 1000 && obsaBc <= 1000) {
			int total = obsABC + obsabc + obsABc + obsabC + obsaBC + obsAbc + obsAbC + obsaBc;
			int obsA = obsABC + obsABc + obsAbc + obsAbC;
			int obsa = obsabc + obsabC + obsaBC + obsaBc;
			int obsB = obsABC + obsABc + obsaBC + obsaBc;
			int obsb = obsabc + obsabC + obsAbc + obsAbC;
			int obsC = obsABC + obsabC + obsaBC + obsAbC;
			int obsc = obsabc + obsABc + obsAbc + obsaBc;
			int obsAB = obsABC + obsABc;
			int obsAb = obsAbc + obsAbC;
			int obsaB = obsaBC + obsaBc;
			int obsab = obsabc + obsabC;
			int obsAC = obsAbC + obsABC;
			int obsAc = obsABc + obsABc;
			int obsaC = obsabC + obsaBC;
			int obsac = obsabc + obsaBc;
			int obsBC = obsABC + obsaBC;
			int obsBc = obsaBc + obsABc;
			int obsbC = obsabC + obsAbC;
			int obsbc = obsabc + obsAbc;
			observedValues.put("obsA", obsA);
			observedValues.put("obsa",  obsa);
			observedValues.put("obsB",  obsB);
			observedValues.put("obsb",  obsb);
			observedValues.put("obsC",  obsC);
			observedValues.put("obsc",  obsc);
			observedValues.put("total",  total);
			observedValues.put("obsAB",  obsAB);
			observedValues.put("obsAb",  obsAb);
			observedValues.put("obsaB",  obsaB);
			observedValues.put("obsab",  obsab);
			observedValues.put("obsAC",  obsAC);
			observedValues.put("obsAc",  obsAc);
			observedValues.put("obsaC",  obsaC);
			observedValues.put("obsac",  obsac);
			observedValues.put("obsBC",  obsBC);
			observedValues.put("obsBc",  obsBc);
			observedValues.put("obsbC",  obsbC);
			observedValues.put("obsbc",  obsbc);
			double expDo = total / 2.0;
			double expRe = total / 2.0;
			expectedValues.put("expDo", expDo);
			expectedValues.put("expRe", expRe);
			if (expDo >= 5 && expRe >= 5) {
				double chiA, chiB, chiC, chiAB = 0, chiAC = 0, chiBC = 0, expInAB, expInAb, expInaB, expInab,
						expInAC, expInAc, expInaC, expInac, expInBC, expInbC, expInBc, expInbc, expConAB = 0,
						expConAb = 0, expConaB = 0, expConab = 0, expConAC = 0, expConAc = 0, expConaC = 0,
						expConac = 0, expConBC = 0, expConbC = 0, expConBc = 0, expConbc = 0, distAB = 0, distAC = 0, distBC= 0,
						rfAB = 0, rfAC = 0, rfBC = 0, lodzAB = 0, lodzAC= 0, lodzBC= 0;

				if ((expDo <= 10 && expDo >= 5) || (expRe <= 10 && expRe >= 5)) {
					chiA = Math.pow((obsA - expDo) - 0.5, 2) / expDo + Math.pow((obsa - expRe) - 0.5, 2) / expRe;
					chiB = Math.pow((obsB - expDo) - 0.5, 2) / expDo + Math.pow((obsb - expRe) - 0.5, 2) / expRe;
					chiC = Math.pow((obsC - expDo) - 0.5, 2) / expDo + Math.pow((obsc - expRe) - 0.5, 2) / expRe;
				} else {
					chiA = Math.pow((obsA - expDo), 2) / expDo + Math.pow((obsa - expRe), 2) / expRe;
					chiB = Math.pow((obsB - expDo), 2) / expDo + Math.pow((obsb - expRe), 2) / expRe;
					chiC = Math.pow((obsC - expDo), 2) / expDo + Math.pow((obsc - expRe), 2) / expRe;
				}
				resultValues.put("chiA", chiA);
				resultValues.put("chiB", chiB);
				resultValues.put("chiC", chiC);
				agree.put("chiA", (chiA > 3.841 ? "No" : "Yes"));
				agree.put("chiB", (chiB > 3.841 ? "No" : "Yes"));
				agree.put("chiC", (chiC > 3.841 ? "No" : "Yes"));

				////////////////////
				expInAB = total / 4.0;
				expInAb = total / 4.0;
				expInaB = total / 4.0;
				expInab = total / 4.0;
				expInAC = total / 4.0;
				expInAc = total / 4.0;
				expInaC = total / 4.0;
				expInac = total / 4.0;
				expInBC = total / 4.0;
				expInBc = total / 4.0;
				expInbc = total / 4.0;
				expInbC = total / 4.0;
				expectedValues.put("expIndAB", expInAB);
				expectedValues.put("expIndAb", expInAb);
				expectedValues.put("expIndaB", expInaB);
				expectedValues.put("expIndab", expInab);
				expectedValues.put("expIndAC", expInAC);
				expectedValues.put("expIndAc", expInAc);
				expectedValues.put("expIndaC", expInaC);
				expectedValues.put("expIndac", expInac);
				expectedValues.put("expIndBC", expInBC);
				expectedValues.put("expIndBc", expInBc);
				expectedValues.put("expIndbc", expInbc);
				expectedValues.put("expIndbC", expInbC);
				expConAB = (obsA * obsB) / (double)total;
				expConAb = (obsA * obsb) / (double)total;
				expConaB = (obsa * obsB) / (double)total;
				expConab = (obsa * obsb) / (double)total;
				expConAC = (obsA * obsC) / (double)total;
				expConAc = (obsA * obsc) / (double)total;
				expConaC = (obsa * obsC) / (double)total;
				expConac = (obsa * obsc) / (double)total;
				expConBC = (obsB * obsC) / (double)total;
				expConBc = (obsB * obsc) / (double)total;
				expConbc = (obsb * obsc) / (double)total;
				expConbC = (obsb * obsC) / (double)total;
				expectedValues.put("contAB", expConAB);
				expectedValues.put("contAb", expConAb);
				expectedValues.put("contaB", expConaB);
				expectedValues.put("contab", expConab);
				expectedValues.put("contAC", expConAC);
				expectedValues.put("contAc", expConAc);
				expectedValues.put("contaC", expConaC);
				expectedValues.put("contac", expConac);
				expectedValues.put("contBC", expConBC);
				expectedValues.put("contBc", expConBc);
				expectedValues.put("contbc", expConbc);
				expectedValues.put("contbC", expConbC);
				////////////////////
				if (expConAB >= 5 && expConAb >= 5 && expConaB >= 5 && expConab >= 5 && expConAC >= 5 && expConAc >= 5
						&& expConaC >= 5 && expConac >= 5 && expConBC >= 5 && expConBc >= 5 && expConbc >= 5
						&& expConbC >= 5) {
					if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
							|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
						chiAB = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
								+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
								+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
								+ Math.pow((obsab - expConab) - 0.5, 2) / expConAb;
					} else {
						chiAB = Math.pow((obsAB - expConAB), 2) / expConAB + Math.pow((obsAb - expConAb), 2) / expConAb
								+ Math.pow((obsaB - expConaB), 2) / expConaB
								+ Math.pow((obsab - expConab), 2) / expConAb;
					}
					if ((expConAC <= 10 && expConAC >= 5) || (expConAc <= 10 && expConAc >= 5)
							|| (expConaC <= 10 && expConaC >= 5) || (expConac <= 10 && expConac >= 5)) {
						chiAC = Math.pow((obsAC - expConAC) - 0.5, 2) / expConAC
								+ Math.pow((obsAc - expConAc) - 0.5, 2) / expConAc
								+ Math.pow((obsaC - expConaC) - 0.5, 2) / expConaC
								+ Math.pow((obsac - expConac) - 0.5, 2) / expConAc;
					} else {
						chiAC = Math.pow((obsAC - expConAC), 2) / expConAC + Math.pow((obsAc - expConAc), 2) / expConAc
								+ Math.pow((obsaC - expConaC), 2) / expConaC
								+ Math.pow((obsac - expConac), 2) / expConAc;
					}
					if ((expConBC <= 10 && expConBC >= 5) || (expConbC <= 10 && expConbC >= 5)
							|| (expConBc <= 10 && expConBc >= 5) || (expConbc <= 10 && expConbc >= 5)) {
						chiBC = Math.pow((obsBC - expConBC) - 0.5, 2) / expConBC
								+ Math.pow((obsbC - expConbC) - 0.5, 2) / expConbC
								+ Math.pow((obsBc - expConBc) - 0.5, 2) / expConBc
								+ Math.pow((obsbc - expConbc) - 0.5, 2) / expConbc;
					} else {
						chiBC = Math.pow((obsBC - expConBC), 2) / expConBC + Math.pow((obsbC - expConbC), 2) / expConbC
								+ Math.pow((obsBc - expConBc), 2) / expConBc
								+ Math.pow((obsbc - expConbc), 2) / expConbc;
					}
					resultValues.put("chiAB", chiAB);
					resultValues.put("chiAC", chiAC);
					resultValues.put("chiBC", chiBC);
				} else {
					return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
				}
				if (chiAB >= 3.841 && obsAB > obsAb) {
					rfAB = (obsAb + obsaB) / (double)total;
					distAB = rfAB * 100;
					lodzAB = Math.log((((Math.pow(1 - rfAB, (obsAB + obsab))) * (Math.pow(rfAB, (obsAb + obsaB)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiAB >= 3.841 && obsAB < obsAb) {
					rfAB = (obsAB + obsab) / (double)total;
					distAB = rfAB * 100;
					lodzAB = Math.log((((Math.pow(1 - rfAB, (obsAb + obsaB))) * (Math.pow(rfAB, (obsAB + obsab)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiAC >= 3.841 && obsAC > obsAc) {
					rfAC = (obsAc + obsaC) / (double)total;
					distAC = rfAC * 100;
					lodzAC = Math.log((((Math.pow(1 - rfAC, (obsAC + obsac))) * (Math.pow(rfAC, (obsAc + obsaC)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiAC >= 3.841 && obsAC < obsAc) {
					rfAC = (obsAC + obsac) / (double)total;
					distAC = rfAC * 100;
					lodzAC = Math.log((((Math.pow(1 - rfAC, (obsAc + obsaC))) * (Math.pow(rfAC, (obsAC + obsac)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiBC >= 3.841 && obsBC > obsBc) {
					rfBC = (obsBc + obsbC) / (double)total;
					distBC = rfBC * 100;
					lodzBC = Math.log((((Math.pow(1 - rfBC, (obsBC + obsbc))) * (Math.pow(rfBC, (obsBc + obsbC)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiBC >= 3.841 && obsBC < obsBc) {
					rfBC = (obsAb + obsaB) / (double)total;
					distBC = rfBC * 100;
					lodzBC = Math.log((((Math.pow(1 - rfBC, (obsBc + obsbC))) * (Math.pow(rfBC, (obsBC + obsbc)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				
				resultValues.put("rfAB", rfAB);
				resultValues.put("distAB", distAB);
				resultValues.put("lodzAB", lodzAB);
				resultValues.put("rfAC", rfAC);
				resultValues.put("distAC", distAC);
				resultValues.put("lodzAC", lodzAC);
				resultValues.put("rfBC", rfBC);
				resultValues.put("distBC", distBC);
				resultValues.put("lodzBC", lodzBC);

				if (chiAB < 3.841 && chiAC < 3.841 && chiBC < 3.841) {
					result = "The three loci are independent";
				} else {
					if (chiAB >= 3.841 && chiAC < 3.841 && chiBC < 3.841) {
						result = "The loci A,a and B,b are linked and the locus C,c is independent";
					} else {
						if (chiAB < 3.841 && chiAC >= 3.841 && chiBC < 3.841) {
							result = "The loci A,a and C,c are linked and the locus B,b is independent";
						} else {
							if (chiAB < 3.841 && chiAC < 3.841 && chiBC >= 3.841) {
								result = "The loci B,b and C,c are linked and the locus A,a is independent";
							} else {
								if (chiAB >= 3.841 && chiAC < 3.841 && chiBC >= 3.841) {
									result = "The extreme loci A, a and C,c behave as independent. The central locus  B,b behaves as linked to the extreme loci.";
								} else {
									if (chiAB < 3.841 && chiAC >= 3.841 && chiBC >= 3.841) {
										result = "The extreme loci A, a and B,b behave as independent. The central locus  C,c behaves as linked to the extreme loci.";
									} else {
										if (chiAB >= 3.841 && chiAC >= 3.841 && chiBC < 3.841) {
											result = "The extreme loci B,b and C,c behave as independent. The central locus A,a behaves as linked to the extreme loci.";
										} else {
											if (chiAB >= 3.841 && chiAC >= 3.841 && chiBC >= 3.841) {
												result = "The three loci are linked";
											}
										}
									}
								}
							}
						}
					}
				}

				int[] observados = { obsABC, obsabc, obsAbc, obsaBC, obsABc, obsabC, obsAbC, obsaBc };
				int max = 0;
				int iMax = 0;
				for (int i = 0; i < observados.length; i++) {
					if (max < observados[i])
						max = observados[i];
					iMax = i;
				}
				double coincidence = 0, interference;
				if (chiAB >= 3.841 && chiAC >= 3.841 && chiBC >= 3.641 && rfAB > rfAC && rfAB > rfBC) {
					phases.put("centre", "C,c");
					if (iMax == 0 || iMax == 1) {
						phases.put("phaseA", "ACB");
						phases.put("phaseB", "acb");
						coincidence = ((obsABc + obsabC) / (double)total) / (rfAC * rfBC);
						resultValues.put("coincidence", coincidence);
					} else {
						if (iMax == 2 || iMax == 3) {
							phases.put("phaseA", "Acb");
							phases.put("phaseB", "aCB");
							coincidence = ((obsAbC + obsaBc) / (double)total) / (rfAC * rfBC);
							resultValues.put("coincidence", coincidence);
						} else {
							if (iMax == 4 || iMax == 5) {
								phases.put("phaseA", "AcB");
								phases.put("phaseB", "aCb");
								coincidence = ((obsABC + obsabc) / (double)total) / (rfAC * rfBC);
								resultValues.put("coincidence", coincidence);
							} else {
								if (iMax == 6 || iMax == 7) {
									phases.put("phaseA", "ACb");
									phases.put("phaseB", "acB");
									coincidence = ((obsaBC + obsABc) / (double)total) / (rfAC * rfBC);
									resultValues.put("coincidence", coincidence);
								}
							}
						}
					}
				} else {
					if (chiAB >= 3.841 && chiAC >= 3.841 && chiBC >= 3.641 && rfAC > rfAB && rfAC > rfBC) {
						phases.put("centre", "B,b");
						if (iMax == 0 || iMax == 1) {
							phases.put("phaseA", "ABC");
							phases.put("phaseB", "abc");
							coincidence = ((obsAbC + obsaBc) / (double)total) / (rfAB * rfBC);
							resultValues.put("coincidence", coincidence);
						} else {
							if (iMax == 2 || iMax == 3) {
								phases.put("phaseA", "Abc");
								phases.put("phaseB", "aBC");
								coincidence = ((obsABc + obsabC) / (double)total) / (rfAB * rfBC);
								resultValues.put("coincidence", coincidence);
							} else {
								if (iMax == 4 || iMax == 5) {
									phases.put("phaseA", "ABc");
									phases.put("phaseB", "abC");
									coincidence = ((obsAbc + obsaBC) / (double)total) / (rfAB * rfBC);
									resultValues.put("coincidence", coincidence);
								} else {
									if (iMax == 6 || iMax == 7) {
										phases.put("phaseA", "AbC");
										phases.put("phaseB", "aBc");
										coincidence = ((obsabc + obsABC) / (double)total) / (rfAB * rfBC);
										resultValues.put("coincidence", coincidence);
									}
								}
							}
						}
					} else {
						if (chiAB >= 3.841 && chiAC >= 3.841 && chiBC >= 3.641 && rfBC > rfAB && rfBC > rfAC) {
							phases.put("centre", "A,a");
							if (iMax == 0 || iMax == 1) {
								phases.put("phaseA", "BAC");
								phases.put("phaseB", "bac");
								coincidence = ((obsaBC + obsAbc) / (double)total) / (rfAB * rfAC);
								resultValues.put("coincidence", coincidence);
							} else {
								if (iMax == 2 || iMax == 3) {
									phases.put("phaseA", "bAc");
									phases.put("phaseB", "BaC");
									coincidence = ((obsABC + obsabc) / (double)total) / (rfAB * rfAC);
									resultValues.put("coincidence", coincidence);
								} else {
									if (iMax == 4 || iMax == 5) {
										phases.put("phaseA", "BAc");
										phases.put("phaseB", "baC");
										coincidence = ((obsAbC + obsaBc) / (double)total) / (rfAB * rfAC);
										resultValues.put("coincidence", coincidence);
									} else {
										if (iMax == 6 || iMax == 7) {
											phases.put("phaseA", "bAC");
											phases.put("phaseB", "Bac");
											coincidence = ((obsABc + obsabC) / (double)total) / (rfAB * rfAC);
											resultValues.put("coincidence", coincidence);
										}
									}
								}
							}
						}
					}
				}
				interference = 1 - coincidence;
				resultValues.put("interference", interference);
			} else {
				return CTResult.builder().cleanInputs(true).feedbackMessage("An expected value is less than 5").build();
			}
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum value allowed is 1000").build();
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

	public CTResult testcrossDM(double r1, double tOs) {
		Map<String, Double> resultValues = new HashMap<>();
		if(r1 >= 0.5 || tOs >= 5000) {
			return CTResult.builder().cleanInputs(true).feedbackMessage("r can´t be higher than 0,5. The max number accepted for total offspring is 5000").build();
		} else {
			resultValues.put("repulsionAB", tOs * r1 / 2.0);
			resultValues.put("couplingAB", tOs * (1 - r1) / 2.0);
			resultValues.put("repulsionAb", tOs * (1 - r1) / 2.0);
			resultValues.put("couplingAb", tOs * r1 / 2.0);
			resultValues.put("repulsionaB", (1 - r1) / 2.0);
			resultValues.put("couplingaB", tOs * r1 / 2.0);
			resultValues.put("repulsionab", tOs * r1 / 2.0);
			resultValues.put("couplingab", tOs * (1 - r1) / 2.0);

			return CTResult.builder()
					.cleanInputs(false)
					.resultValues(resultValues)
					.build();
		}
	}

	public CTResult dominanceDM(double r1, double tOs) {
		Map<String, Double> resultValues = new HashMap<>();
		////////////////////////
		double repAB = 0, repAb = 0, repaB = 0, repab = 0, coupAB = 0, coupAb = 0, coupaB = 0, coupab = 0;
		///////////////////////
		if (r1 < 0.5 && tOs < 5000) {
			coupAB = tOs * ((2 + Math.pow((1 - r1), 2)) / 4.0);
			coupAb = tOs * ((1 - Math.pow((1 - r1), 2)) / 4.0);
			coupaB = tOs * ((1 - Math.pow((1 - r1), 2)) / 4.0);
			coupab = tOs * (Math.pow((1 - r1), 2) / 4.0);

			repAB = tOs * ((2 + Math.pow(r1, 2)) / 4.0);
			repAb = tOs * ((1 - Math.pow(r1, 2)) / 4.0);
			repaB = tOs * ((1 - Math.pow(r1, 2)) / 4.0);
			repab = tOs * (Math.pow(r1, 2) / 4.0);
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("r can´t be higher than 0,5. The max number accepted for total offspring is 5000").build();
		}

		resultValues.put("couplingAB", coupAB);
		resultValues.put("couplingAb", coupAb);
		resultValues.put("couplingaB", coupaB);
		resultValues.put("couplingab", coupab);
		resultValues.put("repulsionAB", repAB);
		resultValues.put("repulsionAb", repAb);
		resultValues.put("repulsionaB", repaB);
		resultValues.put("repulsionab", repab);

		return CTResult.builder()
				.cleanInputs(false)
				.resultValues(resultValues)
				.build();
	}

	public CTResult codominanceDM(double r1, double tOs) {
		Map<String, Double> resultValues = new HashMap<>();
		////////////////////////
		double repA1A1B1B1 = 0, repA1A2B1B1 = 0, repA2A2B1B1 = 0, repA1A1B1B2 = 0, repA1A2B1B2 = 0, repA2A2B1B2 = 0,
				repA1A1B2B2 = 0, repA1A2B2B2 = 0, repA2A2B2B2 = 0, coupA1A1B1B1 = 0, coupA1A2B1B1 = 0, coupA2A2B1B1 = 0,
				coupA1A1B1B2 = 0, coupA1A2B1B2 = 0, coupA2A2B1B2 = 0, coupA1A1B2B2 = 0, coupA1A2B2B2 = 0,
				coupA2A2B2B2 = 0;
		///////////////////////
		if (r1 < 0.5 && tOs < 5000) {
			coupA1A1B1B1 = tOs * ((Math.pow((1 - r1), 2)) / 4.0);
			coupA1A2B1B1 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			coupA2A2B1B1 = tOs * ((Math.pow((r1), 2)) / 4.0);
			coupA1A1B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			coupA1A2B1B2 = ((tOs * (Math.pow((1 - r1), 2))) / 2.0) + ((tOs * (Math.pow((r1), 2))) / 2.0);
			coupA2A2B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			coupA1A1B2B2 = tOs * ((Math.pow((r1), 2)) / 4.0);
			coupA1A2B2B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			coupA2A2B2B2 = tOs * ((Math.pow((1 - r1), 2)) / 4.0);

			repA1A1B1B1 = tOs * ((Math.pow((r1), 2)) / 4.0);
			repA1A2B1B1 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			repA2A2B1B1 = tOs * ((Math.pow((1 - r1), 2)) / 4.0);
			repA1A1B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			repA1A2B1B2 = ((tOs * (Math.pow((1 - r1), 2))) / 2.0) + ((tOs * (Math.pow((r1), 2))) / 2.0);
			repA2A2B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			repA1A1B2B2 = tOs * ((Math.pow((1 - r1), 2)) / 4.0);
			repA1A2B2B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2.0);
			repA2A2B2B2 = tOs * ((Math.pow((r1), 2)) / 4.0);
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("r can´t be higher than 0,5. The max number accepted for total offspring is 5000").build();
		}

		resultValues.put("couplingA1A1B1B1", coupA1A1B1B1);
		resultValues.put("couplingA1A2B1B1", coupA1A2B1B1);
		resultValues.put("couplingA2A2B1B1", coupA2A2B1B1);
		resultValues.put("couplingA1A1B1B2", coupA1A1B1B2);
		resultValues.put("couplingA1A2B1B2", coupA1A2B1B2);
		resultValues.put("couplingA2A2B1B2", coupA2A2B1B2);
		resultValues.put("couplingA1A1B2B2", coupA1A1B2B2);
		resultValues.put("couplingA1A2B2B2", coupA1A2B2B2);
		resultValues.put("couplingA2A2B2B2", coupA2A2B2B2);

		resultValues.put("repulsionA1A1B1B1", repA1A1B1B1);
		resultValues.put("repulsionA1A2B1B1", repA1A2B1B1);
		resultValues.put("repulsionA2A2B1B1", repA2A2B1B1);
		resultValues.put("repulsionA1A1B1B2", repA1A1B1B2);
		resultValues.put("repulsionA1A2B1B2", repA1A2B1B2);
		resultValues.put("repulsionA2A2B1B2", repA2A2B1B2);
		resultValues.put("repulsionA1A1B2B2", repA1A1B2B2);
		resultValues.put("repulsionA1A2B2B2", repA1A2B2B2);
		resultValues.put("repulsionA2A2B2B2", repA2A2B2B2);

		return CTResult.builder()
				.cleanInputs(false)
				.resultValues(resultValues)
				.build();
	}

	public CTResult tresLociDM(double r1, double r2, double cOc, double tOs) {
		Map<String, String> phases = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		////////////////////////
		double vAbCn = 0, vaBcn = 0, vAbcn = 0, vaBCn = 0, vABcn = 0, vabCn = 0, vabcn = 0, vABCn = 0, vAbC2 = 0,
				vaBc2 = 0, vAbc2 = 0, vaBC2 = 0, vABc2 = 0, vabC2 = 0, vabc2 = 0, vABC2 = 0, vAbC3 = 0, vaBc3 = 0,
				vAbc3 = 0, vaBC3 = 0, vABc3 = 0, vabC3 = 0, vabc3 = 0, vABC3 = 0, vAbC4 = 0, vaBc4 = 0, vAbc4 = 0,
				vaBC4 = 0, vABc4 = 0, vabC4 = 0, vabc4 = 0, vABC4 = 0, interference = 0, rf = 0;
		///////////////////////
		if (r1 < 0.5 && r2 < 0.5 && tOs < 5000) {
			vAbCn = tOs * ((r1 * r2 * cOc) / 2.0);
			vaBcn = tOs * ((r1 * r2 * cOc) / 2.0);
			vAbcn = tOs * ((r1 - (r1 * r2 * cOc)) / 2.0);
			vaBCn = tOs * ((r1 - (r1 * r2 * cOc)) / 2.0);
			vABcn = tOs * ((r2 - (r1 * r2 * cOc)) / 2.0);
			vabCn = tOs * ((r2 - (r1 * r2 * cOc)) / 2.0);
			vabcn = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;
			vABCn = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;

			interference = 1 - cOc;
			rf = r1 + r2 - (r1 * r2 * cOc * 2);

			vABC2 = tOs * ((r2 - (r1 * r2 * cOc)) / 2.0);
			vaBC2 = tOs * ((r1 * r2 * cOc) / 2.0);
			vAbC2 = tOs * ((r1 - (r1 * r2 * cOc)) / 2.0);
			vabc2 = tOs * ((r2 - (r1 * r2 * cOc)) / 2.0);
			vABc2 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;
			vaBc2 = tOs * ((r1 - (r1 * r2 * cOc)) / 2.0);
			vAbc2 = tOs * ((r1 * r2 * cOc) / 2.0);
			vabC2 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;

			vABC3 = tOs * ((r1 - (r1 * r2 * cOc)) / 2.0);
			vaBC3 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;
			vAbC3 = tOs * ((r2 - (r1 * r2 * cOc))) / 2.0;
			vabc3 = tOs * ((r1 - (r1 * r2 * cOc))) / 2.0;
			vABc3 = tOs * ((r1 * r2 * cOc)) / 2.0;
			vaBc3 = tOs * ((r2 - (r1 * r2 * cOc))) / 2.0;
			vAbc3 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;
			vabC3 = tOs * (r1 * r2 * cOc) / 2.0;

			vABC4 = tOs * (r1 * r2 * cOc) / 2.0;
			vaBC4 = tOs * ((r2 - (r1 * r2 * cOc))) / 2.0;
			vAbC4 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;
			vabc4 = tOs * ((r1 * r2 * cOc) / 2.0);
			vABc4 = tOs * ((r1 - (r1 * r2 * cOc)) / 2.0);
			vaBc4 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2.0;
			vAbc4 = tOs * ((r2 - (r1 * r2 * cOc))) / 2.0;
			vabC4 = tOs * ((r1 - (r1 * r2 * cOc))) / 2.0;
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("r can´t be higher than 0,5. The max number accepted for total offspring is 5000").build();
		}

		resultValues.put("vAbCn", vAbCn);
		resultValues.put("vaBcn", vaBcn);
		resultValues.put("vAbcn", vAbcn);
		resultValues.put("vaBCn", vaBCn);
		resultValues.put("vABcn", vABcn);
		resultValues.put("vabCn", vabCn);
		resultValues.put("vabcn", vabcn);
		resultValues.put("vABCn", vABCn);

		resultValues.put("vAbC2", vAbC2);
		resultValues.put("vaBc2", vaBc2);
		resultValues.put("vAbc2", vAbc2);
		resultValues.put("vaBC2", vaBC2);
		resultValues.put("vABc2", vABc2);
		resultValues.put("vabC2", vabC2);
		resultValues.put("vabc2", vabc2);
		resultValues.put("vABC2", vABC2);

		resultValues.put("vAbC3", vAbC3);
		resultValues.put("vaBc3", vaBc3);
		resultValues.put("vAbc3", vAbc3);
		resultValues.put("vaBC3", vaBC3);
		resultValues.put("vABc3", vABc3);
		resultValues.put("vabC3", vabC3);
		resultValues.put("vabc3", vabc3);
		resultValues.put("vABC3", vABC3);

		resultValues.put("vAbC4", vAbC4);
		resultValues.put("vaBc4", vaBc4);
		resultValues.put("vAbc4", vAbc4);
		resultValues.put("vaBC4", vaBC4);
		resultValues.put("vABc4", vABc4);
		resultValues.put("vabC4", vabC4);
		resultValues.put("vabc4", vabc4);
		resultValues.put("vABC4", vABC4);

		if (rf >= 0.5) {
			phases.put("rf", "r is >=0.5");
		} else {
			phases.put("rf", String.valueOf(rf));
		}
		resultValues.put("interference", interference);

		return CTResult.builder()
				.cleanInputs(false)
				.phase(phases)
				.resultValues(resultValues)
				.build();
	}
}