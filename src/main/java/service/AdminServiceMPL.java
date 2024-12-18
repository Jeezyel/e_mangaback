package service;

import DTO.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import model.*;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import repository.EnderecoRepository;
import repository.MunicipioRepository;
import repository.TelefoneRepository;
import repository.UsuarioRepository;
import resouce.UsuarioResouce;
import util.JwtUtils;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class AdminServiceMPL implements AdminService{

    private static final Logger LOG = Logger.getLogger(UsuarioResouce.class);

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    JsonWebToken jwt;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponceDTO create(UsuarioDTO usuarioDTO) throws ConstraintViolationException {


        String usuarioo = jwt.getSubject();
        LOG.info("O QUE FOI COLETADO DO TOKEN De service: " + usuarioo);
        Usuario usuarioAdmin = usuarioRepository.findByUsername(usuarioo);

        if (usuarioDTO.perfil() != null && Perfil.ADMIN.name().equals(usuarioDTO.perfil())) {
            if (usuarioAdmin == null || usuarioAdmin.getPerfil() != Perfil.ADMIN) {
                throw new ValidationException("Somente administradores podem criar outros administradores.");
            }
        }

        if (usuarioRepository.findByUsername(usuarioDTO.username()) != null) {
            throw new ValidationException("O username informado já existe, informe outro username");
        }

        Usuario entity = new Usuario();
        entity.setNome(usuarioDTO.nome());
        entity.setEmail(usuarioDTO.email());
        entity.setUsername(usuarioDTO.username());
        entity.setSenha(hashService.getHashSenha(usuarioDTO.senha()));
        entity.setPerfil(Perfil.valueOf(usuarioDTO.perfil()));


        // Persistir os telefones, se houver
        if (usuarioDTO.telefone() != null && !usuarioDTO.telefone().isEmpty()) {
            List<Telefone> telefones = usuarioDTO.telefone();
            telefones.forEach(telefone -> {
                if (telefone.getId() == 0) { // Novo telefone
                    telefoneRepository.persist(telefone);
                }
            });
            entity.setTelefone(telefones);
        }

        // Persistir os endereços, se houver
        if (usuarioDTO.endereco() != null && !usuarioDTO.endereco().isEmpty()) {
            List<Endereco> enderecos = usuarioDTO.endereco();
            enderecos.forEach(endereco -> {
                if (endereco.getId() == 0) { // Novo endereço
                    enderecoRepository.persist(endereco);
                }
            });
            entity.setEndereco(enderecos);
        }

        usuarioRepository.persist(entity);

        return UsuarioResponceDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public UsuarioResponceDTO update(Long id, UsuarioDTO usuarioDTO) throws ConstraintViolationException {

        Usuario entity = usuarioRepository.findById(id);




        entity.setNome(usuarioDTO.nome());
        entity.setEmail(usuarioDTO.email());
        entity.setUsername(usuarioDTO.username());
        entity.setSenha(hashService.getHashSenha(usuarioDTO.senha()));
        entity.setPerfil(Perfil.valueOf(usuarioDTO.perfil()));

        // Atualizar telefones
        if (usuarioDTO.telefone() != null && !usuarioDTO.telefone().isEmpty()) {
            List<Telefone> telefones = usuarioDTO.telefone();
            telefones.forEach(telefone -> {
                if (telefone.getId() == 0) { // Novo telefone
                    telefoneRepository.persist(telefone);
                }
            });
            entity.setTelefone(telefones);
        }

        // Atualizar endereços
        if (usuarioDTO.endereco() != null && !usuarioDTO.endereco().isEmpty()) {
            List<Endereco> enderecos = usuarioDTO.endereco();
            enderecos.forEach(endereco -> {
                if (endereco.getId() == 0) { // Novo endereço
                    enderecoRepository.persist(endereco);
                }
            });
            entity.setEndereco(enderecos);
        }

        usuarioRepository.persist(entity);

        return UsuarioResponceDTO.valueOf(entity);
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



    public UsuarioResponceDTO addEndereco(Long id, EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        //acho que vai dar problema mas vamos deixa
        usuario.getEndereco().add(enderecoRepository.findByCep(enderecoDTO.cep()));
        usuarioRepository.persist(usuario);

        return UsuarioResponceDTO.valueOf(usuario);
    }

    public UsuarioResponceDTO addTelefone(Long id, TelefoneDTO telefoneDTO ) throws ConstraintViolationException {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        usuario.getTelefone().add(telefoneRepository.findByNumeroPanache(telefoneDTO.numero()).firstResult());
        usuarioRepository.persist(usuario);

        return UsuarioResponceDTO.valueOf(usuario);
    }

    public List<EnderecoResponceDTO> getEnderecos(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        return usuario.getEndereco().stream().map(EnderecoResponceDTO::valueOf).toList();
    }

    public List<TelefoneResponceDTO> getTelefones(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("User not found");
        }
        return usuario.getTelefone().stream().map(TelefoneResponceDTO::valueOf).toList();
    }

    public EnderecoResponceDTO convertEndereco(EnderecoDTO enderecoDTO) {

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

        return EnderecoResponceDTO.valueOf(endereco);
    }

    public TelefoneResponceDTO convertTelefone(TelefoneDTO telefoneDTO) {
        Telefone telefone = new Telefone();
        telefone.setCodegoDeArea(telefoneDTO.codegoDeArea());
        telefone.setNumero(telefoneDTO.numero());
        return TelefoneResponceDTO.valueOf(telefone);
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

    private void validar(EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }

    private void validar(TelefoneDTO telefoneDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<TelefoneDTO>> violations = validator.validate(telefoneDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }

    private void validar(UsuarioDTO usuarioDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }
}
