package com.project.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.EUloga;
import com.project.demo.model.UlogaEntity;

public interface UlogaRepository extends JpaRepository<UlogaEntity, Long> {
	
	Optional<UlogaEntity> findByName(EUloga name);

}
