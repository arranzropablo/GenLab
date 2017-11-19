package com.genlab.serverapplication.models;

import lombok.Getter;

@Getter
public enum SectionsMapping {
	
	TWO_LOCI("Two Independent Loci", 0),
	ONE_LOCUS("One Locus", 1),
	LINKAGE("Linkage", 2),
	EPISTASIA("Epistasia", 3),
	POLYHYBRID("Polyhybrid", 4);
	
	private final String value;
	private final int id;

	private SectionsMapping(String v, int id) {
		this.value = v;
		this.id = id;
	}
}
