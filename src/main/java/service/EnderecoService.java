package service;

import DTO.EnderecoDTO;
import DTO.EnderecoResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public interface EnderecoService {

    // recursos basicos
    List<EnderecoResponceDTO> getAll(int page , int size);


    EnderecoResponceDTO create(EnderecoDTO enderecoDTO);

    EnderecoResponceDTO update(Long id, EnderecoDTO enderecoDTO);

    EnderecoResponceDTO enderecoCep(String cep) throws Exception;

    void delete(Long id);

    // recursos extras

    List<EnderecoResponceDTO> findByCep(String cep);

    EnderecoResponceDTO findById(long id);

    long count();
}
