package com.escola.curso.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "É obrigatório informar o nome!")
	private String curso;
	
	@NotBlank(message = "Adicione uma descrição do curso!")
	private String description;
		
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "curso", cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("curso") //Evita looping!
	private List<Modulo> modulo;
	
	public Curso() {
	}

	public Curso(Long id, String curso, String description, List<Modulo> modulo) {
		this.id = id;
		this.curso = curso;
		this.description = description;
		this.modulo = modulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Modulo> getModulo() {
		return modulo;
	}

	public void setModulo(List<Modulo> modulo) {
		this.modulo = modulo;
	}
	
	
	
	
	

}
