package DTO;


import model.Editora;

public record EditoraResponceDTO(
        long idEditora,
        String cnpj,
        String nome,
        String Telefone
) {
    public EditoraResponceDTO(Editora editora){
        this(
                editora.getId(),
                editora.getCnpj(),
                editora.getNome(),
                editora.getTelefone()
        );
    }
}