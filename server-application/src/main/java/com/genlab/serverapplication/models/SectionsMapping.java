package com.genlab.serverapplication.models;

import lombok.Getter;

@Getter
public enum SectionsMapping {
	
	TWOLOCI("Two Independent Loci", 0),
	ONELOCUS("One Locus", 1),
	LINKAGE("Linkage", 2),
	EPISTASIA("Epistasia", 3),
	POLYHYBRID("Polyhybrid", 4);
	
	private final String text;
	private final int id;

	private SectionsMapping(String t, int id) {
		this.text = t;
		this.id = id;
	}
}
