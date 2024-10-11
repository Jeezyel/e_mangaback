package DTO;

import model.*;

import java.util.Set;

public record MangaResponceDTO(
        long idManga,

        String nome,

        FormatoManga FormatoManga,

        Set<MangaGenero> MangaGenero,

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