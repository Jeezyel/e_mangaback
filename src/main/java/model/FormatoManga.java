package model;

import jakarta.persistence.Entity;

@Entity
public class FormatoManga extends DefaultEntity{
    private String formato;

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
}
