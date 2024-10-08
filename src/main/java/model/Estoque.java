package model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Estoque extends DefaultEntity{

    private int quantidade;

    @OneToOne
    private Manga manga;

    private Float valorUnitario;


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public Float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
