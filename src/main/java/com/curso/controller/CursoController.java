package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/cursos")
@Tag(name = "Servicio de cursos", description = "Este servicio proporciona operaciones sobre cursos.")
public class CursoController {

	@Autowired
	private CursosService cursosService;

	@Operation(summary = "Obtención de todos los cursos", description = "Obtiene el listado completo de todos los cursos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> listadoCursos() {
		return cursosService.getAllCursos();
	}

	@Operation(summary = "Alta de un curso", description = "Da de alta un curso a partir de sus datos: código, nombre, duración y precio. Posteriormente devuelve el listado completo de todos los cursos.")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> crearCurso(
			@Parameter(description = "Datos del curso: código, nombre, duración y precio") @RequestBody Curso curso) {
		cursosService.addCurso(curso);
		return cursosService.getAllCursos();
	}

	@Operation(summary = "Borrado de un curso por su código", description = "Realiza el borrado de un curso a partir del código del curso indicado en la URL. Posteriormente devuelve el listado completo de todos los cursos.")
	@DeleteMapping(value = "/{codCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> borrarCurso(@Parameter(description = "Código del curso") @PathVariable String codCurso) {
		cursosService.deleteCurso(codCurso);
		return cursosService.getAllCursos();
	}

	@Operation(summary = "Actualización de la duración de un curso", description = "Actualiza la duración de un curso a partir del código de curso y la duración indicadas en la URL")
	@PatchMapping(value = "/{codCurso}/duracion/{nuevaDuracion}")
	public void actualizarCurso(@Parameter(description = "Código del curso") @PathVariable String codCurso,
			@Parameter(description = "Nueva duración del curso") @PathVariable Double nuevaDuracion) {
		cursosService.updateCurso(codCurso, nuevaDuracion);
	}

	@Operation(summary = "Búsqueda de un curso por su código", description = "Obtiene los datos de un curso a partir del código del curso indicado en la URL")
	@GetMapping(value = "/{codCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso(@Parameter(description = "Código del curso") @PathVariable String codCurso) {
		return cursosService.getCurso(codCurso);
	}

	@Operation(summary = "Búsqueda de cursos por un rango de precios", description = "Obtiene un listado de cursos cuyo precio está entre un mínimo y un máximo.")
	@GetMapping(value = "/rangoPrecios")
	public List<Curso> listadoCursosPorPrecio(@Parameter(description = "Precio mínimo") @RequestParam Double precioMin,
			@Parameter(description = "Precio máximo") @RequestParam Double precioMax) {
		return cursosService.getCursosByPrecioRange(precioMin, precioMax);
	}

	@Operation(summary = "Búsqueda de un curso por su nombre", description = "Obtiene los datos de un curso a partir del nombre del curso indicado en la URL")
	@GetMapping(value = "/nombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCursoPorNombre(@Parameter(description = "Nombre del curso") @PathVariable String nombre) {
		return cursosService.findByNombre(nombre);
	}

}
