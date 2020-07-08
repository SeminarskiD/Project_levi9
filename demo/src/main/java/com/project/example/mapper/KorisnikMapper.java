package com.project.example.mapper;

import org.mapstruct.Mapper;

import com.project.demo.dto.KorisnikDto;
import com.project.demo.model.KorisnikEntity;


@Mapper(componentModel = "spring")
public interface KorisnikMapper {

	KorisnikEntity map(KorisnikDto korisnikDto);

	KorisnikDto mapToDto(KorisnikEntity korisnikEntity);
}
