package model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FormatoManga {
    PDF(1, "PDF"),
    CBR(2, "CBR"),
    CBZ(3, "CBZ"),
    EPUB(4, "EPUB"),
    IMAGEM(5, "Imagem"),
    HTML(6, "HTML");

    private int id;
    private String label;

    FormatoManga(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static FormatoManga valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (FormatoManga formato : FormatoManga.values()) {
            if (id.equals(formato.getId()))
                return formato;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
