package org.om.nutribalance.model.entities;

public class Medidas {
    private Integer id;
    private Consulta consulta;
    private Double peso;
    private Double imc;
    private Double cirCintura;
    private Double cirCadera;
    private String cirAdicional;

    public Medidas(Integer id, Consulta consulta, Double peso, Double imc, Double cirCintura, Double cirCadera, String cirAdicional) {
        this.id = id;
        this.consulta = consulta;
        this.peso = peso;
        this.imc = imc;
        this.cirCintura = cirCintura;
        this.cirCadera = cirCadera;
        this.cirAdicional = cirAdicional;
    }
    public Medidas(){}

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