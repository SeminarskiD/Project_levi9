package com.project.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.model.EUloga;
import com.project.demo.model.KorisnikEntity;
import com.project.demo.model.UlogaEntity;
import com.project.demo.payload.request.LoginRequest;
import com.project.demo.payload.request.SingupRequest;
import com.project.demo.payload.response.JwtResponse;
import com.project.demo.payload.response.MessageResponse;
import com.project.demo.repository.KorisnikRepository;
import com.project.demo.repository.UlogaRepository;
import com.project.demo.security.jwt.JwtUtils;
import com.project.demo.security.service.KorisnikDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	KorisnikRepository userRepository;

	@Autowired
	UlogaRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		KorisnikDetailsImpl userDetails = (KorisnikDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Greska: Korisnicko ime je zauzeto!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email je zauzet!"));
		}

		// Kreiranje novog naloga
		KorisnikEntity user = new KorisnikEntity(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<UlogaEntity> roles = new HashSet<>();

		if (strRoles == null) {
			UlogaEntity userRole = roleRepository.findByName(EUloga.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Greska: Uloga nije pronadjenja ."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					UlogaEntity adminRole = roleRepository.findByName(EUloga.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Greska: Uloga nije pronadjenja "));
					roles.add(adminRole);

					break;

				default:
					UlogaEntity userRole = roleRepository.findByName(EUloga.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Greska: Uloga nije pronadjenja "));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Korisnik je uspesno registrovan"));
	}
}