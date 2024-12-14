package model;


import jakarta.persistence.*;

@Entity
public class Municipio extends DefaultEntity{

    @Column( nullable = false )
    private String nome;

    @JoinColumn(name = "idEstado")
    @ManyToOne()
    private Estado estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
