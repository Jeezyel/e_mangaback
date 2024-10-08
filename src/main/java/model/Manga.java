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
    private FormatoManga formatos;

/*  @Enumerated(EnumType.STRING)
    private Set<MangaGenre> genres; QUANDO VC USAR O 'Set<> INDICA QUE VC QUER SALVAR UMA COLEÇÃO DE DADO DEQUELE ENUM'
    */

    @Enumerated(EnumType.STRING)
    private Set<MangaGenre> genres;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    @Enumerated(EnumType.STRING)
    private ClassificacaoIndicativa classificacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FormatoManga getFormatos() {
        return formatos;
    }

    public void setFormatos(FormatoManga formatos) {
        this.formatos = formatos;
    }

    public Set<MangaGenre> getGenres() {
        return genres;
    }

    public void setGenres(Set<MangaGenre> genres) {
        this.genres = genres;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public ClassificacaoIndicativa getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoIndicativa classificacao) {
        this.classificacao = classificacao;
    }
}
