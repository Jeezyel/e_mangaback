package DTO;


import jakarta.validation.constraints.NotBlank;
import model.Telefone;

public record TelefoneResponceDTO(
        long idTelefone,
        String codegoDeArea,
        String numero
) {
    public TelefoneResponceDTO(Telefone telefone){
        this(
                telefone.getId(),
                telefone.getCodegoDeArea(),
                telefone.getNumero()
        );
    }
}