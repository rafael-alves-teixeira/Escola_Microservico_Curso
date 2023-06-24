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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.escola.curso.entities.Modulo;
import com.escola.curso.services.ModuloService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/modulos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModuloController {
	
	@Autowired
	private ModuloService moduloService; 
	
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Modulo>> findAll(){				
		return ResponseEntity.ok(moduloService.findAll()); 		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Modulo> findById(@Valid @PathVariable Long id) {
		return moduloService.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Modulo> post(@Valid @RequestBody Modulo modulo){
		return moduloService.cadastrarModulo(modulo)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());	
	} 
	
	
	
	@PutMapping("/atualizar")
	public ResponseEntity<Modulo> putModulo(@Valid @RequestBody Modulo modulo) {
		
		return moduloService.atualizarModulo(modulo)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Modulo> modulo = moduloService.findById(id);
		
		if(modulo.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		if(!modulo.get().getAula().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);	
		}	
		
		moduloService.deleteById(id);
	}	
	
	
	
}
