package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.dto.KorisnikDto;
import com.project.demo.model.KorisnikEntity;
import com.project.demo.repository.KorisnikRepository;
import com.project.example.mapper.KorisnikMapper;

@Service
public class KorisnikDetailsServiceImpl implements KorisnikDetailsService {

	@Autowired
	KorisnikRepository korisnikR;

	@Autowired
	KorisnikMapper korisnikM;

	@Override
	public KorisnikDto save(KorisnikDto korisnikDto) {
		final KorisnikEntity korisnikEntityzaCuvanje = korisnikM.map(korisnikDto);
		final KorisnikEntity sacuvaniKorisnik = korisnikR.save(korisnikEntityzaCuvanje);
		final KorisnikDto sacuvaniKorisnikDto = korisnikM.mapToDto(sacuvaniKorisnik);

		return sacuvaniKorisnikDto;
	}

}
