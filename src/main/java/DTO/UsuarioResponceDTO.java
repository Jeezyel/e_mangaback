package DTO;

import model.Endereco;
import model.Perfil;
import model.Telefone;
import model.Usuario;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UsuarioResponceDTO(
        long id,

        String nome,

        String email,

        List<Telefone> telefone,

        List<Endereco> endereco,

        Set<Perfil> perfil,
        
        String username,

        String senha
        
        ) {
        public static UsuarioResponceDTO valueOf(Usuario usuario){

                return new UsuarioResponceDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getTelefone(),
                        usuario.getEndereco(),
                        usuario.getPerfil(),
                        usuario.getUsername(),
                        usuario.getSenha()
                );
        }
}
