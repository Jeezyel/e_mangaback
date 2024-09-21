package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record EstadoDTO(
        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,
        @NotBlank(message = "O campo sigla deve ser informado.")
        @Size(min = 2, max = 2, message = "O campo sigla deve possuir 2 caracteres.")
        String sigla

) {
}