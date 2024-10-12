package service;

import DTO.FormatoResponceDTO;
import DTO.MunicipiosDTO;
import DTO.MunicipiosResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface MunicipioService {

    // recursos basicos
    List<MunicipiosResponceDTO> getAll(int index , int size);


    MunicipiosResponceDTO create(MunicipiosDTO municipioDTO);

    MunicipiosResponceDTO update(Long id, MunicipiosDTO municipioDTO);

    void delete(Long id);

    // recursos extras

    List<MunicipiosResponceDTO> findByNome(String nome);

    MunicipiosResponceDTO findById(long id);

    long count();
}
