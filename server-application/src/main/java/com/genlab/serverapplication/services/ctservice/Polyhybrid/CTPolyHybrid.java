package com.genlab.serverapplication.services.ctservice.Polyhybrid;

import com.genlab.serverapplication.models.CTResult;

public interface CTPolyHybrid {

    CTResult polyhybrid (double n, double h, double d, double r, double D, double R, double T);

    CTResult multiplealleles (double locus1, double locus2, double locus3, double locus4, double locus5);

}
