package DTO;

import model.Endereco;
import model.Perfil;
import model.Telefone;
import model.Usuario;

import java.util.List;

public record UsuarioResponceDTO(

        long id,
        String nome,
        String email,
        List<Telefone> telefone,
        List<Endereco> endereco,
        String username,
        String perfil
        
        ) {
        public static UsuarioResponceDTO valueOf(Usuario usuario){

                return new UsuarioResponceDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getTelefone(),
                        usuario.getEndereco(),
                        usuario.getUsername(),
                        usuario.getPerfil().getLabel()
                );
        }

}
