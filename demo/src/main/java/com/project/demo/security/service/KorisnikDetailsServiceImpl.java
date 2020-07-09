package com.project.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.demo.model.KorisnikEntity;
import com.project.demo.repository.KorisnikRepository;

public class KorisnikDetailsServiceImpl implements UserDetailsService {

	@Autowired
	KorisnikRepository korisnikRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		KorisnikEntity korisnik = korisnikRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return KorisnikDetailsImpl.build(korisnik);
	}

}
