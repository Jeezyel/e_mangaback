package service;

import DTO.EstoqueDTO;
import DTO.EstoqueResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface EstoqueService {

    List<EstoqueResponceDTO> getAll(int page , int size);

    EstoqueResponceDTO create(EstoqueDTO estoqueDTO);

    EstoqueResponceDTO update(Long id, EstoqueDTO estoqueDTO);

    void delete(Long id);


    // recursos extras

    EstoqueResponceDTO findById(long id);

    long count();
}
