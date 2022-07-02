package com.example.app_cgd.DTO;

public class Sintomas {
    String  humor, sintomas, outros;

    public Sintomas() {
    }

    public Sintomas(String humor, String sintomas, String outros) {
        this.humor = humor;
        this.sintomas = sintomas;
        this.outros = outros;
    }

    public String getHumor() {
        return humor;
    }

    public void setHumor(String humor) {
        this.humor = humor;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }
}
