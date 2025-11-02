package com.curso.service;

import java.util.List;

import com.curso.model.Curso;

public interface CursosService {

	List<Curso> getAllCursos();

	void addCurso(Curso curso);

	void deleteCurso(String codCurso);

	void updateCurso(String codCurso, Double duracion);

	Curso getCurso(String codCurso);

	List<Curso> getCursosByPrecioRange(Double precioMinimo, Double precioMaximo);

	Curso findByNombre(String nombre);

}
