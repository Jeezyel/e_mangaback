package DTO;

import jakarta.validation.constraints.NotNull;
import model.FormaDePagamento;

import java.math.BigDecimal;
import java.util.Set;

public record PedidoDTO(
        @NotNull(message = "o Id do usuario deve ser informando")
        Long usuario,

        @NotNull(message = "o Id dos mangar deve ser informando")
        Set<Long>produto,

        @NotNull(message = "O valor total deve ser informado")
        BigDecimal valortotal,

        @NotNull(message = "a forma de pagamento deve ser infomado")
        FormaDePagamento formaDePagamento,

        @NotNull(message = "a quantidade de parcela de ve ser informado")
        int quantidadeParcela

) {
}
