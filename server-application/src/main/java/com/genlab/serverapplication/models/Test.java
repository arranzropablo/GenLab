package com.genlab.serverapplication.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Wither
@Table(name="tests")
public class Test implements Serializable{

	private static final long serialVersionUID = -2999200171545460302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int sectionid;

	@Column(name="titulo")
	private String titulo;

	@OneToMany(mappedBy="test", fetch=FetchType.LAZY)
	//	@OneToMany(targetEntity=TestQuestion.class, mappedBy="test", cascade=CascadeType.ALL)
	private List<TestQuestion> questions;

}
