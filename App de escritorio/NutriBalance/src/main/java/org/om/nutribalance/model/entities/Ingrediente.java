package org.om.nutribalance.model.entities;

public class Ingrediente {
    private Integer id;
    private Alimento alimento;
    private Receta receta;
    private Double cantidad;

    public Ingrediente(Integer id, Alimento alimento, Receta receta, Double cantidad) {
        this.id = id;
        this.alimento = alimento;
        this.receta = receta;
        this.cantidad = cantidad;
    }
    public Ingrediente(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}