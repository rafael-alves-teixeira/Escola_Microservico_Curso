package com.escola.curso.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_modulo")
public class Modulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "É obrigatório informar o nome do módulo!")
	private String modulo;
	
	@ManyToOne
	@JsonIgnoreProperties("modulo") //Evita looping!
	private Curso curso;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "modulo", cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("modulo") //Evita looping!
	private List<Aula> aula;
	
	public Modulo() {
	}

	public Modulo(Long id, String modulo, Curso curso, List<Aula> aula) {
		this.id = id;
		this.modulo = modulo;
		this.curso = curso;
		this.aula = aula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Aula> getAula() {
		return aula;
	}

	public void setAula(List<Aula> aula) {
		this.aula = aula;
	}
	
	
	
	
	
}
