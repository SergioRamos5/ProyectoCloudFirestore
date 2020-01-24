package com.example.proyectocloudfirestore;

public class Ciudad {

    private String nombre;
    private String comunidad;
    private String pais;
    private int poblacion;

    public Ciudad() {
    }

    public Ciudad(String nombre, String comunidad, String pais, int poblacion) {
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

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }
}
