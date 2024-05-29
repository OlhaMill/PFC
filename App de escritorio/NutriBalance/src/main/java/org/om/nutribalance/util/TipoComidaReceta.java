package org.om.nutribalance.util;

import org.om.nutribalance.model.entities.Receta;

public class TipoComidaReceta {
    private String tipoComida;
    private Receta lunes;
    private Receta martes;
    private Receta miercoles;
    private Receta jueves;
    private Receta viernes;
    private Receta sabado;
    private Receta domingo;

    public TipoComidaReceta(String tipoComida, Receta lunes, Receta martes, Receta miercoles, Receta jueves, Receta viernes, Receta sabado, Receta domingo) {
        this.tipoComida = tipoComida;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }
    public TipoComidaReceta() {
        Receta recetaVacia = new Receta("vacio");
        this.lunes = recetaVacia;
        this.martes = recetaVacia;
        this.miercoles = recetaVacia;
        this.jueves = recetaVacia;
        this.viernes = recetaVacia;
        this.sabado = recetaVacia;
        this.domingo = recetaVacia;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Receta getLunes() {
        return lunes;
    }

    public void setLunes(Receta lunes) {
        this.lunes = lunes;
    }

    public Receta getMartes() {
        return martes;
    }

    public void setMartes(Receta martes) {
        this.martes = martes;
    }

    public Receta getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(Receta miercoles) {
        this.miercoles = miercoles;
    }

    public Receta getJueves() {
        return jueves;
    }

    public void setJueves(Receta jueves) {
        this.jueves = jueves;
    }

    public Receta getViernes() {
        return viernes;
    }

    public void setViernes(Receta viernes) {
        this.viernes = viernes;
    }

    public Receta getSabado() {
        return sabado;
    }

    public void setSabado(Receta sabado) {
        this.sabado = sabado;
    }

    public Receta getDomingo() {
        return domingo;
    }

    public void setDomingo(Receta domingo) {
        this.domingo = domingo;
    }
}
