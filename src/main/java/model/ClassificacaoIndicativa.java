package model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ClassificacaoIndicativa {
    LIVRE(1, "Livre"),
    MAIS_10(2, "10+"),
    MAIS_12(3, "12+"),
    MAIS_14(4, "14+"),
    MAIS_16(5, "16+"),
    MAIS_18(6, "18+");

    private final int id;
    private final String label;

    ClassificacaoIndicativa(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static ClassificacaoIndicativa fromId(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (ClassificacaoIndicativa classificacao : ClassificacaoIndicativa.values()) {
            if (id.equals(classificacao.getId()))
                return classificacao;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
