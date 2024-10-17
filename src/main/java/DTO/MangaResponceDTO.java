package DTO;

import model.*;

import java.util.Set;

public record MangaResponceDTO(
        long idManga,

        String nome,

        FormatoManga formatoManga,

        Set<MangaGenero> mangaGenero,

        Idioma idioma,

        ClassificacaoIndicativa classificacao
) {
   public static MangaResponceDTO valueOf(Manga manga) {
       return new MangaResponceDTO(
               manga.getId(),
               manga.getNome(),
               manga.getFormatos(),
               manga.getGeneros(),
               manga.getIdioma(),
               manga.getClassificacao()
       );
   }
}
