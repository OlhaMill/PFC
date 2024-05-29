package org.om.apirestnutribalance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "receta")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "instrucciones")
    private List<String> instrucciones;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "valor_nutricional_id")
    private ValorNutricional valorNutricional;

    @Column(name = "foto", length = Integer.MAX_VALUE)
    private String foto;

    @Column(name = "dificultad")
    private Double dificultad;

    @Column(name = "tiempo_preparacion")
    private Integer tiempoPreparacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Nutricionista creadoPor;

    @OneToMany(mappedBy = "receta")
    private Set<Ingrediente> ingredientes = new LinkedHashSet<>();

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

    public Nutricionista getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Nutricionista creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Set<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Set<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @JsonProperty("creadoPorId")
    public Integer getCreadoPorId() {
        return creadoPor != null ? creadoPor.getId() : null;
    }
}