package DTO;

import jakarta.validation.constraints.NotBlank;
import model.Endereco;
import model.Telefone;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "O nome deve ser informdo ")
        String nome,

        @NotBlank (message = "O email deve ser informdo ")
        String email,

        List<Long> telefone,

        @NotBlank (message = "O endereco deve ser informdo ")
        List<Long> endereco,

        @NotBlank (message = "deve informar se o usuario e administrador ")
        Boolean administrador,

        @NotBlank (message = "O username deve ser informado ")
        String username,

        @NotBlank (message = "A senha deve ser informada ")
        String senha
        ) {
}
