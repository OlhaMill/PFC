package org.om.nutribalance.model.entities;

import jakarta.persistence.*;

public class ValorNutricional {
    private Integer id;
    private Double calorias;
    private Double proteina;
    private Double grasa;
    private Double carbohidratos;
    private Double sal;
    private Double azucar;

    public ValorNutricional(Integer id, Double calorias, Double proteina, Double grasa, Double carbohidratos, Double sal, Double azucar) {
        this.id = id;
        this.calorias = calorias;
        this.proteina = proteina;
        this.grasa = grasa;
        this.carbohidratos = carbohidratos;
        this.sal = sal;
        this.azucar = azucar;
    }

    public ValorNutricional(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public Double getProteina() {
        return proteina;
    }

    public void setProteina(Double proteina) {
        this.proteina = proteina;
    }

    public Double getGrasa() {
        return grasa;
    }

    public void setGrasa(Double grasa) {
        this.grasa = grasa;
    }

    public Double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(Double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getAzucar() {
        return azucar;
    }

    public void setAzucar(Double azucar) {
        this.azucar = azucar;
    }
}