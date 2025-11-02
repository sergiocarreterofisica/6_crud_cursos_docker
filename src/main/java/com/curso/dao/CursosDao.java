package com.curso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.model.Curso;

public interface CursosDao extends JpaRepository<Curso, String> {

	// Método para obtener un listado de cursos por su rango de precios.
	List<Curso> findByPrecioBetween(Double precioMin, Double precioMax);
	
	Curso findByNombre(String nombre);

}
