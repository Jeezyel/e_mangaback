package DTO;

import jakarta.validation.constraints.NotBlank;

public record IdiomaDTO(
        @NotBlank(message = "O campo Idioma deve ser informado.")
        String Idioma,
        @NotBlank(message = "O campo Sigla deve ser informado.")
        String Sigla


        ) {
}