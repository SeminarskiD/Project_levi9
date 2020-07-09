package com.project.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.KorisnikEntity;

public interface KorisnikRepository extends JpaRepository<KorisnikEntity, Long> {
	
	Optional<KorisnikEntity> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
