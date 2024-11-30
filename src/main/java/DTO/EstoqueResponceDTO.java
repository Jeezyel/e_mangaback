package DTO;

import model.Estoque;

public record EstoqueResponceDTO(

        long idEstoque,

        int quantidade,

        Long manga,

        Float valorUnitario
) {
        public static EstoqueResponceDTO valueOf(Estoque estoque){
                return new EstoqueResponceDTO(
                  estoque.getId(),
                  estoque.getQuantidade(),
                  estoque.getManga().getId(),
                  estoque.getValorUnitario()
                );
        }
}
