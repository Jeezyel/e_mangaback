package DTO;

import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(
        @NotBlank (message = "O codegoDeArea deve ser informdo ")
        String codegoDeArea,
        @NotBlank(message = "O numero deve ser informdo ")
        String numero
) {
}
