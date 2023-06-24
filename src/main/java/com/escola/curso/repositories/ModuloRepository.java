package com.escola.curso.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.curso.entities.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Long>{
	
	Optional<Modulo> findByModulo(String modulo);	

}

