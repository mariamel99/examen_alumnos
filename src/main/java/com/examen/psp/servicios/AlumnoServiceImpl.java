package com.examen.psp.servicios;

import com.examen.psp.entidades.Alumno;
import com.examen.psp.repositorios.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Alumno> listar() {
        return alumnoRepository.listarAlumnos();
    }

    @Override
    public Alumno buscar(Long id) {
        return alumnoRepository.buscarAlumno(id);
    }

    @Override
    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.guardarAlumno(alumno);
    }

    @Override
    public Alumno actualizar(Long id,Alumno alumno) throws Exception {
        return alumnoRepository.actualizarAlumno( id,alumno);
    }

    @Override
    public Alumno eliminar(Long id) {
        return null;
    }

}
