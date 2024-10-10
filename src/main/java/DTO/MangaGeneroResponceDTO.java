package DTO;

import jakarta.validation.constraints.NotBlank;
import model.MangaGenero;

public record MangaGeneroResponceDTO(
        long idMangaGenero,

        String genero
) {
        public static MangaGeneroResponceDTO valueOf(MangaGenero mangaGenero) {

                return new MangaGeneroResponceDTO(
                        mangaGenero.getId(),
                        mangaGenero.getGenero()
                );
        }
}
