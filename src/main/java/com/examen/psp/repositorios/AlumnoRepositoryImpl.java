package com.examen.psp.repositorios;

import com.examen.psp.entidades.Alumno;
import com.examen.psp.servicios.CryptoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AlumnoRepositoryImpl implements AlumnoRepository {

    private final CryptoService cryptoService;

    private final Map<Long, Alumno> alumnos;
    private Long idSecuencia = 1L;

    @Autowired
    public AlumnoRepositoryImpl(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
        this.alumnos = new HashMap<>();
    }

    @PostConstruct
    public void init() throws Exception {

        String[] claves = {
                "Red#Dragon7391", "Blue$Whale4528", "Green@Frog1937",
                "Yellow#Sun8246", "Purple$Moon5713", "Orange@Star9462",
                "Pink#Cloud3845", "White$Snow6159", "Black@Night2874",
                "Brown#Tree5294", "Gray$Wolf7163", "Gold@Lion4981",
                "Silver#Fish3567", "Bronze$Bird8925"
        };

        // Inicializa el mapa con un listado de alumnos predeterminado
        for (int i = 0; i < claves.length; i++) {
            String claveCifrada = cryptoService.encriptarConPublica(claves[i]);

            Alumno alumno = new Alumno("Nombre Apellido Apellido", "XXXXXXXXX", claveCifrada);
            alumno.setId(idSecuencia++);
            alumnos.put(alumno.getId(), alumno);
        }
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return new ArrayList<>(alumnos.values());
    }

    @Override
    public Alumno buscarAlumno(Long id) {
        return alumnos.get(id);
    }

    @Override
    public Alumno guardarAlumno(Alumno alumno) {
        if (alumno.getId() == null) {
            alumno.setId(idSecuencia++);
        }
        alumnos.put(alumno.getId(), alumno);
        return alumno;
    }

    @Override
    public Alumno actualizarAlumno(Long id, Alumno alumno) throws Exception {


        return alumno;
    }

    @Override
    public void eliminarAlumno(Long id) {
        alumnos.remove(id);
    }


}
