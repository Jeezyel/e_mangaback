package service;



import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface UsuarioService {

    List<UsuarioResponceDTO> getAll();

    UsuarioResponceDTO create(UsuarioDTO usuarioDTO);

    UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO);

    void delete(Long id);

    // recursos extras

    UsuarioResponceDTO findById(long id);

    UsuarioResponceDTO findByLoginAndSenha(String login, String senha);
}
