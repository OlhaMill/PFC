package org.om.nutribalance.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Receta {
    private Integer id;
    private String nombre;
    private List<Ingrediente> listaIngredientes = new ArrayList<>();
    private List<String> instrucciones;
    private ValorNutricional valorNutricional;
    private String foto;
    private Double dificultad;
    private Integer tiempoPreparacion;
    private int creadoPor;

    public Receta(Integer id, String nombre, List<String> instrucciones, ValorNutricional valorNutricional, String foto, Double dificultad, Integer tiempoPreparacion, List<Ingrediente> listaIngredientes, int creadoPor) {
        this.id = id;
        this.nombre = nombre;
        this.listaIngredientes = listaIngredientes;
        this.instrucciones = instrucciones;
        this.valorNutricional = valorNutricional;
        this.foto = foto;
        this.dificultad = dificultad;
        this.tiempoPreparacion = tiempoPreparacion;
        this.creadoPor = creadoPor;
    }
    public Receta(String vacio){
        this.nombre = "-";
    }
    public Receta(){}

    public Integer getId() {
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

    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public List<String> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<String> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public ValorNutricional getValorNutricional() {
        return valorNutricional;
    }

    public void setValorNutricional(ValorNutricional valorNutricional) {
        this.valorNutricional = valorNutricional;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getDificultad() {
        return dificultad;
    }

    public void setDificultad(Double dificultad) {
        this.dificultad = dificultad;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public int getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }
}