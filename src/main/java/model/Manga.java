package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Manga extends DefaultEntity {

    @NotNull
    @Size(max = 100)
    private String nome;

    private String nomeImagem;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "idEditora", nullable = false) // Relacionamento com Editora
    private Editora editora;

    @ManyToMany
    @JoinTable(
        name = "manga_genero", 
        joinColumns = @JoinColumn(name = "idManga"), 
        inverseJoinColumns = @JoinColumn(name = "idGenero")
    )
    private Set<MangaGenero> genero;

    @ManyToOne
    @JoinColumn(name = "idFormato", nullable = false)
    private FormatoManga formato;

    @ManyToOne
    @JoinColumn(name = "idIdioma", nullable = false)
    private Idioma idioma;

    @Enumerated(EnumType.ORDINAL) // Use ORDINAL se o banco espera números
    @Column(name = "classificacaoindicativa", nullable = false)
    private ClassificacaoIndicativa classificacaoIndicativa;
    
    @NotNull
    @Column(nullable = false)
    private Integer estoque;

    // Getters e Setters


    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Set<MangaGenero> getGenero() {
        return genero;
    }

    public void setGenero(Set<MangaGenero> genero) {
        this.genero = genero;
    }

    public FormatoManga getFormato() {
        return formato;
    }

    public void setFormato(FormatoManga formato) {
        this.formato = formato;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public ClassificacaoIndicativa getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
    
}