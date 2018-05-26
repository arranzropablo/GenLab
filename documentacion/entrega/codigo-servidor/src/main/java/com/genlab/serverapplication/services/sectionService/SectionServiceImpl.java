package com.genlab.serverapplication.services.sectionService;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.Sections;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.repositories.SectionsRepository;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionsRepository sectionsRepository;
	
	@Override
	public List<Sections> getSectionsSortedByPriority(){
		List<Sections> sectionlist = ((List<Sections>) sectionsRepository.findAll());
		sectionlist.stream().forEach(section -> section.setText(SectionsMapping.valueOf(section.getNombre().toUpperCase()).getText()));
		Collections.sort(sectionlist, (section1, section2) -> {
			return section1.getPriority() < section2.getPriority() ? -1 : 1;
		});
		return sectionlist;
	}
	
	public void updatePriority(List<Integer> sectionidlist){
		int priority = 0;
		for (int i = 0; i < sectionidlist.size(); i++) {
			Sections tosave = sectionsRepository.findOne(sectionidlist.get(i));
			tosave.setPriority(priority++);
			sectionsRepository.save(tosave);
		}
	}
}
