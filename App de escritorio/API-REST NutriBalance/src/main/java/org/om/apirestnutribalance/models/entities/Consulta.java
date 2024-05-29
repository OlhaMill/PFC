package org.om.apirestnutribalance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha_hora")
    private Instant fechaHora;

    @Column(name = "observaciones", length = Integer.MAX_VALUE)
    private String observaciones;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "objetivo", length = Integer.MAX_VALUE)
    private String objetivo;

    @Column(name = "objeivo_peso")
    private Double objetivoPeso;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutricionista_id")
    private Nutricionista nutricionista;

    @OneToOne(mappedBy = "consulta")
    private Medida medidas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Double getObjetivoPeso() {
        return objetivoPeso;
    }

    public void setObjetivoPeso(Double objetivoPeso) {
        this.objetivoPeso = objetivoPeso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public Medida getMedidas() {
        return medidas;
    }

    public void setMedidas(Medida medidas) {
        this.medidas = medidas;
    }

    @JsonProperty("clienteId")
    public Integer getClienteId() {
        return cliente != null ? cliente.getId() : null;
    }
    @JsonProperty("clienteNombre")
    public String getClienteNombre() {
        return cliente != null ? cliente.getNombre() : null;
    }

    @JsonProperty("nutricionistaId")
    public Integer getNutricionistaId() {
        return nutricionista != null ? nutricionista.getId() : null;
    }
}