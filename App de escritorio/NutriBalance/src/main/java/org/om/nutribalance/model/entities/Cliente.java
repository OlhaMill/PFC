package org.om.nutribalance.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.*;

public class Cliente {
    private Integer id;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String genero;
    private Nutricionista nutricionista;
    private ValorNutricional valorNutricionalRec;
    private Map<String, Object> fotos;
    private List<Dieta> listaDietas = new ArrayList<>();
    private List<Consulta> progreso = new ArrayList<>();
    private Double valoracion;

    public Cliente(Integer id, String nombre, String email, String telefono, LocalDate fechaNacimiento, String genero, Nutricionista nutricionista, ValorNutricional valorNutricionalRec, Map<String, Object> fotos, List<Dieta> listaDietas, List<Consulta> progreso, Double valoracion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.nutricionista = nutricionista;
        this.valorNutricionalRec = valorNutricionalRec;
        this.fotos = fotos;
        this.listaDietas = listaDietas;
        this.progreso = progreso;
        this.valoracion = valoracion;
    }
    public Cliente(){}

    public Cliente(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }
    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public ValorNutricional getValorNutricionalRec() {
        return valorNutricionalRec;
    }
    public void setValorNutricionalRec(ValorNutricional valorNutricionalRec) {
        this.valorNutricionalRec = valorNutricionalRec;
    }

    public Map<String, Object> getFotos() {
        return fotos;
    }
    public void setFotos(Map<String, Object> fotos) {
        this.fotos = fotos;
    }

    public List<Dieta> getListaDietas() {
        return listaDietas;
    }
    public void setListaDietas(List<Dieta> listaDietas) {
        this.listaDietas = listaDietas;
    }

    public List<Consulta> getProgreso() {
        return progreso;
    }
    public void setProgreso(List<Consulta> progreso) {
        this.progreso = progreso;
    }

    public Double getValoracion() {
        return valoracion;
    }
    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }
    public String toString(){
        return nombre + "  " + email;
    }
}