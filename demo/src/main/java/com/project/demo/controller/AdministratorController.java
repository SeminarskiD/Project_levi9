package com.project.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.KorisnikDto;
import com.project.demo.service.KorisnikDetailsService;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
	
	@Autowired
	KorisnikDetailsService korisnikS;
	
	@PostMapping(path = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	
	public ResponseEntity<KorisnikDto> save(@RequestBody final KorisnikDto korisnikDto) {
		final KorisnikDto response = korisnikS.save(korisnikDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
