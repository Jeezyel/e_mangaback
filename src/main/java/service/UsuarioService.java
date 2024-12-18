package service;

import jakarta.enterprise.context.ApplicationScoped;

import model.Telefone;
import model.Endereco;

import java.util.List;

import DTO.EnderecoDTO;
import DTO.TelefoneDTO;
import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;

@ApplicationScoped
public interface UsuarioService {

    UsuarioResponceDTO create(UsuarioDTO usuarioDTO);

    UsuarioResponceDTO create2(UsuarioDTO usuarioDTO);

    UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO, String authToken);

    void delete(Long id);

    List<UsuarioResponceDTO> getAll(int page , int size);

    long count();

    UsuarioResponceDTO findById(long id);

    UsuarioResponceDTO userLogin(String userName);

    UsuarioResponceDTO findByUsernameAndSenha(String login, String senha);

    UsuarioResponceDTO addEndereco(Long id, EnderecoDTO enderecoDTO);

    UsuarioResponceDTO addTelefone(Long id, TelefoneDTO telefoneDTO);

    Endereco convertEndereco(EnderecoDTO enderecoDTO);

    Telefone convertTelefone(TelefoneDTO telefoneDTO);

    List<Endereco> getEnderecos(Long id);

    List<Telefone> getTelefones(Long id);

}
