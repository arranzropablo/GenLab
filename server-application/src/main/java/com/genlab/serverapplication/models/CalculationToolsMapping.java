package com.genlab.serverapplication.models;

import lombok.Getter;

@Getter
public enum CalculationToolsMapping {
	
	TEST_CROSSDOMINANCE("Testcross Dominance", 0, 0),
	F2_DOMINANCE_TWOLOCI("F2 Dominance", 0, 1),
	F2_CODOMINANCE("F2 Dominance", 0, 2),
	F2_CODOM2("F2Codom(2)-Dom", 0, 3),
	F2_CODOM4("F2Codom(4)-Dom", 0, 4),
	F2_TESTCROSS("F2-TestcrossDom", 0, 5),
	F2_TESTCROSS2("F2-Testcross2-Dom", 0, 6),
	F2_TESTCROSS4("F2-Testcross4-Dom", 0, 7),
	
	TESTCROSS("Testcross", 1, 0),
	F2_DOMINANCE_ONELOCUS("F2 Dominance", 1, 1),
	F2_CODOMINANCE_ONELOCUS("F2 Codominance", 1, 2),
	CODOMINANCE_3_ALLELES("Codominance 3 Alleles", 1, 3),
	CODOMINANCE_4_ALLELES("Codominance 4 Alleles", 1, 4),
	LETHAL_GENES("Lethal Genes", 1, 5),
	
	TWO_LOCI_TESTCROSS("Two loci / Testcross", 2, 0),
	TWO_LOCI_F2_DOMINANCE("Two loci / F2 Dominance", 2, 1),
	TWO_LOCI_F2_CODOMINANCE("Two loci / F2 Codominance", 2, 2),
	THREE_LOCI_TESTCROSS("Three loci / Testcross", 2, 3),
	DIRECT_METHOD_TESTCROSS("Direct method / Testcross", 2, 4),
	DIRECT_METHOD_F2_DOMINANCE("Direct method / F2 Dominance", 2, 5),
	DIRECT_METHOD_F2_CODOMINANCE("Direct method / F2 Codominance", 2, 6),
	DIRECT_METHOD_TESTCROSS_3_LOCI("Direct method / Testcross 3 Loci", 2, 7),
	
	WITHOUT_MODIFICATION("Without modification", 3, 0),
	SINGLE_RECESSIVE("Single recessive", 3, 1),
	SINGLE_DOMINANT("Single dominant", 3, 2),
	SEGREGATION_961("Segregation 9:6:1", 3, 3),
	DOUBLE_RECESSIVE("Double Recessive", 3, 4),
	DOUBLE_DOMINANT("Double Dominant", 3, 5),
	DOUBLE_DOMINANT_RECESSIVE("Double Dominant-Recessive", 3, 6),
	SEGREGATION_6334("Segregation 6:3:3:4", 3, 7),
	SEGREGATION_1033("Segregation 10:3:3", 3, 8),
	
	POLYHYBRID("Polyhybrid", 4, 0),
	MULTIPLE_ALLELES("Multiple Alleles", 4, 1)
	;
	
	private final String value;
	private final int sectionId;
	private final int id;
	
	private CalculationToolsMapping(String v, int sectionId, int id) {
		this.value = v;
		this.sectionId = sectionId;
		this.id = id;
	}
}
