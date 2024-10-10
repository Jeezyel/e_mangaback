package service;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;

import java.util.List;

public interface MangaService {
    List<MangaResponceDTO> getAll(int page , int size);


    MangaResponceDTO create(MangaDTO mangaDTO);

    MangaResponceDTO update(Long id, MangaDTO mangaDTO);

    void delete(Long id);

    // recursos extras

    List<MangaResponceDTO> findByNome(String nome);

    MangaResponceDTO findById(long id);

    long count();
}
