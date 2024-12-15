package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotBlank(message = "O CEP deve ser informdo ")
        String cep,
        String logradouro,
        String complemento,
        String bairro,

        @NotBlank(message = "O nome do município deve ser informado")
        String nomeMunicipio, 

        @NotBlank(message = "A sigla do estado deve ser informada")
        String sigla 
) {}