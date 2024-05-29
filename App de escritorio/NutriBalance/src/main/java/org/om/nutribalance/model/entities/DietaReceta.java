package org.om.nutribalance.model.entities;


import org.om.nutribalance.util.DiaSemana;
import org.om.nutribalance.util.TipoComida;

public class DietaReceta {
    private Integer id;
    private DiaSemana dia;
    private int dieta;
    private Receta receta;
    private TipoComida tipoComida;

    public DietaReceta() {}

    public DietaReceta(Integer id, DiaSemana dia, int dieta, Receta receta, TipoComida tipoComida) {
        this.id = id;
        this.dia = dia;
        this.dieta = dieta;
        this.receta = receta;
        this.tipoComida = tipoComida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    public int getDieta() {
        return dieta;
    }

    public void setDieta(int dieta) {
        this.dieta = dieta;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }
}
