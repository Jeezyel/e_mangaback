package service;

import DTO.MangaGeneroDTO;
import DTO.MangaGeneroResponceDTO;

import java.util.List;

public interface MangaGeneroService {
    List<MangaGeneroResponceDTO> getAll(int page , int size);


    MangaGeneroResponceDTO create(MangaGeneroDTO mangaGeneroDTO);

    MangaGeneroResponceDTO update(Long id, MangaGeneroDTO mangaGeneroDTO);

    void delete(Long id);

    // recursos extras

    List<MangaGeneroResponceDTO> findByGenero(String genero);

    MangaGeneroResponceDTO findById(long id);

    long count();
}
