package com.examen.psp.controladores;

import com.examen.psp.entidades.Alumno;
import com.examen.psp.servicios.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> listar() {
        return alumnoService.listar();
    }

    @GetMapping("/{id}")
    public Alumno buscar(@PathVariable Long id) {
        return alumnoService.buscar(id);
    }

    @PostMapping
    public Alumno guardar(@RequestBody Alumno alumno) {
        return alumnoService.guardar(alumno);
    }

    @PutMapping("/{id}")
    public Alumno actualizar(@PathVariable Long id, @RequestBody Alumno misDatos) throws Exception {
        return alumnoService.actualizar(id,misDatos);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        alumnoService.eliminar(id);
    }

}
