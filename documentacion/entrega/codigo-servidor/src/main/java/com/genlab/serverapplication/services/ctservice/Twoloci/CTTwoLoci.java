package com.genlab.serverapplication.services.ctservice.Twoloci;

import com.genlab.serverapplication.models.CTResult;

public interface CTTwoLoci {

    CTResult testcross(int obsAB, int obsAb, int obsaB, int obsab);

    CTResult f2Dominance(int obsAB, int obsAb, int obsaB, int obsab);

    CTResult f2coDominance(int obsA1A1B1B1, int obsA1A1B1B2, int obsA1A1B2B2, int obsA1A2B1B1, int obsA1A2B1B2, int obsA1A2B2B2, int obsA2A2B1B1, int obsA2A2B1B2, int obsA2A2B2B2);

    CTResult f2coDom2dom(int obsA1A1B, int obsA1A2B, int obsA2A2B, int obsA1A1b, int obsA1A2b, int obsA2A2b);

    CTResult f2coDom4dom(int obsA1A3B, int obsA1A3b, int obsA1A4B, int obsA1A4b, int obsA2A3B, int obsA2A3b, int obsA2A4B, int obsA2A4b);

    CTResult f2TestcrossDom(int obsAB, int obsAb, int obsaB, int obsab);

    CTResult f2Testcross2Dom(int obsA1A1B, int obsA1A2B, int obsA2A2B, int obsA1A1b, int obsA1A2b, int obsA2A2b);

    CTResult f2Testcross4Dom(int obsA1A3B, int obsA1A3b, int obsA1A4B, int obsA1A4b, int obsA2A3B, int obsA2A3b, int obsA2A4B, int obsA2A4b);

}
