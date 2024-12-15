package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.DefaultValue;
import model.Endereco;
import model.Perfil;
import model.Telefone;

import java.util.List;
import java.util.Set;

public record UsuarioDTO(

        @NotBlank(message = "O nome deve ser informdo ")
        String nome,

        @NotBlank (message = "O email deve ser informdo ")
        String email,

        List<Telefone> telefone,

        List<Endereco> endereco,

        @NotBlank (message = "O username deve ser informado ")
        String username,

        @NotBlank (message = "A senha deve ser informada ")
        String senha,

        String perfil

) {}
