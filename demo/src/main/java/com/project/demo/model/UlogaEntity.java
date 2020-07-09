package com.project.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UlogaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUloga;

	@Enumerated(EnumType.STRING)
	private EUloga name;


	public UlogaEntity() {

	}
	

	public Integer getIdUloga() {
		return idUloga;
	}


	public void setIdUloga(Integer idUloga) {
		this.idUloga = idUloga;
	}


	public EUloga getName() {
		return name;
	}


	public void setName(EUloga name) {
		this.name = name;
	}


}
