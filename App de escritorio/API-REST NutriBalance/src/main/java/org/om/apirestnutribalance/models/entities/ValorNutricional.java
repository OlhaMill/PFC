package org.om.apirestnutribalance.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "valor_nutricional")
public class ValorNutricional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "calorias")
    private Double calorias;

    @Column(name = "proteina")
    private Double proteina;

    @Column(name = "grasa")
    private Double grasa;

    @Column(name = "carbohidratos")
    private Double carbohidratos;

    @Column(name = "sal")
    private Double sal;

    @Column(name = "azucar")
    private Double azucar;

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