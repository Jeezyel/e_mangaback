package service;

import DTO.EstadoDTO;
import DTO.EstadoResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface EstadoService {

    // recursos basicos

    List<EstadoResponceDTO> getAll(int index , int size);

    EstadoResponceDTO create(EstadoDTO estadosDTO);

    EstadoResponceDTO update(Long id, EstadoDTO estadosDTO);

    void delete(Long id);

    // recursos extras

    List<EstadoResponceDTO> findByNome(String nome);

    EstadoResponceDTO findById(long id);

    long count();

}
