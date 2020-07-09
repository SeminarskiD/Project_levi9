package com.project.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class KorisnikEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idKorisnik;

	private String ime;

	private String prezime;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@NotBlank
	private String username;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UlogaEntity> uloge = new HashSet<>();
	
	public KorisnikEntity() {
	}
	
	public KorisnikEntity(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}


	public Long getIdKorisnik() {
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

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UlogaEntity> getRoles() {
		return uloge;
	}

	public void setRoles(Set<UlogaEntity> uloge) {
		this.uloge = uloge;
	}
	
	


}
