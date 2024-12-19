package DTO;

import model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record PedidoResponceDTO(
        Long id,
        Long usuario,
        List<Manga> produto,
        BigDecimal valortotal,
        FormaDePagamento formaDePagamento,
        Set<Status> status,
        int quantidadeParcela

) {
        public static PedidoResponceDTO valueOf(Pedido pedido){
                return new PedidoResponceDTO(
                        pedido.getId(),
                        pedido.getUsuario().getId(),
                        pedido.getProduto(),
                        pedido.getValortotal(),
                        pedido.getFormaDePagamento(),
                        pedido.getStatus(),
                        pedido.getQuantidadeParcela()
                );
        }
}
