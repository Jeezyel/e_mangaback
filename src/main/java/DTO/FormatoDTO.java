package DTO;

import jakarta.validation.constraints.NotBlank;
import model.ClassificacaoIndicativa;

public record FormatoDTO(
        @NotBlank(message = "O campo formato deve ser informado.")
        String formato


        ) {
}
