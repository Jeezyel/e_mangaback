package DTO;

import jakarta.validation.constraints.NotBlank;
import model.Idioma;

public record IdiomaResposceDTO(
        long idIdioma,

        String idiomas,

        String sigla


        ) {
        public static IdiomaResposceDTO valueOf(Idioma idioma) {
                return new IdiomaResposceDTO(
                        idioma.getId(),
                        idioma.getIdioma(),
                        idioma.getSigla()
                );
        }
}
