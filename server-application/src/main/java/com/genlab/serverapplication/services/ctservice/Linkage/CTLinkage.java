package com.genlab.serverapplication.services.ctservice.Linkage;

import com.genlab.serverapplication.models.CTResult;

public interface CTLinkage {

    CTResult testcross2Loci(int obsAB, int obsAb, int obsaB, int obsab);

    CTResult f22LociDominance(int obsAB, int obsAb, int obsaB, int obsab);

    CTResult f22LociCodominance(int obsAABB, int obsAABb, int obsAAbb, int obsAaBB, int obsAaBb, int obsAabb,
                                int obsaaBB, int obsaaBb, int obsaabb);

    CTResult testcross3Loci(int obsABC, int obsabc, int obsABc, int obsabC, int obsaBC, int obsAbc, int obsAbC,
                            int obsaBc);

    CTResult testcrossDM(double r1, double r2, double cOc, double tOs);

    CTResult dominanceDM(double r1, double tOs);

    CTResult codominanceDM(double r1, double tOs);

    CTResult tresLociDM(double r1, double r2, double cOc, double tOs);

}