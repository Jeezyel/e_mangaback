package service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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
    public List<UsuarioResponceDTO> getAll(int page , int size) {
        List<Usuario> list = usuarioRepository.findAll().page(page,size).list();
        return list.stream().map(UsuarioResponceDTO::valueOf).toList();
    }

    @Override
    public UsuarioResponceDTO create(UsuarioDTO usuarioDTO) {
        validar(usuarioDTO);


        Usuario entity = new Usuario();

        entity.setNome(usuarioDTO.nome());
        entity.setEmail(usuarioDTO.email());
        entity.setTelefone(telefoneList(usuarioDTO.telefone()));
        entity.setEndereco(enderecolist(usuarioDTO.endereco()));
        entity.setPerfil(Perfil.perfilSet("User"));
        entity.setUsername(usuarioDTO.username());
        entity.setSenha(hashService.getHashSenha(usuarioDTO.senha()));

        usuarioRepository.persist(entity);

        return UsuarioResponceDTO.valueOf(entity);
    }

    @Override
    public UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO) {
        validar(usuarioDTO);

        Usuario entity = usuarioRepository.findById(id);

        entity.setNome(usuarioDTO.nome());
        entity.setEmail(usuarioDTO.email());
        entity.setTelefone(telefoneList(usuarioDTO.telefone()));
        entity.setEndereco(enderecolist(usuarioDTO.endereco()));
        entity.setUsername(usuarioDTO.username());
        entity.setSenha(hashService.getHashSenha(usuarioDTO.senha()));

        usuarioRepository.persist(entity);

        return UsuarioResponceDTO.valueOf(entity);
    }

    @Override
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
    @Transactional
    public UsuarioResponceDTO updateprivilege(String nameAdm, Long idUserUpdate) {
        Usuario userAdm = usuarioRepository.findByUserName(nameAdm);
        Usuario usuario = usuarioRepository.findById(idUserUpdate);

        if (userAdm != null && usuario != null){
            Log.info("ENTRO ONDE DEVERIA");
            if (true)
                usuario.setPerfil(Perfil.perfilSet("Admin"));

        }

        usuarioRepository.persist(usuario);

        return UsuarioResponceDTO.valueOf(usuario);
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

    private void validar(UsuarioDTO usuarioDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }
}
