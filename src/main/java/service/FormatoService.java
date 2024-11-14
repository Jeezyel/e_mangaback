package service;

import DTO.FormatoDTO;
import DTO.FormatoResponceDTO;
import DTO.MangaResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface FormatoService {
    List<FormatoResponceDTO> getAll(int page , int size);


    FormatoResponceDTO create(FormatoDTO formato);

    FormatoResponceDTO update(Long id, FormatoDTO formato);

    void delete(Long id);

    // recursos extras

    List<FormatoResponceDTO> search(int page , int size, String formato);


    List<FormatoResponceDTO> findByFormato(String formato);

    FormatoResponceDTO findById(long id);

    long count();
}
