package org.om.nutribalance.model.entities;



public class Alimento {

    private int id;
    private String nombre;
    private Nutricionista creadoPor;

    public Alimento(Integer id, String nombre, Nutricionista creadoPor) {
        this.id = id;
        this.nombre = nombre;
        this.creadoPor = creadoPor;
    }

    public Alimento(){}
    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nutricionista getCreadoPor() {
        return creadoPor;
    }
    public void setCreadoPor(Nutricionista creadoPor) {
        this.creadoPor = creadoPor;
    }
}