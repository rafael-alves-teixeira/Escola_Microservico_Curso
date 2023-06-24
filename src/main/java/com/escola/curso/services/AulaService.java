package com.escola.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.curso.entities.Aula;
import com.escola.curso.repositories.AulaRepository;

@Service
public class AulaService {


	@Autowired
	private AulaRepository aulaRepository;
	
	
	public List<Aula> findAll() {
		List<Aula> aula = aulaRepository.findAll();
		return aula;

	}
	
	public Optional<Aula> findById(Long id) {
		Optional<Aula> aula = aulaRepository.findById(id);
		return aula;
	}

	public Optional<Aula> cadastrarAula(Aula aula) {
		
		if(aulaRepository.findByAula(aula.getAula()).isPresent()) {
			return Optional.empty();
	}
		
		return Optional.of(aulaRepository.save(aula));		
	}

	public Optional<Aula> atualizarAula(Aula aula) {
		
		if(aulaRepository.findById(aula.getId()).isPresent()) {
			
			if(aulaRepository.findByAula(aula.getAula()).isPresent()) {
				return Optional.empty(); //Verifica por nome e não deixa atualizar com nome já existente
		}
						
			return Optional.ofNullable(aulaRepository.save(aula));
		}
		
		return Optional.empty();
	}
	

	public void deleteById(Long id) {
		
		if(aulaRepository.findById(id).isPresent()) {	
			
			aulaRepository.deleteById(id);
		}		
	}

}
