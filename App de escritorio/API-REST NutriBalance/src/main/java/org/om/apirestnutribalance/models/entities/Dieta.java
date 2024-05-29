package org.om.apirestnutribalance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "dieta")
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Nutricionista creadoPor;

    @OneToMany(mappedBy = "dieta")
    private Set<DietaReceta> listaRecetas = new LinkedHashSet<>();

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

    public Nutricionista getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Nutricionista creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Set<DietaReceta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(Set<DietaReceta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @JsonProperty("creadoPorId")
    public Integer getCreadoPorId() {
        return creadoPor != null ? creadoPor.getId() : null;
    }
}