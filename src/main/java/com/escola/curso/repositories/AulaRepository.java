package com.escola.curso.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.curso.entities.Aula;

public interface AulaRepository extends JpaRepository<Aula, Long> {
	
	Optional<Aula> findByAula(String aula);	

}
