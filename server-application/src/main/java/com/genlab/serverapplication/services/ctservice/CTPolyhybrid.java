package com.genlab.serverapplication.services.ctservice;

import com.genlab.serverapplication.models.CTResult;

public class CTPolyhybrid {

	public CTResult polyhybrid(int n, int h, int d, int r, int D, int R, int T) {
		//Cosas que haríamos en el controlador:
		//Validar que sea <20 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
		//Validar que sea > 5
		//Sumar para poner total en el propio controlador
		//Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
		if (n != d + h + r || n != D + R){
			//warning y clean
		} else {
			int factn = 1;
			for (int i = 2; i <= n; i++){
				factn *= i;
			}
			int facth = 1;
			for (int i = 2; i <= h; i++){
				facth *= i;
			}
			int factd = 1;
			for (int i = 2; i <= d; i++){
				factd *= i;
			}
			int factr = 1;
			for (int i = 2; i <= r; i++){
				factr *= i;
			}
			int factD = 1;
			for (int i = 2; i <= D; i++){
				factD *= i;
			}
			int factR = 1;
			for (int i = 2; i <= R; i++){
				factR *= i;
			}
			double gametes = Math.pow(2, n);
			double genotypes = Math.pow(3, n);
			double homogenotypes = Math.pow(2, n);
			double heterogenotypes = genotypes - homogenotypes;
			double factGenotypes = factn / (factd * facth * factr);
			double frGenotype = Math.pow(1/4, d) * Math.pow(1/4, h) * Math.pow(1/2, r);
			double frecGenotype = factGenotypes * frGenotype;
			double totalGenotype = frecGenotype * T;
			double factPhenotype = factn / (factD * factR);
			double frPhenotype = Math.pow(3/4, D) * Math.pow(1/4, R);
			double frecPhenotype = factPhenotype * frPhenotype;
			double totalPhenotype = frecPhenotype * T;
		}
		return null;
	}
	
	public CTResult multiplealleles	(int locus1, int locus2, int locus3, int locus4, int locus5) {
		//Cosas que haríamos en el controlador:
		//Validar que sea <50 en el controlador con lo de @Valid y/o BindingResult (creo qe es lo segundo por ser primitivo) https://stackoverflow.com/questions/24767164/spring-mvc-with-hibernate-validator-to-validate-single-basic-type
		//Validar que sea > 5
		//Sumar para poner total en el propio controlador
		//Con lo que se devuelva de aquí poner "The locus is/isn't segregating correctly" y en Agree un YES o NO
		int HM1 = locus1;
		int HM2 = locus2;
		int HM3 = locus3;
		int HM4 = locus4;
		int HM5 = locus5;

		int NG1 = locus1 * (locus1 + 1) / 2;
		int NG2 = locus2 * (locus2 + 1) / 2;
		int NG3 = locus3 * (locus3 + 1) / 2;
		int NG4 = locus4 * (locus4 + 1) / 2;
		int NG5 = locus5 * (locus5 + 1) / 2;

		int HT1 = locus1 * (locus1 - 1) / 2;
		int HT2 = locus2 * (locus2 - 1) / 2;
		int HT3 = locus3 * (locus3 - 1) / 2;
		int HT4 = locus4 * (locus4 - 1) / 2;
		int HT5 = locus5 * (locus5 - 1) / 2;

		int totalgenotypes = NG1 * NG2 * NG3 * NG4 * NG5;

		return null;
	}
	
}
