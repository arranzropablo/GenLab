package com.genlab.serverapplication.services.ctservice.Polyhybrid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.CTResult;

@Service
public class CTPolyhybridimp implements CTPolyHybrid {

	public CTResult polyhybrid(int n, int h, int d, int r, int D, int R, int T) {
		Map<String, Double> resultValues = new HashMap<>();
		if(n > 20 || h > 20 || d > 20 || r > 20 || D > 20 || R > 20) {
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum number of loci allowed is 20").build();
		} else {
			if (n != d + h + r || n != D + R) {
				return CTResult.builder().cleanInputs(true).feedbackMessage("The sum of d+h+r or the sum of D+R is different to n").build();
			} else {
				double factn = 1;
				for (int i = 2; i <= n; i++) {
					factn *= i;
				}
				double facth = 1;
				for (int i = 2; i <= h; i++) {
					facth *= i;
				}
				double factd = 1;
				for (int i = 2; i <= d; i++) {
					factd *= i;
				}
				double factr = 1;
				for (int i = 2; i <= r; i++) {
					factr *= i;
				}
				double factD = 1;
				for (int i = 2; i <= D; i++) {
					factD *= i;
				}
				double factR = 1;
				for (int i = 2; i <= R; i++) {
					factR *= i;
				}
				double gametes = Math.pow(2, n);
				resultValues.put("gametes", gametes);

				double genotypes = Math.pow(3, n);
				resultValues.put("genotypes", genotypes);

				double homogenotypes = Math.pow(2, n);
				resultValues.put("homogenotypes", homogenotypes);

				double heterogenotypes = genotypes - homogenotypes;
				resultValues.put("heterogenotypes", heterogenotypes);

				double factGenotypes = factn / (factd * facth * factr);
				resultValues.put("factGenotypes", factGenotypes);

				double frGenotype = Math.pow(1 / 4.0, d) * Math.pow(1 / 4.0, h) * Math.pow(1 / 2.0, r);
				resultValues.put("frGenotype", frGenotype);

				double frecGenotype = factGenotypes * frGenotype;
				resultValues.put("frecGenotype", frecGenotype);

				double totalGenotype = frecGenotype * T;
				resultValues.put("totalGenotype", totalGenotype);

				double factPhenotype = factn / (factD * factR);
				resultValues.put("factPhenotype", factPhenotype);

				double frPhenotype = Math.pow(3 / 4.0, D) * Math.pow(1 / 4.0, R);
				resultValues.put("frPhenotype", frPhenotype);

				double frecPhenotype = factPhenotype * frPhenotype;
				resultValues.put("frecPhenotype", frecPhenotype);

				double totalPhenotype = frecPhenotype * T;
				resultValues.put("totalPhenotype", totalPhenotype);

			}
		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).build();
	}
	
	public CTResult multiplealleles	(int locus1, int locus2, int locus3, int locus4, int locus5) {
		Map<String, Double> resultValues = new HashMap<>();
		if(locus1 > 50 || locus2 > 50 || locus3 > 50 || locus4 > 50 || locus5 > 50) {
			return CTResult.builder().cleanInputs(true).feedbackMessage("The maximum number of alleles allowed in a locus is 50").build();
		} else {
			double HM1 = locus1;
			resultValues.put("HM1", HM1);

			double HM2 = locus2;
			resultValues.put("HM2", HM2);

			double HM3 = locus3;
			resultValues.put("HM3", HM3);

			double HM4 = locus4;
			resultValues.put("HM4", HM4);

			double HM5 = locus5;
			resultValues.put("HM5", HM5);

			double NG1 = locus1 * (locus1 + 1) / 2;
			resultValues.put("NG1", NG1);

			double NG2 = locus2 * (locus2 + 1) / 2;
			resultValues.put("NG2", NG2);

			double NG3 = locus3 * (locus3 + 1) / 2;
			resultValues.put("NG3", NG3);

			double NG4 = locus4 * (locus4 + 1) / 2;
			resultValues.put("NG4", NG4);

			double NG5 = locus5 * (locus5 + 1) / 2;
			resultValues.put("NG5", NG5);

			double HT1 = locus1 * (locus1 - 1) / 2;
			resultValues.put("HT1", HT1);

			double HT2 = locus2 * (locus2 - 1) / 2;
			resultValues.put("HT2", HT2);

			double HT3 = locus3 * (locus3 - 1) / 2;
			resultValues.put("HT3", HT3);

			double HT4 = locus4 * (locus4 - 1) / 2;
			resultValues.put("HT4", HT4);

			double HT5 = locus5 * (locus5 - 1) / 2;
			resultValues.put("HT5", HT5);

			double totalgenotypes = NG1 * NG2 * NG3 * NG4 * NG5;
			resultValues.put("totalgenotypes", totalgenotypes);

		}
		return CTResult.builder().resultValues(resultValues).cleanInputs(false).build();
	}
	
}
