package org.om.apirestnutribalance.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "medidas")
public class Medida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "imc")
    private Double imc;

    @Column(name = "cir_cintura")
    private Double cirCintura;

    @Column(name = "cir_cadera")
    private Double cirCadera;

    @Column(name = "cir_adicional", length = Integer.MAX_VALUE)
    private String cirAdicional;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public Double getCirCintura() {
        return cirCintura;
    }

    public void setCirCintura(Double cirCintura) {
        this.cirCintura = cirCintura;
    }

    public Double getCirCadera() {
        return cirCadera;
    }

    public void setCirCadera(Double cirCadera) {
        this.cirCadera = cirCadera;
    }

    public String getCirAdicional() {
        return cirAdicional;
    }

    public void setCirAdicional(String cirAdicional) {
        this.cirAdicional = cirAdicional;
    }

}