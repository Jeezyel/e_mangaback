package DTO;

import model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record UsuarioPedidoJuncaoResponceDTO(
        Long id,
        Long usuario,
        List<Manga>produto,
        BigDecimal valortotal,
        FormaDePagamento formaDePagamento,
        Set<Status> status,
        int quantidadeParcela,
        String nome,
        String email,
        List<Telefone> telefone,
        List<Endereco> endereco

) {
        public static UsuarioPedidoJuncaoResponceDTO valueOf(Pedido pedido , Usuario usuario){
                return new UsuarioPedidoJuncaoResponceDTO(
                        pedido.getId(),
                        pedido.getUsuario().getId(),
                        pedido.getProduto(),
                        pedido.getValortotal(),
                        pedido.getFormaDePagamento(),
                        pedido.getStatus(),
                        pedido.getQuantidadeParcela(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getTelefone(),
                        usuario.getEndereco()
                );
        }
}
