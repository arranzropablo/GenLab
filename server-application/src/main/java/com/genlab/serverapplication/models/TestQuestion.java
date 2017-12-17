package com.genlab.serverapplication.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="questions")
public class TestQuestion implements Serializable{
	
	private static final long serialVersionUID = 403919529582470981L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="texto")
	@NonNull
	private String texto;
	
	@OneToMany(targetEntity=TestAnswer.class, mappedBy="pregunta", cascade=CascadeType.ALL)
	private List<TestAnswer> answers;	
	
	@ManyToOne(targetEntity=Test.class)
	@JoinColumn(name="test_id", nullable=false)
	private Test test;
	
}
