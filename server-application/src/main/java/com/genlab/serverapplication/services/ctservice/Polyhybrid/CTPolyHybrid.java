package com.genlab.serverapplication.services.ctservice.Polyhybrid;

import com.genlab.serverapplication.models.CTResult;

public interface CTPolyHybrid {

    CTResult polyhybrid (int n, int h, int d, int r, int D, int R, int T);

    CTResult multiplealleles (int locus1, int locus2, int locus3, int locus4, int locus5);

}
