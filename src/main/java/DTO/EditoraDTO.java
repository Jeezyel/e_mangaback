package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditoraDTO(
        @NotBlank(message = "A cor deve ser informdo ")
        String cnpj,
        @NotBlank(message = "o nome deve ser informdo ")
        String nome,
        @NotBlank(message = "o Telefone deve ser informdo ")
        String Telefone
) {
}
