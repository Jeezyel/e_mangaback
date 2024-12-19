package DTO;


import model.*;

import java.math.BigDecimal;
import java.util.List;

public record UsuarioPedidoJuncaoDTO(

        Long id,
        List<Long>produto,
        BigDecimal valortotal,
        FormaDePagamento formaDePagamento,
        int quantidadeParcela,
        String nome,
        String email,
        List<Telefone> telefone,
        List<Endereco> endereco


    ) {
    }

