package service;

import DTO.*;
import jakarta.enterprise.context.ApplicationScoped;
import model.Endereco;
import model.Telefone;

import java.util.List;

@ApplicationScoped
public interface AdminService {

    UsuarioResponceDTO create(UsuarioDTO usuarioDTO);

    UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO);

    void delete(Long id);

    List<UsuarioResponceDTO> getAll(int page , int size);

    long count();

    UsuarioResponceDTO findById(long id);

    UsuarioResponceDTO findByUsernameAndSenha(String login, String senha);

    UsuarioResponceDTO addEndereco(Long id, EnderecoDTO enderecoDTO);

    UsuarioResponceDTO addTelefone(Long id, TelefoneDTO telefoneDTO);

    EnderecoResponceDTO convertEndereco(EnderecoDTO enderecoDTO);

    TelefoneResponceDTO convertTelefone(TelefoneDTO telefoneDTO);

    List<EnderecoResponceDTO> getEnderecos(Long id);

    List<TelefoneResponceDTO> getTelefones(Long id);

}
