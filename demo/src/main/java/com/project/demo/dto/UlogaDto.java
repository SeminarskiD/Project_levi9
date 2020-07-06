package com.project.demo.dto;

public class UlogaDto {

	private int idUloga;
	
	private String nazivUloga;

	public UlogaDto(int idUloga, String nazivUloga) {
		super();
		this.idUloga = idUloga;
		this.nazivUloga = nazivUloga;
	}

	public int getIdUloga() {
		return idUloga;
	}

	public String getNazivUloga() {
		return nazivUloga;
	}
	
	
	
	
}
