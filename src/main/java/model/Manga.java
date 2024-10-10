package model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
public class Manga extends DefaultEntity {

    private String nome;

    @ManyToOne
    private FormatoManga formatos;

/*  @Enumerated(EnumType.STRING)
    private Set<MangaGenre> genres; QUANDO VC USAR O 'Set<> INDICA QUE VC QUER SALVAR UMA COLEÇÃO DE DADO DEQUELE ENUM'
    */

    @ManyToMany
    @JoinTable(
            name = "manga_genero", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "manga_id"), // Chave estrangeira para Manga
            inverseJoinColumns = @JoinColumn(name = "genero_id") // Chave estrangeira para Genero
    )
     private Set<MangaGenero> generos;

    @ManyToOne
    private Idioma idioma;

    @Enumerated(EnumType.STRING)
    private ClassificacaoIndicativa classificacao; // o unico que vai continuar um enum

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

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Set<MangaGenero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<MangaGenero> generos) {
        this.generos = generos;
    }

    public ClassificacaoIndicativa getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoIndicativa classificacao) {
        this.classificacao = classificacao;
    }
}
