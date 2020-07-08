package com.project.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.demo.model.KorisnikEntity;

public interface KorisnikRepository extends CrudRepository<KorisnikEntity, Integer> {

}
