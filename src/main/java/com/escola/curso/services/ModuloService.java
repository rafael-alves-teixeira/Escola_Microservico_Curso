package com.escola.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.curso.entities.Modulo;
import com.escola.curso.repositories.ModuloRepository;

@Service
public class ModuloService {



	@Autowired
	private ModuloRepository moduloRepository;
	
	
	public List<Modulo> findAll() {
		List<Modulo> modulo = moduloRepository.findAll();
		return modulo;

	}
	
	public Optional<Modulo> findById(Long id) {
		Optional<Modulo> modulo = moduloRepository.findById(id);
		return modulo;
	}

	public Optional<Modulo> cadastrarModulo(Modulo modulo) {
		
		if(moduloRepository.findByModulo(modulo.getModulo()).isPresent()) {
			return Optional.empty();
	}
		
		return Optional.of(moduloRepository.save(modulo));		
	}

	public Optional<Modulo> atualizarModulo(Modulo modulo) {
		
		if(moduloRepository.findById(modulo.getId()).isPresent()) {
			
			if(moduloRepository.findByModulo(modulo.getModulo()).isPresent()) {
				return Optional.empty(); //Verifica por nome e não deixa atualizar com nome já existente
		}
						
			return Optional.ofNullable(moduloRepository.save(modulo));
		}
		
		return Optional.empty();
	}
	

public void deleteById(Long id) {
		
		Optional<Modulo> modulo = moduloRepository.findById(id);
		
		if(modulo.isPresent()) {
			if(!modulo.get().getAula().isEmpty()) {
				moduloRepository.deleteById(id);
			}	
		}
					
	}	


	
}
