package service;

import DTO.IdiomaDTO;
import DTO.IdiomaResposceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface IdiomaService {
    List<IdiomaResposceDTO> getAll(int page , int size);


    IdiomaResposceDTO create(IdiomaDTO idiomaDTO);

    IdiomaResposceDTO update(Long id, IdiomaDTO idiomaDTO);

    void delete(Long id);

    // recursos extras

    List<IdiomaResposceDTO> search(int page , int size, String idioma);

    List<IdiomaResposceDTO> findByIdioma(String idioma);

    IdiomaResposceDTO findById(long id);

    long count();
}
