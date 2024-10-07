package model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Idioma {
    PORTUGUES(1, "Português"),
    INGLES(2, "Inglês"),
    ESPANHOL(3, "Espanhol"),
    JAPONES(4, "Japonês"),
    FRANCES(5, "Francês"),
    ALEMAO(6, "Alemão"),
    ITALIANO(7, "Italiano"),
    COREANO(8, "Coreano"),
    CHINES(9, "Chinês");

    private int id;
    private String label;

    Idioma(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Idioma valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Idioma idioma : Idioma.values()) {
            if (id.equals(idioma.getId()))
                return idioma;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
