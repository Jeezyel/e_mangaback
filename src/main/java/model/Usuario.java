package model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Usuario extends DefaultEntity{

    private String nome;
    private String email;

    @Enumerated(EnumType.STRING)
    private Set<Perfil> perfil;

    private String senha;

    @Column(unique = true)
    private String username;
    
    @Column(name = "imagem")
    private String nomeImagem;

    @OneToMany
    private List<Telefone> telefone;
    
    @OneToMany
    private List<Endereco> endereco;
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public Set<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(Set<Perfil> perfil) {
        this.perfil = perfil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
