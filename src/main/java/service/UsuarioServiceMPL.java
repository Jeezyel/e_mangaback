package service;

import DTO.*;
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
import model.Municipio;
import model.Perfil;
import model.Telefone;
import model.Usuario;
import repository.EnderecoRepository;
import repository.MunicipioRepository;
import repository.TelefoneRepository;
import repository.UsuarioRepository;
import util.JwtUtils;

@ApplicationScoped
public class UsuarioServiceMPL implements UsuarioService{
    
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    TelefoneService telefoneService;

    @Inject
    EnderecoService enderecoService;

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    JwtUtils jwtUtil;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponceDTO create(UsuarioDTO usuarioDTO) {


        if (usuarioRepository.findByUsername(usuarioDTO.username()) != null) {
            throw new ValidationException("O username informado já existe, informe outro username");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuarioDTO.nome());
        novoUsuario.setEmail(usuarioDTO.email());
        novoUsuario.setUsername(usuarioDTO.username());
        novoUsuario.setSenha(hashService.getHashSenha(usuarioDTO.senha()));
        novoUsuario.setPerfil(Perfil.valueOf(usuarioDTO.perfil()));


        // Persistir os telefones, se houver
        if (usuarioDTO.telefone() != null && !usuarioDTO.telefone().isEmpty()) {
            List<Telefone> telefones = usuarioDTO.telefone();
            telefones.forEach(telefone -> {
                if (telefone.getId() == 0) { // Novo telefone
                    telefoneRepository.persist(telefone);
                }
            });
            novoUsuario.setTelefone(telefones);
        }

        // Persistir os endereços, se houver
        if (usuarioDTO.endereco() != null && !usuarioDTO.endereco().isEmpty()) {
            List<Endereco> enderecos = usuarioDTO.endereco();
            enderecos.forEach(endereco -> {
                if (endereco.getId() == 0) { // Novo endereço
                    enderecoRepository.persist(endereco);
                }
            });
            novoUsuario.setEndereco(enderecos);
        }

        usuarioRepository.persist(novoUsuario);

        return UsuarioResponceDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponceDTO create2(UsuarioDTO usuarioDTO) {


        if (usuarioRepository.findByUsername(usuarioDTO.username()) != null) {
            throw new ValidationException("O username informado já existe, informe outro username");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuarioDTO.nome());
        novoUsuario.setEmail(usuarioDTO.email());
        novoUsuario.setUsername(usuarioDTO.username());
        novoUsuario.setSenha(hashService.getHashSenha(usuarioDTO.senha()));
        novoUsuario.setPerfil(Perfil.valueOf(2));


        // Persistir os telefones, se houver
        if (usuarioDTO.telefone() != null && !usuarioDTO.telefone().isEmpty()) {
            List<Telefone> telefones = usuarioDTO.telefone();
            telefones.forEach(telefone -> {
                if (telefone.getId() == 0) { // Novo telefone
                    telefoneRepository.persist(telefone);
                }
            });
            novoUsuario.setTelefone(telefones);
        }

        // Persistir os endereços, se houver
        if (usuarioDTO.endereco() != null && !usuarioDTO.endereco().isEmpty()) {
            List<Endereco> enderecos = usuarioDTO.endereco();
            enderecos.forEach(endereco -> {
                if (endereco.getId() == 0) { // Novo endereço
                    enderecoRepository.persist(endereco);
                }
            });
            novoUsuario.setEndereco(enderecos);
        }

        usuarioRepository.persist(novoUsuario);

        return UsuarioResponceDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO, String authToken) {

        String authUsername = jwtUtil.extractUsername(authToken);
        Usuario usuarioAdmin = usuarioRepository.findByUsername(authUsername);
        Usuario usuarioAtualizado = usuarioRepository.findById(id);

        if (usuarioAtualizado == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Verificar se o perfil está sendo alterado para ADMIN
        if (usuarioDTO.perfil() != null && Perfil.ADMIN.name().equals(usuarioDTO.perfil())) {
            if (usuarioAdmin == null || usuarioAdmin.getPerfil() != Perfil.ADMIN) {
                throw new ValidationException("Somente administradores podem atualizar o perfil para ADMIN.");
            }
            usuarioAtualizado.setPerfil(Perfil.ADMIN);
        }

        usuarioAtualizado.setNome(usuarioDTO.nome());
        usuarioAtualizado.setEmail(usuarioDTO.email());
        usuarioAtualizado.setUsername(usuarioDTO.username());
        usuarioAtualizado.setSenha(hashService.getHashSenha(usuarioDTO.senha()));

        // Atualizar telefones
        if (usuarioDTO.telefone() != null && !usuarioDTO.telefone().isEmpty()) {
            List<Telefone> telefones = usuarioDTO.telefone();
            telefones.forEach(telefone -> {
                if (telefone.getId() == 0) { // Novo telefone
                    telefoneRepository.persist(telefone);
                }
            });
            usuarioAtualizado.setTelefone(telefones);
        }

        // Atualizar endereços
        if (usuarioDTO.endereco() != null && !usuarioDTO.endereco().isEmpty()) {
            List<Endereco> enderecos = usuarioDTO.endereco();
            enderecos.forEach(endereco -> {
                if (endereco.getId() == 0) { // Novo endereço
                    enderecoRepository.persist(endereco);
                }
            });
            usuarioAtualizado.setEndereco(enderecos);
        }

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

    public UsuarioResponceDTO addEndereco(Long id, EnderecoDTO enderecoDTO) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        usuario.getEndereco().add(enderecoRepository.findByCep(enderecoDTO.cep()));
        usuarioRepository.persist(usuario);
        
        return UsuarioResponceDTO.valueOf(usuario);
    }

    public UsuarioResponceDTO addTelefone(Long id, TelefoneDTO telefoneDTO) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        TelefoneResponceDTO telefoneResponceDTO = telefoneService.create(telefoneDTO);

        List<Telefone> telefones = usuario.getTelefone();

        telefones.add(telefoneRepository.findById(telefoneResponceDTO.idTelefone()));


        usuario.setTelefone(telefones);
        usuarioRepository.persist(usuario);

        return UsuarioResponceDTO.valueOf(usuario);
    }

    public List<Endereco> getEnderecos(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        return usuario.getEndereco();
    }

    public List<Telefone> getTelefones(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        return usuario.getTelefone();
    }

    public Endereco convertEndereco(EnderecoDTO enderecoDTO) {
        
        Municipio municipio = municipioRepository.findByMunicipioAndEstado(
                enderecoDTO.nomeMunicipio(), enderecoDTO.sigla()
        );

        if (municipio == null) {
            throw new IllegalArgumentException(
                    "Município não encontrado: " + enderecoDTO.nomeMunicipio() + " - " + enderecoDTO.sigla()
            );
        }

        Endereco endereco = new Endereco();
        endereco.setCep(enderecoDTO.cep());
        endereco.setLogradouro(enderecoDTO.logradouro());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setMunicipio(municipio);

        return endereco;
    }

    public Telefone convertTelefone(TelefoneDTO telefoneDTO) {
        Telefone telefone = new Telefone();
        telefone.setCodegoDeArea(telefoneDTO.codegoDeArea());
        telefone.setNumero(telefoneDTO.numero());
        return telefone;
    }

    @Override
    public UsuarioResponceDTO userLogin(String userName) {
        return UsuarioResponceDTO.valueOf(usuarioRepository.findByUsername(userName));
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
