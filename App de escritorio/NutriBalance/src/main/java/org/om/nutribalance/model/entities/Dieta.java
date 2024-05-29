package org.om.nutribalance.model.entities;

import java.util.List;

public class Dieta {

    private Integer id;
    private String nombre;
    private String descripcion;
    private List<DietaReceta> listaRecetas;
    private List<Cliente> listaClientes;
    private Nutricionista creadoPor;

    public Dieta(Integer id, String nombre, String descripcion, List<DietaReceta> listaRecetas, List<Cliente> listaClientes, Nutricionista creadoPor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaRecetas = listaRecetas;
        this.listaClientes = listaClientes;
        this.creadoPor = creadoPor;
    }
    public Dieta(){}

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DietaReceta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<DietaReceta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Nutricionista getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Nutricionista creadoPor) {
        this.creadoPor = creadoPor;
    }
    @Override
    public String toString() {
        return nombre;
    }
}