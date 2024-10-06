package model;

import jakarta.persistence.*;

@Entity
public class Telefone extends DefaultEntity {
    private String codegoDeArea;
    private String numero;


    public String getCodegoDeArea() {
        return codegoDeArea;
    }

    public void setCodegoDeArea(String codegoDeArea) {
        this.codegoDeArea = codegoDeArea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}