package service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;

@ApplicationScoped
public interface UsuarioService {

    UsuarioResponceDTO create(UsuarioDTO usuarioDTO);

    UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO);

    void delete(Long id);

    List<UsuarioResponceDTO> getAll(int page , int size);

    long count();

    UsuarioResponceDTO findById(long id);

    UsuarioResponceDTO findByUsernameAndSenha(String login, String senha);

}
