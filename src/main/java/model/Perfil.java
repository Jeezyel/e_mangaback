package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collections;
import java.util.Set;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {

    ADMIN(1, "ADMIN"),
    USER(2, "ADMIN");

    private final int id;
    private final String label;

    Perfil(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Perfil valueOf(Integer id) {
        if (id == null)
            return null;
        for(Perfil perfil : Perfil.values()) {
            if (perfil.getId().equals(id))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido:");
    }

}
