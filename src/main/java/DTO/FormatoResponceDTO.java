package DTO;

import jakarta.validation.constraints.NotBlank;
import model.FormatoManga;

public record FormatoResponceDTO(
        long idFormato,

        String formato


        ) {
        public static FormatoResponceDTO valueOf(FormatoManga formato) {
                return new FormatoResponceDTO(
                        formato.getId(),
                        formato.getFormato()
                );
        }
}
