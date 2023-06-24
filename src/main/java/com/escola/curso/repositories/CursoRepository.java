package com.escola.curso.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.escola.curso.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	Optional<Curso> findByCurso(String curso);
	
	List <Curso> findAllByCursoContainingIgnoreCase(@Param("curso")String titulo);
	
}
