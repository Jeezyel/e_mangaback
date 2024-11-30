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

        @NotBlank(message = "o campo valor deve ser informado")
        Float valor,

        @NotBlank(message = "O campo Formato do Manga deve ser informado.")
        FormatoManga formatoManga,

        @NotBlank(message = "O campo MangaGenero deve ser informado.")
        Set<MangaGenero> mangaGeneros,

        @NotBlank(message = "O campo idioma deve ser informado.")
        Idioma idioma,

        @NotBlank(message = "O campo classificacao deve ser informado.")
        ClassificacaoIndicativa classificacao

        ) {
}
