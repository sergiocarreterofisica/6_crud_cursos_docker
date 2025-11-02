package com.curso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.dao.CursosDao;
import com.curso.model.Curso;
import com.curso.service.CursosService;

@Service
public class CursosServiceImpl implements CursosService {

	@Autowired
	private CursosDao cursosDao;

	@Override
	public List<Curso> getAllCursos() {
		return cursosDao.findAll();
	}

	@Override
	@Transactional
	public void addCurso(Curso curso) {
		cursosDao.save(curso);
	}

	@Override
	@Transactional
	public void deleteCurso(String codCurso) {
		cursosDao.deleteById(codCurso);
	}

	@Override
	@Transactional
	public void updateCurso(String codCurso, Double duracion) {
		Curso curso = cursosDao.findById(codCurso).orElse(null);
		if (curso != null) {
			curso.setDuracion(duracion);
			cursosDao.save(curso);
		}
	}

	@Override
	public Curso getCurso(String codCurso) {

		return cursosDao.findById(codCurso).orElse(null);
	}

	@Override
	public List<Curso> getCursosByPrecioRange(Double precioMinimo, Double precioMaximo) {
		return cursosDao.findByPrecioBetween(precioMinimo, precioMaximo);
	}

	@Override
	public Curso findByNombre(String nombre) {
		return cursosDao.findByNombre(nombre);
	}

}
