package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

import model.Endereco;
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
    public List<UsuarioResponceDTO> getAll() {
        List<Usuario> list = usuarioRepository.listAll();
        return list.stream().map(UsuarioResponceDTO::valueOf).toList();
    }

    @Override
    public UsuarioResponceDTO create(UsuarioDTO usuarioDTO, Boolean isAdminRequest) {
        
        // Por padrão, todos os usuários são clientes
        boolean isAdmin = usuarioDTO.administrador() != null ? usuarioDTO.administrador() : false;

        // Verifica se a requisição tem permissão para criar administradores
        if (isAdmin && !isAdminRequest){
            throw new RuntimeException("Somente administradores podem criar novos administradores.");
        }

        Usuario entity = new Usuario();
        entity.setNome(usuarioDTO.nome());
        entity.setEmail(usuarioDTO.email());
        entity.setTelefone(telefoneList(usuarioDTO.telefone()));
        entity.setEndereco(enderecolist(usuarioDTO.endereco()));
        entity.setAdministrador(isAdmin);
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
        entity.setAdministrador(usuarioDTO.administrador());
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

    private void validar(UsuarioDTO usuarioDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }

    private List<Telefone> telefoneList(List<Long> idstelefones){

        List<Telefone> listTelefone = null;
        for (int i = 0; i < idstelefones.size(); i++) {
            listTelefone.add(telefoneRepository.findById(idstelefones.get(i)));
        }
        return listTelefone;
    }

    private List<Endereco> enderecolist(List<Long> idsEnderecos){

        List<Endereco> listEndereco = null;
        for (int i = 0; i < idsEnderecos.size(); i++) {
            listEndereco.add(enderecoRepository.findById(idsEnderecos.get(i)));
        }
        return listEndereco;
    }

    @Override
    public UsuarioResponceDTO findByUsernameAndSenha(String login, String senha, Boolean administrador) {
        Usuario usuario = usuarioRepository.findByUsernameAndSenha(login, senha, administrador);
        return usuario != null ? UsuarioResponceDTO.valueOf(usuario) : null;
    }
}
