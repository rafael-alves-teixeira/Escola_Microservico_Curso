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

import com.escola.curso.entities.Curso;
import com.escola.curso.services.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

	@Autowired
	private CursoService cursoService; 
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Curso>> findAll(){				
		return ResponseEntity.ok(cursoService.findAll()); 		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> findById(@Valid @PathVariable Long id) {
		return cursoService.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{curso}")
	
	public ResponseEntity<List<Curso>> getByCurso(@PathVariable String curso){
		
		return ResponseEntity.ok(cursoService.findAllByCursoContainingIgnoreCase(curso));
	}
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Curso> post(@Valid @RequestBody Curso curso){
		return cursoService.cadastrarCurso(curso)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());	
	} 
	
	@PutMapping("/atualizar")
	public ResponseEntity<Curso> putCurso(@Valid @RequestBody Curso curso) {
		
		return cursoService.atualizarCurso(curso)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Curso> curso= cursoService.findById(id);
		
		if(curso.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		if(!curso.get().getModulo().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);	
		}
		
		cursoService.deleteById(id);
	}	
	
	
	
}
