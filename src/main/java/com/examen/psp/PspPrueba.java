package com.examen.psp;

import com.examen.psp.entidades.Alumno;
import com.examen.psp.repositorios.AlumnoRepository;
import com.examen.psp.repositorios.AlumnoRepositoryImpl;
import com.examen.psp.servicios.AlumnoServiceImpl;
import com.examen.psp.servicios.CryptoService;
import com.examen.psp.servicios.CryptoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

@Component
public class PspPrueba implements CommandLineRunner {
    private CryptoServiceImpl cryptoService ;
    private AlumnoRepositoryImpl alumnoRepository ;
    private static final String BASE_URL = "http://localhost:8080/asignatura";
    private static final String miClave = "Bronze$Bird8925";

    @Autowired
    public PspPrueba(CryptoServiceImpl cryptoService, AlumnoRepositoryImpl alumnoRepository) {
        this.cryptoService = cryptoService;
        this.alumnoRepository= alumnoRepository;
    }

    public void ejecutar() throws Exception {

        // Listar todos los alumnos matriculados
        List<Alumno> registros = listarAlumnos();

        // 1.- Recorrer lista alumnos y desencriptar sus claves
            for (Alumno alumno : registros) {

                String claveDescriptada = cryptoService.desencriptarConPrivada(alumno.getClave());
                // 2.- Actualizar registro alumno
                if (claveDescriptada.equals(miClave)) {
                    alumno.setNombre("Mariam El Amrnai");
                    alumno.setDni("Y8651598R");
                    alumno.setClave(claveDescriptada);
                    modificarAlumno(alumno.getId(),alumno);
                    System.out.println("Alumno modificado");
                    alumnoRepository.guardarAlumno(alumno);
                }
                else{
                    System.out.println("Alumno "+alumno.getId()+" eliminado");
                    alumnoRepository.eliminarAlumno(alumno.getId());
                }
            }
            registros = alumnoRepository.listarAlumnos();

        // 3.- Mostrar nueva lista de alumnos
            for (Alumno alumno : registros) {
                System.out.println(alumno);
            }
//            registros.clear();
//            registros = listarAlumnos();
//            for (Alumno alumno : registros) {
//                System.out.println(alumno);
//            }


        // 4.- Eliminar el resto de alumnos



        // 5.- Listar alumnos (sólo debería quedar el tuyo)


    }

    @Override
    public void run(String... args) throws Exception {
        ejecutar();
    }

    private List<Alumno> listarAlumnos() {
        List<Alumno> rowAlumnos = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Alumno[]> response =
                restTemplate.getForEntity(BASE_URL, Alumno[].class);
        Alumno[] alumnos = response.getBody();
        System.out.println("LISTADO ALUMNOS:");
        for (Alumno alumno : alumnos) {
            rowAlumnos.add(alumno);
            System.out.println(alumno);
        }
        return rowAlumnos;
    }

    private void eliminarAlumno(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL + "/" + id);
        System.out.println("Alumno eliminado con ID: " + id);
    }

    private Alumno modificarAlumno(Long id, Alumno alumno)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Alumno> requestEntity = new
                HttpEntity<>(alumno, headers);
        ResponseEntity<Alumno> response =
                restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT,
                        requestEntity, Alumno.class);
        return response.getBody();
    }

}
