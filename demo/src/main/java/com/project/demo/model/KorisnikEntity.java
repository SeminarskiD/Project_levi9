package com.project.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class KorisnikEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idKorisnik;
	
	private String ime;
	
	private String prezime;
	
	private String password;
	
	private String username;
	
	@ManyToOne
	@JoinColumn(name="Uloga_idUloga")
	private UlogaEntity uloga;
	
	public KorisnikEntity() {
	}

	public int getIdKorisnik() {
		return idKorisnik;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public UlogaEntity getUloga() {
		return uloga;
	}

	public void setUloga(UlogaEntity uloga) {
		this.uloga = uloga;
	}
	
	
	
	
}
