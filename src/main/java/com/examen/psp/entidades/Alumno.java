package com.examen.psp.entidades;

public class Alumno {

    private Long id;
    private String nombre;
    private String dni;
    private String clave;

    public Alumno(String nombre, String dni, String clave) {
        this.nombre = nombre;
        this.dni = dni;
        this.clave = clave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + getId() +
                ", nombre=" + getNombre() +
                ", dni=" + getDni() +
                ", clave=" + getClave() + '}';
    }
}
