package model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class MangaGenero extends DefaultEntity{

    private String genero;

    @ManyToMany(mappedBy = "genero")
    private Set<Manga> manga;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
