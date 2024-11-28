package service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;

@ApplicationScoped
public interface UsuarioService {

    List<UsuarioResponceDTO> getAll();

    UsuarioResponceDTO create(UsuarioDTO usuarioDTO, Boolean isAdminRequest);

    UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO);

    void delete(Long id);

    // recursos extras

    UsuarioResponceDTO findById(long id);

    UsuarioResponceDTO findByUsernameAndSenha(String login, String senha, Boolean administrador);
}
