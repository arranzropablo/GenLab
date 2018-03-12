package com.genlab.serverapplication.services.ctservice.Linkage;

import com.genlab.serverapplication.models.CTResult;

public interface CTLinkage {

    CTResult testcross2Loci(int obsAB, int obsAb, int obsaB, int obsab);

    CTResult f22LociDominance(int obsAB, int obsAb, int obsaB, int obsab);

    CTResult f22LociCodominance(int obsA1A1B1B1, int obsA1A1B1B2, int obsA1A1B2B2, int obsA1A2B1B1, int obsA1A2B1B2, int obsA1A2B2B2,
								int obsA2A2B1B1, int obsA2A2B1B2, int obsA2A2B2B2);

    CTResult testcross3Loci(int obsABC, int obsabc, int obsABc, int obsabC, int obsaBC, int obsAbc, int obsAbC,
                            int obsaBc);

    CTResult testcrossDM(double r1, double tOs);

    CTResult dominanceDM(double r1, double tOs);

    CTResult codominanceDM(double r1, double tOs);

    CTResult tresLociDM(double r1, double r2, double cOc, double tOs);

}