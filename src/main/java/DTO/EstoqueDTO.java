package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstoqueDTO(

        @NotBlank(message = "a quantidade do item deve ser informado. ")
        int quantidade,

        @NotNull(message = "o manga deve ser informado")
        Long manga,

        @NotBlank(message = "o valorUnitario do item deve ser informado. ")
        Float valorUnitario
) {
}
