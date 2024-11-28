package service;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import DTO.TelefoneDTO;
import DTO.TelefoneResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface TelefoneService {

    List<TelefoneResponceDTO> getAll(int page , int size);


    TelefoneResponceDTO create(TelefoneDTO telefoneDTO);

    TelefoneResponceDTO update(Long id, TelefoneDTO telefoneDTO);

    void delete(Long id);

    // recursos extras

    List<TelefoneResponceDTO> findByNumero(String numero);

    TelefoneResponceDTO findById(long id);

    long count();
}
