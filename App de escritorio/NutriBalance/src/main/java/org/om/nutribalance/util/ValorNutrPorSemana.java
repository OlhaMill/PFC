package org.om.nutribalance.util;

public class ValorNutrPorSemana {
    private String tipo;
    private double lunes;
    private double martes;
    private double miercoles;
    private double jueves;
    private double viernes;
    private double sabado;
    private double domingo;

    public ValorNutrPorSemana() {
        lunes = 0;
        martes = 0;
        miercoles = 0;
        jueves = 0;
        viernes = 0;
        sabado = 0;
        domingo = 0;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getLunes() {
        return lunes;
    }

    public void setLunes(double lunes) {
        this.lunes = lunes;
    }

    public double getMartes() {
        return martes;
    }

    public void setMartes(double martes) {
        this.martes = martes;
    }

    public double getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(double miercoles) {
        this.miercoles = miercoles;
    }

    public double getJueves() {
        return jueves;
    }

    public void setJueves(double jueves) {
        this.jueves = jueves;
    }

    public double getViernes() {
        return viernes;
    }

    public void setViernes(double viernes) {
        this.viernes = viernes;
    }

    public double getSabado() {
        return sabado;
    }

    public void setSabado(double sabado) {
        this.sabado = sabado;
    }

    public double getDomingo() {
        return domingo;
    }

    public void setDomingo(double domingo) {
        this.domingo = domingo;
    }
}
