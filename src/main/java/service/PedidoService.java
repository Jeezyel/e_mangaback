package service;

import DTO.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface PedidoService {

    List<PedidoResponceDTO> getAll(int page , int size);

    UsuarioPedidoJuncaoResponceDTO create(UsuarioPedidoJuncaoDTO usuarioPedidoJuncaoDTO);

    //PedidoResponceDTO update(Long id, PedidoDTO pedidoDTO);

    void delete(Long id);

    // recursos extras

    PedidoResponceDTO findByUser(Long idUsuario);

    List<PedidoResponceDTO> History(String userName);

    PedidoResponceDTO updateStatus(Long id, String status);

    PedidoResponceDTO findById(long id);

    long count();
}
