package DTO;

import jakarta.validation.constraints.NotBlank;
import model.Endereco;
import model.Telefone;
import model.Usuario;

import java.util.List;

public record UsuarioPedidoResponceDTO(
        String nome,

        String email,

        List<Telefone> telefone,

        List<Endereco> endereco


    ) {
    public static UsuarioPedidoResponceDTO valueOf(Usuario usuario){

        return new UsuarioPedidoResponceDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getEndereco()
        );
    }
    }

