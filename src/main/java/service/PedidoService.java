package service;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import DTO.PedidoDTO;
import DTO.PedidoResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.Status;
import model.Usuario;

import java.util.List;

@ApplicationScoped
public interface PedidoService {

    List<PedidoResponceDTO> getAll(int page , int size);

    PedidoResponceDTO create(PedidoDTO pedidoDTO);

    PedidoResponceDTO update(Long id, PedidoDTO pedidoDTO);

    void delete(Long id);

    // recursos extras

    PedidoResponceDTO findByUser(Long idUsuario);

    PedidoResponceDTO updateStatus(Long id, String status);

    PedidoResponceDTO findById(long id);

    long count();
}
