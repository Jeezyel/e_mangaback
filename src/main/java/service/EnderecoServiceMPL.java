package service;

import DTO.EnderecoDTO;
import DTO.EnderecoResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.Endereco;
import model.ViaCep;
import org.jboss.logging.Logger;
import repository.EnderecoRepository;
import repository.MunicipioRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoServiceMPL implements EnderecoService{

    private static final Logger LOG = Logger.getLogger(EnderecoServiceMPL.class);

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    ViacepService viacepService;

    @Inject
    Validator validator;

    @Override
    public List<EnderecoResponceDTO> getAll(int page , int size) {

        List<Endereco> list = enderecoRepository.findAll().page(page,size).list();
        return list.stream().map(EnderecoResponceDTO::valueOf).collect(Collectors.toList());

    }

    @Override
    public EnderecoResponceDTO create(EnderecoDTO enderecoDTO) {
        validar(enderecoDTO);

        Endereco entity = new Endereco();
        entity.setCep(enderecoDTO.cep());
        entity.setLogradouro(enderecoDTO.logradouro());
        entity.setComplemento(enderecoDTO.complemento());
        entity.setBairro(enderecoDTO.bairro());
        entity.setMunicipio(municipioRepository.findByName(enderecoDTO.nomeMunicipio()));

        enderecoRepository.persist(entity);

        return EnderecoResponceDTO.valueOf(entity);


    }

    @Override
    public EnderecoResponceDTO update(Long id, EnderecoDTO enderecoDTO) {
        validar(enderecoDTO);

        Endereco entity = enderecoRepository.findById(id);


        entity.setCep(enderecoDTO.cep());
        entity.setLogradouro(enderecoDTO.logradouro());
        entity.setComplemento(enderecoDTO.complemento());
        entity.setBairro(enderecoDTO.bairro());
        entity.setMunicipio(municipioRepository.findByName(enderecoDTO.nomeMunicipio()));

        enderecoRepository.persist(entity);

        return EnderecoResponceDTO.valueOf(entity);
    }

    @Override
    public EnderecoResponceDTO enderecoCep(String cep) throws Exception {

        ViaCep viaCep = viacepService.ViaCep(cep);
        Endereco endereco = new Endereco();

        endereco.setCep(viaCep.getCep());
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setComplemento(viaCep.getComplemento());
        endereco.setBairro(viaCep.getBairro());
        endereco.setMunicipio(municipioRepository.findByName(viaCep.getLocalidade()));

        enderecoRepository.persist(endereco);


        return EnderecoResponceDTO.valueOf(endereco);
    }


    protected Endereco enderecoViaCep(String cep) throws Exception {
        LOG.info("pegando o cep via api");
        ViaCep viaCep = viacepService.ViaCep(cep);
        Endereco endereco = new Endereco();

        LOG.info("construindo o endereco com os dados da api");
        endereco.setCep(viaCep.getCep());
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setComplemento(viaCep.getComplemento());
        endereco.setBairro(viaCep.getBairro());
        endereco.setMunicipio(municipioRepository.findByName(viaCep.getLocalidade()));

        LOG.info("salvando os dados");
        enderecoRepository.persist(endereco);

        LOG.info("retornando o endereco");
        return endereco;
    }

    @Override
    public EnderecoResponceDTO findById(long id) {
        Endereco endereco = enderecoRepository.findById(id);

        if (endereco == null) {

            LOG.info("nao esta encontrando o endereco");
            return null;
        }else {
            LOG.info("encontro o endereco");
            return EnderecoResponceDTO.valueOf(endereco);
        }

    }

    @Override
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public List<EnderecoResponceDTO> findByCep(String cep) {

        List<Endereco> list = enderecoRepository.findByListaCep(cep);
        return list.stream().map(EnderecoResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return enderecoRepository.count();
    }

    private void validar(EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }
}