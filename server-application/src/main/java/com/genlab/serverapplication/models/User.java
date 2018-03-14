package com.genlab.serverapplication.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable{
	@Id
	private String email;
	@Column
	private String password;
	@Column(name="role")
	private String roles;
	@Column(name="feedback")
	private String feedback;

	private static final long serialVersionUID = -2021877520386437191L;
	
	
	
}
