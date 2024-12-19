package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.Endereco;
import model.FormaDePagamento;
import model.Telefone;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record UsuarioPedidoDTO(
        @NotBlank(message = "O nome deve ser informdo ")
        String nome,

        @NotBlank (message = "O email deve ser informdo ")
        String email,

        List<Telefone> telefone,

        List<Endereco> endereco


    ) {
    }

