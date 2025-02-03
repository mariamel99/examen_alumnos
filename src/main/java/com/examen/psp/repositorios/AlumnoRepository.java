package com.examen.psp.repositorios;

import com.examen.psp.entidades.Alumno;

import java.util.List;

public interface AlumnoRepository {

    public List<Alumno> listarAlumnos();
    public Alumno buscarAlumno(Long id);
    public Alumno guardarAlumno(Alumno alumno);

    Alumno actualizarAlumno(Long id, Alumno alumno) throws Exception;

    public void eliminarAlumno(Long id);
}
