package service;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface MangaService {
    List<MangaResponceDTO> getAll(int page , int size);


    MangaResponceDTO create(MangaDTO mangaDTO);

    MangaResponceDTO update(Long id, MangaDTO mangaDTO);

    void delete(Long id);

    // recursos extras

    List<MangaResponceDTO> search(int page , int size, String nome);

    List<MangaResponceDTO> findByNome(String nome);

    MangaResponceDTO findById(long id);

    long count();
}
