package service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;

@ApplicationScoped
public interface UsuarioService {

    List<UsuarioResponceDTO> getAll(int page , int size);

    UsuarioResponceDTO create(UsuarioDTO usuarioDTO);

    UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO);

    void delete(Long id);

    // recursos extras

    UsuarioResponceDTO findById(long id);

    UsuarioResponceDTO findByUsernameAndSenha(String login, String senha);

    UsuarioResponceDTO updateprivilege (String nameAdm , Long idUserUpdate);

    long count();

}
