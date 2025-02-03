package com.examen.psp.servicios;

import com.examen.psp.entidades.Alumno;

import java.util.List;

public interface AlumnoService {

    List<Alumno> listar();
    Alumno buscar(Long id);
    Alumno guardar(Alumno alumno);
    Alumno actualizar(Long id,Alumno alumno) throws Exception;
    Alumno eliminar(Long id) ;

}
