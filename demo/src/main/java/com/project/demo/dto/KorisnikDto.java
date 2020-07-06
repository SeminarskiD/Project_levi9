package com.project.demo.dto;

public class KorisnikDto {
	
	private int idKorisnik;
	
	private String username;
	
	private String password;

	public KorisnikDto(int idKorisnik, String username, String password) {
		super();
		this.idKorisnik = idKorisnik;
		this.username = username;
		this.password = password;
	}

	public int getIdKorisnik() {
		return idKorisnik;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
	

}
