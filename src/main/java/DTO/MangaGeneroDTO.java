package DTO;

import jakarta.validation.constraints.NotBlank;

public record MangaGeneroDTO(
        @NotBlank(message = "O campo genero deve ser informado.")
        String genero
) {
}
