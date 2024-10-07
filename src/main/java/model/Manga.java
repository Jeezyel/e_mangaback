package model;

import java.util.Set;
import jakarta.persistence.*;
import jakarta.ws.rs.FormParam;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Manga extends DefaultEntity {

    private String nome;

    @Enumerated(EnumType.STRING)
    private Set<FormatoManga> formatos;

    @Enumerated(EnumType.STRING)
    private Set<MangaGenre> genres;

    @Enumerated(EnumType.STRING)
    private Set<Idioma> idioma;

    @Enumerated(EnumType.STRING)
    private Set<ClassificacaoIndicativa> classificacao;
}
