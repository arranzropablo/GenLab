package com.genlab.serverapplication.services.ctservice.Onelocus;

import com.genlab.serverapplication.models.CTResult;

public interface CTOneLocus {

    CTResult testcross(int obsA, int obsa);

    CTResult f2Dominance(int obsA, int obsa);

    CTResult f2CoDominance(int obsAA, int obsAa, int obsaa);

    CTResult coDominance3Alleles(int obsA1A1, int obsA1A2, int obsA1A3, int obsA2A3);

    CTResult coDominance4Alleles(int obsA1A3, int obsA1A4, int obsA2A3, int obsA2A4);

    CTResult lethalGenes(int obsAA, int obsAa);
}
