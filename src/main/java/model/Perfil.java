package model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collections;
import java.util.Set;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {
    ADMIN(1, "Admin"),
    USER(2, "User");

    private int id;
    private String label;

    Perfil(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Perfil valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for(Perfil perfil : Perfil.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }

    public static Set<Perfil> perfilSet (String perfil){
        if (perfil.equals("User"))
            return Collections.singleton(Perfil.USER);

        return Collections.singleton(Perfil.ADMIN);

    }
}
