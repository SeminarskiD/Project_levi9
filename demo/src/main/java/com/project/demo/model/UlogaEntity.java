package com.project.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UlogaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUloga;

	private String nazivUloga;

	// bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy = "uloga")
	private List<KorisnikEntity> korisniks;

	public UlogaEntity() {

	}
	
	public int getIdUloga() {
		return this.idUloga;
	}

	public void setIdUloga(int idUloga) {
		this.idUloga = idUloga;
	}

	public String getNazivUloga() {
		return this.nazivUloga;
	}

	public void setNazivUloga(String nazivUloga) {
		this.nazivUloga = nazivUloga;
	}

	public List<KorisnikEntity> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<KorisnikEntity> korisniks) {
		this.korisniks = korisniks;
	}

	public KorisnikEntity addKorisnik(KorisnikEntity korisnik) {
		getKorisniks().add(korisnik);
		korisnik.setUloga(this);

		return korisnik;
	}

	public KorisnikEntity removeKorisnik(KorisnikEntity korisnik) {
		getKorisniks().remove(korisnik);
		korisnik.setUloga(null);

		return korisnik;
	}

}
