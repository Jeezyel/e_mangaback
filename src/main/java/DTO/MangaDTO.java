package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

import model.Manga;
import model.Editora;
import model.FormatoManga;
import model.MangaGenero;
import model.ClassificacaoIndicativa;
import model.Idioma;

public record MangaDTO(

        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,

        @NotNull(message = "o campo valor deve ser informado")
        BigDecimal valor,

        @NotNull(message = "o campo editora deve ser informado")
        Long idEditora,

        @NotNull(message = "O campo formato do mangá deve ser informado.")
        Long idFormato,

        @NotNull(message = "O campo gênero do mangá deve ser informado.")
        Set<Long> idMangaGenero,

        @NotNull(message = "O campo idioma deve ser informado.")
        Long idIdioma,

        @NotNull(message = "O campo classificação do mangá deve ser informado.")
        Integer id,

        @NotNull(message = "O estoque do mangá deve ser informado.")
        Integer estoque

        ) {
}
