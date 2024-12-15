package DTO;

import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(

        @NotBlank (message = "O codegoDeArea deve ser informado ")
        String codegoDeArea,
        @NotBlank(message = "O numero deve ser informado ")
        String numero

) {
}
