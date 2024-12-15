package service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import model.Endereco;
import model.Perfil;
import model.Telefone;
import model.Usuario;
import DTO.EditoraDTO;
import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;
import repository.EnderecoRepository;
import repository.TelefoneRepository;
import repository.UsuarioRepository;

@ApplicationScoped
public class UsuarioServiceMPL implements UsuarioService{
    
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponceDTO create(UsuarioDTO usuarioDTO) {

        if (usuarioRepository.findByUserName(usuarioDTO.username()) != null) {
            throw new ValidationException("O username informado já existe, informe outro username");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuarioDTO.nome());
        novoUsuario.setEmail(usuarioDTO.email());
        novoUsuario.setTelefone(telefoneList(usuarioDTO.telefone()));
        novoUsuario.setEndereco(enderecolist(usuarioDTO.endereco()));
        novoUsuario.setUsername(usuarioDTO.username());
        novoUsuario.setSenha(hashService.getHashSenha(usuarioDTO.senha()));
        novoUsuario.setPerfil(Perfil.valueOf(usuarioDTO.idPerfil()));

        usuarioRepository.persist(novoUsuario);

        return UsuarioResponceDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO) {

        Usuario usuarioAtualizado = usuarioRepository.findById(id);

        usuarioAtualizado.setNome(usuarioDTO.nome());
        usuarioAtualizado.setEmail(usuarioDTO.email());
        usuarioAtualizado.setTelefone(telefoneList(usuarioDTO.telefone()));
        usuarioAtualizado.setEndereco(enderecolist(usuarioDTO.endereco()));
        usuarioAtualizado.setUsername(usuarioDTO.username());
        usuarioAtualizado.setSenha(hashService.getHashSenha(usuarioDTO.senha()));

        usuarioRepository.persist(usuarioAtualizado);

        return UsuarioResponceDTO.valueOf(usuarioAtualizado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponceDTO findById(long id) {

        return UsuarioResponceDTO.valueOf(usuarioRepository.findById(id));
    }

    private List<Telefone> telefoneList(List<Long> idstelefones){

        List<Telefone> listTelefone = new ArrayList<>();
        if (idstelefones != null) {
            for (Long id : idstelefones) {
                listTelefone.add(telefoneRepository.findById(id));
            }            
        }
        return listTelefone;
    }

    private List<Endereco> enderecolist(List<Long> idsEnderecos){

        List<Endereco> listEndereco = new ArrayList<>();
        if (idsEnderecos != null){
            for (Long id : idsEnderecos) {
                listEndereco.add(enderecoRepository.findById(id));
            }    
        }
        return listEndereco;
    }

    @Override
    public UsuarioResponceDTO findByUsernameAndSenha(String username, String senha) {
        if (username == null || senha == null )
            return null;

        // Use o repositório para realizar a busca
        Usuario usuario = usuarioRepository.findByUsernameAndSenha(username, senha);
        return usuario != null ? UsuarioResponceDTO.valueOf(usuario) : null;
    }

    @Override
    public List<UsuarioResponceDTO> getAll(int page , int size) {
        List<Usuario> list = usuarioRepository.findAll().page(page,size).list();
        return list.stream().map(UsuarioResponceDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

}
