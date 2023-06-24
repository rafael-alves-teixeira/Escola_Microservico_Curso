package com.escola.curso.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.escola.curso.entities.Aula;
import com.escola.curso.services.AulaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aulas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AulaController {


	@Autowired
	private AulaService aulaService; 
	
	@GetMapping("/all")
	public ResponseEntity<List<Aula>> findAll(){				
		return ResponseEntity.ok(aulaService.findAll()); 		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aula> findById(@Valid @PathVariable Long id) {
		return aulaService.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Aula> post(@Valid @RequestBody Aula aula){
		return aulaService.cadastrarAula(aula)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());	
	} 
	
	@PutMapping("/atualizar")
	public ResponseEntity<Aula> putAula(@Valid @RequestBody Aula aula) {
		
		return aulaService.atualizarAula(aula)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Aula> aula = aulaService.findById(id);
		
		if(aula.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		aulaService.deleteById(id);
	}	
	
	
}
