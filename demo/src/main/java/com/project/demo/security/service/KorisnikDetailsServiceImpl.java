package com.project.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.demo.model.KorisnikEntity;
import com.project.demo.repository.KorisnikRepository;

@Service
public class KorisnikDetailsServiceImpl implements UserDetailsService {

	@Autowired
	KorisnikRepository korisnikRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		KorisnikEntity korisnik = korisnikRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Nema korisnika sa username: " + username));

		return KorisnikDetailsImpl.build(korisnik);
	}

}
