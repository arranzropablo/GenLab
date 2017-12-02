package com.genlab.serverapplication.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Theory {
	@Id
	private long id;
	private String title;
	private String content;
}
