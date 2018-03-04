package com.genlab.serverapplication.services.ctservice.Linkage;

import java.util.HashMap;
import java.util.Map;

import com.genlab.serverapplication.models.CTResult;
import org.springframework.stereotype.Service;

@Service
public class CTLinkageImp implements CTLinkage{

	private double getRffromQuadraticEquation(double a, double b, double c) {
		double root1, root2;
		root1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
		root2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
		if (root1 > 0) {
			return Math.sqrt(root1);
		} else {
			return Math.sqrt(root2);
		}
	}

	/* TESTCROSS 2 LOCI */
	public CTResult testcross2Loci(int obsAB, int obsAb, int obsaB, int obsab) {
		Map<String, String> agree = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Double> observedValues = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		String result = "";
		if (obsAB <= 1000 && obsAb <= 1000 && obsaB <= 1000 && obsab <= 1000) {
			int total = obsAB + obsAb + obsaB + obsab;
			int obsA = obsAB + obsAb;
			int obsa = obsaB + obsab;
			int obsB = obsAB + obsaB;
			int obsb = obsAb + obsab;
			observedValues.put("obsA", (double) obsA);
			observedValues.put("obsa", (double) obsa);
			observedValues.put("obsB", (double) obsB);
			observedValues.put("obsb", (double) obsb);
			observedValues.put("total", (double) total);
			double expA = total / 2;
			double expa = total / 2;
			double expB = total / 2;
			double expb = total / 2;
			expectedValues.put("expA", expA);
			expectedValues.put("expa", expa);
			expectedValues.put("expB", expB);
			expectedValues.put("expb", expb);
			if (expA >= 5 && expa >= 5 && expB >= 5 && expb >= 5) {
				double chiA, chiB, chiInd, chiCon = 0, expInAB, expInAb, expInaB, expInab, expConAB = 0, expConAb = 0,
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
					expInAB = total / 4;
					expInAb = total / 4;
					expInaB = total / 4;
					expInab = total / 4;
					expectedValues.put("expectedIndAB", expInAB);
					expectedValues.put("expectedIndAb", expInAb);
					expectedValues.put("expectedIndaB", expInaB);
					expectedValues.put("expectedIndab", expInab);
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
							agree.put("chiInd", (chiInd > 3.841 ? "No" : "Yes"));
							if (chiInd < 7.82) { result = "The loci A,a and B,b are independent"; }
						}
						expConAB = (obsA * obsB) / total;
						expConAb = (obsA * obsb) / total;
						expConaB = (obsa * obsB) / total;
						expConab = (obsa * obsb) / total;
						expectedValues.put("expConAB", expConAB);
						expectedValues.put("expConAb", expConAb);
						expectedValues.put("expConaB", expConaB);
						expectedValues.put("expConab", expConab);
						if (expConAB >= 5 && expConAb >= 5 && expConaB >= 5 && expConab >= 5) {
							if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
									|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
								chiCon = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
										+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
										+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
										+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
							} else {
								chiCon = Math.pow((obsAB - expConAB), 2) / expConAB
										+ Math.pow((obsAb - expConAb), 2) / expConAb
										+ Math.pow((obsaB - expConaB), 2) / expConaB
										+ Math.pow((obsab - expConab), 2) / expConab;
							}
							resultValues.put("chiCon", chiCon);
							agree.put("chiCon", (chiCon > 3.841 ? "No" : "Yes"));
							if (chiCon < 3.841) {
								result = "The loci A,a and B,b are independent";
							} else {
								double rf, distance, lodZ;
								if ((chiCon >= 3.841) && (obsAB > obsAb)) {
									phases.put("phaseA", "Coupling");
									phases.put("phaseB", "AB/ab");
									rf = (obsAb + obsaB) / total;
								} else {
									phases.put("phaseA", "Repulsion");
									phases.put("phaseB", "Ab/aB");
									rf = (obsAB + obsab) / total;
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
					expConAB = (obsA * obsB) / total;
					expConAb = (obsA * obsb) / total;
					expConaB = (obsa * obsB) / total;
					expConab = (obsa * obsb) / total;
					expectedValues.put("expConAB", expConAB);
					expectedValues.put("expConAb", expConAb);
					expectedValues.put("expConaB", expConaB);
					expectedValues.put("expConab", expConab);
					if (expConAB >=  5 && expConAb >=  5 && expConaB >=  5 && expConab >=5) {
						if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
								|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
							chiCon = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
									+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
									+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
									+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
						} else {
							chiCon = Math.pow((obsAB - expConAB), 2) / expConAB
									+ Math.pow((obsAb - expConAb), 2) / expConAb
									+ Math.pow((obsaB - expConaB), 2) / expConaB
									+ Math.pow((obsab - expConab), 2) / expConab;
						}
						resultValues.put("chiCon", chiCon);
						agree.put("chiCon", (chiCon > 3.841 ? "No" : "Yes"));

						if (chiCon < 3.841) {
							result = "The loci A,a and B,b are independent";
						} else {
							double rf, distance, lodZ;
							if ((chiCon >= 3.841) && (obsAB > obsAb)) {
								phases.put("phaseA", "Coupling");
								phases.put("phaseB", "AB/ab");
								rf = (obsAb + obsaB) / total;
							} else {
								phases.put("phaseA", "Repulsion");
								phases.put("phaseB", "Ab/aB");
								rf = (obsAB + obsab) / total;
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
		Map<String, Double> observedValues = new HashMap<>();
		String result = "";
		if (obsAB < 1000 && obsAb < 1000 && obsaB < 1000 && obsab < 1000) {
			int total = obsAB + obsAb + obsaB + obsab;
			int obsA = obsAB + obsAb;
			int obsa = obsaB + obsab;
			int obsB = obsAB + obsaB;
			int obsb = obsAb + obsab;
			observedValues.put("obsA", (double) obsA);
			observedValues.put("obsa", (double) obsa);
			observedValues.put("obsB", (double) obsB);
			observedValues.put("obsb", (double) obsb);
			observedValues.put("total", (double) total);
			double expA = total * (3 / 4);
			double expa = total / 4;
			double expB = total * (3 / 4);
			double expb = total / 4;
			expectedValues.put("expA", expA);
			expectedValues.put("expa", expa);
			expectedValues.put("expB", expB);
			expectedValues.put("expb", expb);
			if (expA >= 5 && expa >= 5 && expB >= 5 && expb >= 5) {
				double chiA, chiB, chiInde, chiCon = 0, expInAB, expInAb, expInaB, expInab, expConAB = 0, expConAb = 0,
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
					expInAB = total * (9 / 16);
					expInAb = total * (3 / 16);
					expInaB = total * (3 / 16);
					expInab = total * (1 / 16);
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

						expConAB = (obsA * obsB) / total;
						expConAb = (obsA * obsb) / total;
						expConaB = (obsa * obsB) / total;
						expConab = (obsa * obsb) / total;
						expectedValues.put("expConAB", expConAB);
						expectedValues.put("expConAb", expConAb);
						expectedValues.put("expConaB", expConaB);
						expectedValues.put("expConab", expConab);
						if (expConAB >=  5 && expConAb >=  5 && expConaB >=  5 && expConab >= 5) {
							if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
									|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
								chiCon = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
										+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
										+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
										+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
							} else {
								chiCon = Math.pow((obsAB - expConAB), 2) / expConAB
										+ Math.pow((obsAb - expConAb), 2) / expConAb
										+ Math.pow((obsaB - expConaB), 2) / expConaB
										+ Math.pow((obsab - expConab), 2) / expConab;
							}
							resultValues.put("chiCon", chiCon);
							agree.put("chiCon", (chiCon > 3.841 ? "No" : "Yes"));

							if (chiCon < 3.841) {
								result = "The loci A,a and B,b are independent";
							}
							if (chiCon >= 3.841 && obsab > expConab) {
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
							if (chiCon >= 3.841 && obsab > expConab) {
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
					expConAB = (obsA * obsB) / total;
					expConAb = (obsA * obsb) / total;
					expConaB = (obsa * obsB) / total;
					expConab = (obsa * obsb) / total;
					expectedValues.put("expConAB", expConAB);
					expectedValues.put("expConAb", expConAb);
					expectedValues.put("expConaB", expConaB);
					expectedValues.put("expConab", expConab);
					if (expConAB >=  5 && expConAb >=  5 && expConaB >=  5 && expConab >= 5) {
						if ((expConAB <= 10 && expConAB >= 5) || (expConAb <= 10 && expConAb >= 5)
								|| (expConaB <= 10 && expConaB >= 5) || (expConab <= 10 && expConab >= 5)) {
							chiCon = Math.pow((obsAB - expConAB) - 0.5, 2) / expConAB
									+ Math.pow((obsAb - expConAb) - 0.5, 2) / expConAb
									+ Math.pow((obsaB - expConaB) - 0.5, 2) / expConaB
									+ Math.pow((obsab - expConab) - 0.5, 2) / expConab;
						} else {
							chiCon = Math.pow((obsAB - expConAB), 2) / expConAB
									+ Math.pow((obsAb - expConAb), 2) / expConAb
									+ Math.pow((obsaB - expConaB), 2) / expConaB
									+ Math.pow((obsab - expConab), 2) / expConab;
						}
						resultValues.put("chiCon", chiCon);
						agree.put("chiCon", (chiCon > 3.841 ? "No" : "Yes"));

						if (chiCon < 3.841) {
							result = "The loci A,a and B,b are independent";
						}
						if (chiCon >= 3.841 && obsab > expConab) {
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
						if (chiCon >= 3.841 && obsab > expConab) {
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

	public CTResult f22LociCodominance(int obsAABB, int obsAABb, int obsAAbb, int obsAaBB, int obsAaBb, int obsAabb,
			int obsaaBB, int obsaaBb, int obsaabb) {
		Map<String, String> agree = new HashMap<>();
		Map<String, String> phases = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		Map<String, Double> expectedValues = new HashMap<>();
		Map<String, Double> observedValues = new HashMap<>();
		String result = "";
		if (obsAABB <= 1000 && obsAABb <= 1000 && obsAAbb <= 1000 && obsAaBB <= 1000 && obsAaBb <= 1000
				&& obsAabb <= 1000 && obsaaBB <= 1000 && obsaaBb <= 1000 && obsaabb <= 1000) {
			int total = obsAABB + obsAABb + obsAAbb + obsAaBB + obsAaBb + obsAabb + obsaaBB + obsaaBb + obsaabb;
			int obsA1 = obsAABB + obsAABb + obsAAbb;
			int obsA1A2 = obsAaBB + obsAaBb + obsAabb;
			int obsA2 = obsaaBB + obsaaBb + obsaabb;
			int expA1 = total / 4;
			int expA1A2 = total / 2;
			int expA2 = total / 4;
			int obsB1 = obsAABB + obsAaBB + obsaaBB;
			int obsB1B2 = obsAABb + obsAaBb + obsaaBb;
			int obsB2 = obsAAbb + obsAabb + obsaabb;
			int expB1 = total / 4;
			int expB1B2 = total / 2;
			int expB2 = total / 4;

			observedValues.put("total", (double) total);
			observedValues.put("obsA1", (double) obsA1);
			observedValues.put("obsA1A2", (double) obsA1A2);
			observedValues.put("obsA2", (double) obsA2);
			expectedValues.put("expA1", (double) expA1);
			expectedValues.put("expA1A2", (double) expA1A2);
			expectedValues.put("expA2", (double) expA2);
			observedValues.put("obsB1", (double) obsB1);
			observedValues.put("obsB1B2", (double) obsB1B2);
			observedValues.put("obsB2", (double) obsB2);
			expectedValues.put("expB1", (double) expB1);
			expectedValues.put("expB1B2", (double) expB1B2);
			expectedValues.put("expB2", (double) expB2);

			double chiA, chiB, chiInde, chiCon, chiLink, expInA1B1, expInA1B1B2, expInA1B2, expInA2B1, expInA2B1B2,
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
				expInA1B1 = (1 / 16) * total;
				expInA1B1B2 = (2 / 16) * total;
				expInA1B2 = (1 / 16) * total;
				expInA2B1 = (1 / 16) * total;
				expInA2B1B2 = (2 / 16) * total;
				expInA2B2 = (1 / 16) * total;
				expInA1A2B1 = (2 / 16) * total;
				expInA1A2B2 = (2 / 16) * total;
				expInA1A2B1B2 = (2 / 16) * total;
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
						chiInde = Math.pow((obsAABB - expInA1B1) - 0.5, 2) / expInA1B1
								+ Math.pow((obsAAbb - expInA1B2) - 0.5, 2) / expInA1B2
								+ Math.pow((obsAABb - expInA1B1B2) - 0.5, 2) / expInA1B1B2
								+ Math.pow((obsaaBB - expInA2B1) - 0.5, 2) / expInA2B1
								+ Math.pow((obsaabb - expInA2B2) - 0.5, 2) / expInA2B2
								+ Math.pow((obsaaBb - expInA2B1B2) - 0.5, 2) / expInA2B1B2
								+ Math.pow((obsAaBB - expInA1A2B1) - 0.5, 2) / expInA1A2B1
								+ Math.pow((obsAabb - expInA1A2B2) - 0.5, 2) / expInA1A2B2
								+ Math.pow((obsAaBb - expInA1A2B1B2) - 0.5, 2) / expInA1A2B1B2;
					} else {
						chiInde = Math.pow((obsAABB - expInA1B1), 2) / expInA1B1
								+ Math.pow((obsAAbb - expInA1B2), 2) / expInA1B2
								+ Math.pow((obsAABb - expInA1B1B2), 2) / expInA1B1B2
								+ Math.pow((obsaaBB - expInA2B1), 2) / expInA2B1
								+ Math.pow((obsaabb - expInA2B2), 2) / expInA2B2
								+ Math.pow((obsaaBb - expInA2B1B2), 2) / expInA2B1B2
								+ Math.pow((obsAaBB - expInA1A2B1), 2) / expInA1A2B1
								+ Math.pow((obsAabb - expInA1A2B2), 2) / expInA1A2B2
								+ Math.pow((obsAaBb - expInA1A2B1B2), 2) / expInA1A2B1B2;
					}
					resultValues.put("chiInd", chiInde);
					agree.put("chiInd", (chiInde > 15.51 ? "No" : "Yes"));

					chiLink = chiInde - (chiA + chiB);
					agree.put("chiLink", (chiLink > 9.49 ? "No" : "Yes"));

					if (chiInde < 15.51) {
						result = "The loci A₁A₂ and B₁B₂ are independent";
					}
					expConA1B1 = (obsA1 * obsB1) / total;
					expConA1B2 = (obsA1 * obsB2) / total;
					expConA1B1B2 = (obsA1 * obsB1B2) / total;
					expConA2B1 = (obsA2 * obsB1) / total;
					expConA2B2 = (obsA2 * obsB2) / total;
					expConA2B1B2 = (obsA2 * obsB1B2) / total;
					expConA1A2B1 = (obsA1A2 * obsB1) / total;
					expConA1A2B2 = (obsA1A2 * obsB2) / total;
					expConA1A2B1B2 = (obsA1A2 * obsB1B2) / total;
					resultValues.put("expConA1B1", expConA1B1);
					resultValues.put("expConA1B1B2", expConA1B1B2);
					resultValues.put("expConA1B2", expConA1B2);
					resultValues.put("expConA2B1", expConA2B1);
					resultValues.put("expConA2B1B2", expConA2B1B2);
					resultValues.put("expConA2B2", expConA2B2);
					resultValues.put("expConA1A2B1", expConA1A2B1);
					resultValues.put("expConA1A2B2", expConA1A2B2);
					resultValues.put("expConA1A2B1B2", expConA1A2B1B2);
					if ((expConA1B1 <= 10 && expConA1B1 >= 5) || (expConA1B1B2 <= 10 && expConA1B1B2 >= 5)
							|| (expConA1B2 <= 10 && expConA1B2 >= 5) || (expConA2B1 <= 10 && expConA2B1 >= 5)
							|| (expConA2B1B2 <= 10 && expConA2B1B2 >= 5) || (expConA2B2 <= 10 && expConA2B2 >= 5)
							|| (expConA1A2B1 <= 10 && expConA1A2B1 >= 5) || (expConA1A2B2 <= 10 && expConA1A2B2 >= 5)
							|| (expConA1A2B1B2 <= 10 && expConA1A2B1B2 >= 5)) {
						chiCon = Math.pow((obsAABB - expConA1B1) - 0.5, 2) / expConA1B1
								+ Math.pow((obsAAbb - expConA1B2) - 0.5, 2) / expConA1B2
								+ Math.pow((obsAABb - expConA1B1B2) - 0.5, 2) / expConA1B1B2
								+ Math.pow((obsaaBB - expConA2B1) - 0.5, 2) / expConA2B1
								+ Math.pow((obsaabb - expConA2B2) - 0.5, 2) / expConA2B2
								+ Math.pow((obsaaBb - expConA2B1B2) - 0.5, 2) / expConA2B1B2
								+ Math.pow((obsAaBB - expConA1A2B1) - 0.5, 2) / expConA1A2B1
								+ Math.pow((obsAabb - expConA1A2B2) - 0.5, 2) / expConA1A2B2
								+ Math.pow((obsAaBb - expConA1A2B1B2) - 0.5, 2) / expConA1A2B1B2;
					} else {
						chiCon = Math.pow((obsAABB - expConA1B1), 2) / expConA1B1
								+ Math.pow((obsAAbb - expConA1B2), 2) / expConA1B2
								+ Math.pow((obsAABb - expConA1B1B2), 2) / expConA1B1B2
								+ Math.pow((obsaaBB - expConA2B1), 2) / expConA2B1
								+ Math.pow((obsaabb - expConA2B2), 2) / expConA2B2
								+ Math.pow((obsaaBb - expConA2B1B2), 2) / expConA2B1B2
								+ Math.pow((obsAaBB - expConA1A2B1), 2) / expConA1A2B1
								+ Math.pow((obsAabb - expConA1A2B2), 2) / expConA1A2B2
								+ Math.pow((obsAaBb - expConA1A2B1B2), 2) / expConA1A2B1B2;
					}

					resultValues.put("chiCon", chiCon);
					agree.put("chiCon", (chiCon > 9.49 ? "No" : "Yes"));

					if (chiCon < 9.49) {
						result = "The loci A₁A₂ and B₁B₂ are independent";
					} else {
						if (chiCon > 9.49 && obsAABB > obsAAbb && obsaabb > obsaaBB) {
							rf = ((2 * (obsAAbb + obsaaBB)) + ((obsAABB + obsaaBb + obsAaBB + obsAabb) / 2)) / total;
							phases.put("phaseA", "Coupling");
							phases.put("phaseB", "A₁B₁/A₂B₂");
						} else {
							rf = ((2 * (obsAABB + obsaabb)) + ((obsAABb + obsaaBb + obsAaBB + obsAabb) / 2)) / total;
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
				expConA1B1 = (obsA1 * obsB1) / total;
				expConA1B2 = (obsA1 * obsB2) / total;
				expConA1B1B2 = (obsA1 * obsB1B2) / total;
				expConA2B1 = (obsA2 * obsB1) / total;
				expConA2B2 = (obsA2 * obsB2) / total;
				expConA2B1B2 = (obsA2 * obsB1B2) / total;
				expConA1A2B1 = (obsA1A2 * obsB1) / total;
				expConA1A2B2 = (obsA1A2 * obsB2) / total;
				expConA1A2B1B2 = (obsA1A2 * obsB1B2) / total;
				resultValues.put("expConA1B1", expConA1B1);
				resultValues.put("expConA1B1B2", expConA1B1B2);
				resultValues.put("expConA1B2", expConA1B2);
				resultValues.put("expConA2B1", expConA2B1);
				resultValues.put("expConA2B1B2", expConA2B1B2);
				resultValues.put("expConA2B2", expConA2B2);
				resultValues.put("expConA1A2B1", expConA1A2B1);
				resultValues.put("expConA1A2B2", expConA1A2B2);
				resultValues.put("expConA1A2B1B2", expConA1A2B1B2);
				if ((expConA1B1 <= 10 && expConA1B1 >= 5) || (expConA1B1B2 <= 10 && expConA1B1B2 >= 5)
						|| (expConA1B2 <= 10 && expConA1B2 >= 5) || (expConA2B1 <= 10 && expConA2B1 >= 5)
						|| (expConA2B1B2 <= 10 && expConA2B1B2 >= 5) || (expConA2B2 <= 10 && expConA2B2 >= 5)
						|| (expConA1A2B1 <= 10 && expConA1A2B1 >= 5) || (expConA1A2B2 <= 10 && expConA1A2B2 >= 5)
						|| (expConA1A2B1B2 <= 10 && expConA1A2B1B2 >= 5)) {
					chiCon = Math.pow((obsAABB - expConA1B1) - 0.5, 2) / expConA1B1
							+ Math.pow((obsAAbb - expConA1B2) - 0.5, 2) / expConA1B2
							+ Math.pow((obsAABb - expConA1B1B2) - 0.5, 2) / expConA1B1B2
							+ Math.pow((obsaaBB - expConA2B1) - 0.5, 2) / expConA2B1
							+ Math.pow((obsaabb - expConA2B2) - 0.5, 2) / expConA2B2
							+ Math.pow((obsaaBb - expConA2B1B2) - 0.5, 2) / expConA2B1B2
							+ Math.pow((obsAaBB - expConA1A2B1) - 0.5, 2) / expConA1A2B1
							+ Math.pow((obsAabb - expConA1A2B2) - 0.5, 2) / expConA1A2B2
							+ Math.pow((obsAaBb - expConA1A2B1B2) - 0.5, 2) / expConA1A2B1B2;
				} else {
					chiCon = Math.pow((obsAABB - expConA1B1), 2) / expConA1B1
							+ Math.pow((obsAAbb - expConA1B2), 2) / expConA1B2
							+ Math.pow((obsAABb - expConA1B1B2), 2) / expConA1B1B2
							+ Math.pow((obsaaBB - expConA2B1), 2) / expConA2B1
							+ Math.pow((obsaabb - expConA2B2), 2) / expConA2B2
							+ Math.pow((obsaaBb - expConA2B1B2), 2) / expConA2B1B2
							+ Math.pow((obsAaBB - expConA1A2B1), 2) / expConA1A2B1
							+ Math.pow((obsAabb - expConA1A2B2), 2) / expConA1A2B2
							+ Math.pow((obsAaBb - expConA1A2B1B2), 2) / expConA1A2B1B2;
				}

				resultValues.put("chiCon", chiCon);
				agree.put("chiCon", (chiCon > 9.49 ? "No" : "Yes"));

				if (chiCon < 9.49) {
					result = "The loci A₁A₂ and B₁B₂ are independent";
				} else {
					if (chiCon > 9.49 && obsAABB > obsAAbb && obsaabb > obsaaBB) {
						rf = ((2 * (obsAAbb + obsaaBB)) + ((obsAABB + obsaaBb + obsAaBB + obsAabb) / 2)) / total;
						phases.put("phaseA", "Coupling");
						phases.put("phaseB", "A₁B₁/A₂B₂");
					} else {
						rf = ((2 * (obsAABB + obsaabb)) + ((obsAABb + obsaaBb + obsAaBB + obsAabb) / 2)) / total;
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
		Map<String, Double> observedValues = new HashMap<>();
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
			observedValues.put("obsA", (double) obsA);
			observedValues.put("obsa", (double) obsa);
			observedValues.put("obsB", (double) obsB);
			observedValues.put("obsb", (double) obsb);
			observedValues.put("obsC", (double) obsC);
			observedValues.put("obsc", (double) obsc);
			observedValues.put("total", (double) total);
			observedValues.put("obsAB", (double) obsAB);
			observedValues.put("obsAb", (double) obsAb);
			observedValues.put("obsaB", (double) obsaB);
			observedValues.put("obsab", (double) obsab);
			observedValues.put("obsAC", (double) obsAC);
			observedValues.put("obsAc", (double) obsAc);
			observedValues.put("obsaC", (double) obsaC);
			observedValues.put("obsac", (double) obsac);
			observedValues.put("obsBC", (double) obsBC);
			observedValues.put("obsBc", (double) obsBc);
			observedValues.put("obsbC", (double) obsbC);
			observedValues.put("obsbc", (double) obsbc);
			double expDo = total / 2;
			double expRe = total / 2;
			expectedValues.put("expDo", expDo);
			expectedValues.put("expRe", expRe);
			if (expDo >= 5 && expRe >= 5) {
				double chiA, chiB, chiC, chiInde, chiAB = 0, chiAC = 0, chiBC = 0, expInAB, expInAb, expInaB, expInab,
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
				expInAB = total / 4;
				expInAb = total / 4;
				expInaB = total / 4;
				expInab = total / 4;
				expInAC = total / 4;
				expInAc = total / 4;
				expInaC = total / 4;
				expInac = total / 4;
				expInBC = total / 4;
				expInBc = total / 4;
				expInbc = total / 4;
				expInbC = total / 4;
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
				expConAB = (obsA * obsB) / total;
				expConAb = (obsA * obsb) / total;
				expConaB = (obsa * obsB) / total;
				expConab = (obsa * obsb) / total;
				expConAC = (obsA * obsC) / total;
				expConAc = (obsA * obsc) / total;
				expConaC = (obsa * obsC) / total;
				expConac = (obsa * obsc) / total;
				expConBC = (obsB * obsC) / total;
				expConBc = (obsB * obsc) / total;
				expConbc = (obsb * obsc) / total;
				expConbC = (obsb * obsC) / total;
				expectedValues.put("expConAB", expConAB);
				expectedValues.put("expConAb", expConAb);
				expectedValues.put("expConaB", expConaB);
				expectedValues.put("expConab", expConab);
				expectedValues.put("expConAC", expConAC);
				expectedValues.put("expConAc", expConAc);
				expectedValues.put("expConaC", expConaC);
				expectedValues.put("expConac", expConac);
				expectedValues.put("expConBC", expConBC);
				expectedValues.put("expConBc", expConBc);
				expectedValues.put("expConbc", expConbc);
				expectedValues.put("expConbC", expConbC);
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
					rfAB = (obsAb + obsaB) / total;
					distAB = rfAB * 100;
					lodzAB = Math.log((((Math.pow(1 - rfAB, (obsAB + obsab))) * (Math.pow(rfAB, (obsAb + obsaB)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiAB >= 3.841 && obsAB < obsAb) {
					rfAB = (obsAB + obsab) / total;
					distAB = rfAB * 100;
					lodzAB = Math.log((((Math.pow(1 - rfAB, (obsAb + obsaB))) * (Math.pow(rfAB, (obsAB + obsab)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiAC >= 3.841 && obsAC > obsAc) {
					rfAC = (obsAc + obsaC) / total;
					distAC = rfAC * 100;
					lodzAC = Math.log((((Math.pow(1 - rfAC, (obsAC + obsac))) * (Math.pow(rfAC, (obsAc + obsaC)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiAC >= 3.841 && obsAC < obsAc) {
					rfAC = (obsAC + obsac) / total;
					distAC = rfAC * 100;
					lodzAC = Math.log((((Math.pow(1 - rfAC, (obsAc + obsaC))) * (Math.pow(rfAC, (obsAC + obsac)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiBC >= 3.841 && obsBC > obsBc) {
					rfBC = (obsBc + obsbC) / total;
					distBC = rfBC * 100;
					lodzBC = Math.log((((Math.pow(1 - rfBC, (obsBC + obsbc))) * (Math.pow(rfBC, (obsBc + obsbC)))
							/ Math.pow(0.5, total))) / Math.log(10));
				}
				if (chiBC >= 3.841 && obsBC < obsBc) {
					rfBC = (obsAb + obsaB) / total;
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
						coincidence = ((obsABc + obsabC) / total) / (rfAC * rfBC);
						resultValues.put("coincidence", coincidence);
					} else {
						if (iMax == 2 || iMax == 3) {
							phases.put("phaseA", "Acb");
							phases.put("phaseB", "aCB");
							coincidence = ((obsAbC + obsaBc) / total) / (rfAC * rfBC);
							resultValues.put("coincidence", coincidence);
						} else {
							if (iMax == 4 || iMax == 5) {
								phases.put("phaseA", "AcB");
								phases.put("phaseB", "aCb");
								coincidence = ((obsABC + obsabc) / total) / (rfAC * rfBC);
								resultValues.put("coincidence", coincidence);
							} else {
								if (iMax == 6 || iMax == 7) {
									phases.put("phaseA", "ACb");
									phases.put("phaseB", "acB");
									coincidence = ((obsaBC + obsABc) / total) / (rfAC * rfBC);
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
							coincidence = ((obsAbC + obsaBc) / total) / (rfAB * rfBC);
							resultValues.put("coincidence", coincidence);
						} else {
							if (iMax == 2 || iMax == 3) {
								phases.put("phaseA", "Abc");
								phases.put("phaseB", "aBC");
								coincidence = ((obsABc + obsabC) / total) / (rfAB * rfBC);
								resultValues.put("coincidence", coincidence);
							} else {
								if (iMax == 4 || iMax == 5) {
									phases.put("phaseA", "ABc");
									phases.put("phaseB", "abC");
									coincidence = ((obsAbc + obsaBC) / total) / (rfAB * rfBC);
									resultValues.put("coincidence", coincidence);
								} else {
									if (iMax == 6 || iMax == 7) {
										phases.put("phaseA", "AbC");
										phases.put("phaseB", "aBc");
										coincidence = ((obsabc + obsABC) / total) / (rfAB * rfBC);
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
								coincidence = ((obsaBC + obsAbc) / total) / (rfAB * rfAC);
								resultValues.put("coincidence", coincidence);
							} else {
								if (iMax == 2 || iMax == 3) {
									phases.put("phaseA", "bAc");
									phases.put("phaseB", "BaC");
									coincidence = ((obsABC + obsabc) / total) / (rfAB * rfAC);
									resultValues.put("coincidence", coincidence);
								} else {
									if (iMax == 4 || iMax == 5) {
										phases.put("phaseA", "BAc");
										phases.put("phaseB", "baC");
										coincidence = ((obsAbC + obsaBc) / total) / (rfAB * rfAC);
										resultValues.put("coincidence", coincidence);
									} else {
										if (iMax == 6 || iMax == 7) {
											phases.put("phaseA", "bAC");
											phases.put("phaseB", "Bac");
											coincidence = ((obsABc + obsabC) / total) / (rfAB * rfAC);
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

	public CTResult testcrossDM(double r1, double r2, double cOc, double tOs) {
		Map<String, String> phases = new HashMap<>();
		Map<String, Double> resultValues = new HashMap<>();
		////////////////////////
		double ABCn, abcn, ABcn, abCn, AbCn, aBcn, aBCn, Abcn, ABC2, abc2, ABc2, abC2, AbC2, aBc2, aBC2, Abc2, ABC3,
				abc3, ABc3, abC3, AbC3, aBc3, aBC3, Abc3, ABC4, abc4, ABc4, abC4, AbC4, aBc4, aBC4, Abc4, interference,
				rf;
		///////////////////////
		if (r1 < 0.5 && r2 < 0.5 && tOs < 5000) {
			AbCn = tOs * ((r1 * r2 * cOc) / 2);
			aBcn = tOs * ((r1 * r2 * cOc) / 2);
			Abcn = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			aBCn = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			ABcn = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			abCn = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			abcn = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);
			ABCn = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);

			resultValues.put("AbCn", AbCn);
			resultValues.put("aBcn", aBcn);
			resultValues.put("Abcn", Abcn);
			resultValues.put("aBCn", aBCn);
			resultValues.put("ABcn", ABcn);
			resultValues.put("abCn", abCn);
			resultValues.put("abcn", abcn);
			resultValues.put("ABCn", ABCn);

			interference = 1 - cOc;
			rf = r1 + r2 - (r1 * r2 * cOc * 2);

			resultValues.put("interference", interference);
			resultValues.put("rf", rf);

			ABC2 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);//
			aBC2 = tOs * ((r1 * r2 * cOc) / 2);//
			AbC2 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);//
			abc2 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);//
			ABc2 = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);//
			aBc2 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);//
			Abc2 = tOs * ((r1 * r2 * cOc) / 2);//
			abC2 = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);//
			resultValues.put("AbC2", AbC2);
			resultValues.put("aBc2", aBc2);
			resultValues.put("Abc2", Abc2);
			resultValues.put("aBC2", aBC2);
			resultValues.put("ABc2", ABc2);
			resultValues.put("abC2", abC2);
			resultValues.put("abc2", abc2);
			resultValues.put("ABC2", ABC2);

			ABC3 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);//
			aBC3 = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);//
			AbC3 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);//
			abc3 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);//
			ABc3 = tOs * ((r1 * r2 * cOc) / 2);//
			aBc3 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);//
			Abc3 = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);//
			abC3 = tOs * ((r1 * r2 * cOc) / 2);//
			resultValues.put("AbC3", AbC3);
			resultValues.put("aBc3", aBc3);
			resultValues.put("Abc3", Abc3);
			resultValues.put("aBC3", aBC3);
			resultValues.put("ABc3", ABc3);
			resultValues.put("abC3", abC3);
			resultValues.put("abc3", abc3);
			resultValues.put("ABC3", ABC3);

			ABC4 = tOs * ((r1 * r2 * cOc) / 2);//
			aBC4 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);//
			AbC4 = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);//
			abc4 = tOs * ((r1 * r2 * cOc) / 2);//
			ABc4 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);//
			aBc4 = tOs * ((1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc))) / 2);//
			Abc4 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);//
			abC4 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);//
			resultValues.put("AbC4", AbC4);
			resultValues.put("aBc4", aBc4);
			resultValues.put("Abc4", Abc4);
			resultValues.put("aBC4", aBC4);
			resultValues.put("ABc4", ABc4);
			resultValues.put("abC4", abC4);
			resultValues.put("abc4", abc4);
			resultValues.put("ABC4", ABC4);

			if (rf >= 0.5) {
				phases.put("rf", "r is >=0.5");
			} else {
				phases.put("rf", String.valueOf(rf));
			}
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage(" r can´t be higher than 0,5. The max number of total offspring is 5000").build();
		}

		return CTResult.builder()
				.cleanInputs(false)
				.resultValues(resultValues)
				.phase(phases)
				.build();
	}

	public CTResult dominanceDM(double r1, double tOs) {
		Map<String, Double> resultValues = new HashMap<>();
		////////////////////////
		double repAB = 0, repAb = 0, repaB = 0, repab = 0, coupAB = 0, coupAb = 0, coupaB = 0, coupab = 0;
		///////////////////////
		if (r1 < 0.5 && tOs < 5000) {
			coupAB = tOs * ((2 + Math.pow((1 - r1), 2)) / 4);
			coupAb = tOs * ((1 - Math.pow((1 - r1), 2)) / 4);
			coupaB = tOs * ((1 - Math.pow((1 - r1), 2)) / 4);
			coupab = tOs * (Math.pow((1 - r1), 2) / 4);

			repAB = tOs * ((2 + Math.pow(r1, 2)) / 4);
			repAb = tOs * ((1 - Math.pow(r1, 2)) / 4);
			repaB = tOs * ((1 - Math.pow(r1, 2)) / 4);
			repab = tOs * (Math.pow(r1, 2) / 4);
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("r can´t be higher than 0,5. The max number accepted for total offspring is 5000").build();
		}

		resultValues.put("coupAB", coupAB);
		resultValues.put("coupAb", coupAb);
		resultValues.put("coupaB", coupaB);
		resultValues.put("coupab", coupab);
		resultValues.put("repAB", repAB);
		resultValues.put("repAb", repAb);
		resultValues.put("repaB", repaB);
		resultValues.put("repab", repab);

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
			coupA1A1B1B1 = tOs * ((Math.pow((1 - r1), 2)) / 4);
			coupA1A2B1B1 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			coupA2A2B1B1 = tOs * ((Math.pow((r1), 2)) / 4);
			coupA1A1B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			coupA1A2B1B2 = ((tOs * (Math.pow((1 - r1), 2))) / 2) + ((tOs * (Math.pow((r1), 2))) / 2);
			coupA2A2B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			coupA1A1B2B2 = tOs * ((Math.pow((r1), 2)) / 4);
			coupA1A2B2B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			coupA2A2B2B2 = tOs * ((Math.pow((1 - r1), 2)) / 4);

			repA1A1B1B1 = tOs * ((Math.pow((r1), 2)) / 4);
			repA1A2B1B1 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			repA2A2B1B1 = tOs * ((Math.pow((1 - r1), 2)) / 4);
			repA1A1B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			repA1A2B1B2 = ((tOs * (Math.pow((1 - r1), 2))) / 2) + ((tOs * (Math.pow((r1), 2))) / 2);
			repA2A2B1B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			repA1A1B2B2 = tOs * ((Math.pow((1 - r1), 2)) / 4);
			repA1A2B2B2 = tOs * ((r1 - Math.pow((r1), 2)) / 2);
			repA2A2B2B2 = tOs * ((Math.pow((r1), 2)) / 4);
		} else {
			return CTResult.builder().cleanInputs(true).feedbackMessage("r can´t be higher than 0,5. The max number accepted for total offspring is 5000").build();
		}

		resultValues.put("coupA1A1B1B1", coupA1A1B1B1);
		resultValues.put("coupA1A2B1B1", coupA1A2B1B1);
		resultValues.put("coupA2A2B1B1", coupA2A2B1B1);
		resultValues.put("coupA1A1B1B2", coupA1A1B1B2);
		resultValues.put("coupA1A2B1B2", coupA1A2B1B2);
		resultValues.put("coupA2A2B1B2", coupA2A2B1B2);
		resultValues.put("coupA1A1B2B2", coupA1A1B2B2);
		resultValues.put("coupA1A2B2B2", coupA1A2B2B2);
		resultValues.put("coupA2A2B2B2", coupA2A2B2B2);

		resultValues.put("repA1A1B1B1", repA1A1B1B1);
		resultValues.put("repA1A2B1B1", repA1A2B1B1);
		resultValues.put("repA2A2B1B1", repA2A2B1B1);
		resultValues.put("repA1A1B1B2", repA1A1B1B2);
		resultValues.put("repA1A2B1B2", repA1A2B1B2);
		resultValues.put("repA2A2B1B2", repA2A2B1B2);
		resultValues.put("repA1A1B2B2", repA1A1B2B2);
		resultValues.put("repA1A2B2B2", repA1A2B2B2);
		resultValues.put("repA2A2B2B2", repA2A2B2B2);

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
			vAbCn = tOs * ((r1 * r2 * cOc) / 2);
			vaBcn = tOs * ((r1 * r2 * cOc) / 2);
			vAbcn = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			vaBCn = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			vABcn = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vabCn = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vabcn = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);
			vABCn = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);

			interference = 1 - cOc;
			rf = r1 + r2 - (r1 * r2 * cOc * 2);

			vABC2 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vaBC2 = tOs * ((r1 * r2 * cOc) / 2);
			vAbC2 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			vabc2 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vABc2 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);
			vaBc2 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			vAbc2 = tOs * ((r1 * r2 * cOc) / 2);
			vabC2 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);

			vABC3 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			vaBC3 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);
			vAbC3 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vabc3 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			vABc3 = tOs * ((r1 * r2 * cOc) / 2);
			vaBc3 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vAbc3 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);
			vabC3 = tOs * ((r1 * r2 * cOc) / 2);

			vABC4 = tOs * ((r1 * r2 * cOc) / 2);
			vaBC4 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vAbC4 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);
			vabc4 = tOs * ((r1 * r2 * cOc) / 2);
			vABc4 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
			vaBc4 = tOs * (1 - ((r2 - (r1 * r2 * cOc)) + (r1 - (r1 * r2 * cOc)) + (r1 * r2 * cOc)) / 2);
			vAbc4 = tOs * ((r2 - (r1 * r2 * cOc)) / 2);
			vabC4 = tOs * ((r1 - (r1 * r2 * cOc)) / 2);
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
