package DTO;


import model.Telefone;

public record TelefoneResponceDTO(
    
        long idTelefone,
        String codegoDeArea,
        String numero
) {
    public static TelefoneResponceDTO valueOf(Telefone telefone){

        return new TelefoneResponceDTO(
                telefone.getId(),
                telefone.getCodegoDeArea(),
                telefone.getNumero()
        );
    }
}