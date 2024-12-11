package DTO;

import model.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public record MangaResponceDTO(

        long idManga,
        String nome,
        BigDecimal valor,
        String editora,
        Set<String> genero,
        String formato,
        String idioma,
        String classificacaoIndicativa,
        Integer estoque

) {
   public static MangaResponceDTO valueOf(Manga manga) { 

       return new MangaResponceDTO(
               manga.getId(),
               manga.getNome(),
               manga.getValor(),
               manga.getEditora().getNome(),
               manga.getGenero().stream()
                        .map(MangaGenero::getGenero)
                        .collect(Collectors.toSet()),
               manga.getFormato().getFormato(),
               manga.getIdioma().getSigla(),
               manga.getClassificacaoIndicativa().getLabel(),
               manga.getEstoque()
       );
   }
}
