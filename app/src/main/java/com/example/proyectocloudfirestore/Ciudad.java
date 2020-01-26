package com.example.proyectocloudfirestore;

public class Ciudad {

    private String nombre;
    private String comunidad;
    private String pais;
    private String poblacion;

    public Ciudad() {
    }

    public Ciudad(String nombre, String comunidad, String pais, String poblacion) {
        this.nombre = nombre;
        this.comunidad = comunidad;
        this.pais = pais;
        this.poblacion = poblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
}
