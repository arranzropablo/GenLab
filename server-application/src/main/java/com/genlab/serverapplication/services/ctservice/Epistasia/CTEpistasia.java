package com.genlab.serverapplication.services.ctservice.Epistasia;

import com.genlab.serverapplication.models.CTResult;

public interface CTEpistasia {

    CTResult whithoutModif(int obsAB, int obsaB, int obsAb, int obsab);

    CTResult singleRecessive(int obsAB, int obsAb, int obsaBab);

    CTResult singleDominant(int obsaB, int obsab, int obsABAb);

    CTResult singleAdditive(int obsAB, int obsab, int obsAbaB);

    CTResult doubleRecessive(int obsAB, int obsAaaBab);

    CTResult doubleDominant(int obsab, int obsABAbaB);

    CTResult doubleDominantRecessive(int obsaB, int obsABAbab);

    CTResult segregation6334(int obs6,int obs3a,int obs3b, int obs4);

    CTResult segregation1033(int obs10,int obs3a,int obs3b);

}
