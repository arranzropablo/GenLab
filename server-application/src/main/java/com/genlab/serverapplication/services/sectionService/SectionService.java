package com.genlab.serverapplication.services.sectionService;

import java.util.List;

import com.genlab.serverapplication.models.Sections;

public interface SectionService {
	
	public List<Sections> getSectionsSortedByPriority();

	public void updatePriority(List<Integer> sectionidlist);
	
}
