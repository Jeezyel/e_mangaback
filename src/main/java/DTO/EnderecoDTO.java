package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotBlank(message = "O CEP deve ser informdo ")
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        @NotNull(message = "o Id do Municipios deve ser informado")
        long idMunicipio
) {
}