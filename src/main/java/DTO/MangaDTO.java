package DTO;

import jakarta.validation.constraints.NotBlank;
import model.ClassificacaoIndicativa;
import model.FormatoManga;
import model.Idioma;
import model.MangaGenero;

import java.util.Set;

public record MangaDTO(
        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,

        @NotBlank(message = "O campo Formato do Manga deve ser informado.")
        FormatoManga FormatoManga,

        @NotBlank(message = "O campo MangaGenero deve ser informado.")
        Set<MangaGenero> MangaGeneros,

        @NotBlank(message = "O campo idioma deve ser informado.")
        Idioma idioma,

        @NotBlank(message = "O campo classificacao deve ser informado.")
        ClassificacaoIndicativa classificacao

        ) {
}
