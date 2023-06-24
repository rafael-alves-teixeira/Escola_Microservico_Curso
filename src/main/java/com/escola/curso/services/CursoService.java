package com.escola.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.curso.entities.Curso;
import com.escola.curso.repositories.CursoRepository;

@Service
public class CursoService {


	@Autowired
	private CursoRepository cursoRepository;
	
	
	public List<Curso> findAll() {
		List<Curso> curso = cursoRepository.findAll();
		return curso;

	}
	
	
	public List<Curso> findAllByCursoContainingIgnoreCase(String curso) {
		List<Curso> cursos = cursoRepository.findAllByCursoContainingIgnoreCase(curso);		
		return cursos;
	}
	
	
	
	
	public Optional<Curso> findById(Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		return curso;
	}

	public Optional<Curso> cadastrarCurso(Curso curso) {
		
		if(cursoRepository.findByCurso(curso.getCurso()).isPresent()) {
			return Optional.empty();
	}
		
		return Optional.of(cursoRepository.save(curso));		
	}

	public Optional<Curso> atualizarCurso(Curso curso) {
		
		if(cursoRepository.findById(curso.getId()).isPresent()) {
			
			if(cursoRepository.findByCurso(curso.getCurso()).isPresent()) {
				return Optional.empty();
		}
						
			return Optional.ofNullable(cursoRepository.save(curso));
		}
		
		return Optional.empty();
	}
	

	public void deleteById(Long id) {
		

		Optional<Curso> curso = cursoRepository.findById(id);
		
		if(curso.isPresent()) {
			if(!curso.get().getModulo().isEmpty()) {
				cursoRepository.deleteById(id);
			}	
		}
	}		
}
