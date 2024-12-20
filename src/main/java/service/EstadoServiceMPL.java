package service;


import DTO.EstadoDTO;
import DTO.EstadoResponceDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import jakarta.inject.Inject;
import model.Estado;
import repository.EstadoRepository;
import repository.MunicipioRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstadoServiceMPL implements EstadoService{

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<EstadoResponceDTO> getAll(int page , int size) {

        List<Estado> list = estadoRepository.findAll().page(page,size).list();
        return list.stream().map(EstadoResponceDTO::new).collect(Collectors.toList());

    }

    @Override
    public EstadoResponceDTO create(EstadoDTO estadoDTO) {
        validar(estadoDTO);

        Estado entity = new Estado();
        entity.setNome(estadoDTO.nome());
        entity.setSigla(estadoDTO.sigla());

        estadoRepository.persist(entity);

        return new EstadoResponceDTO(entity);


    }

    @Override
    public EstadoResponceDTO update(Long id, EstadoDTO estadoDTO) {
        validar(estadoDTO);

        Estado entity = estadoRepository.findById(id);

        entity.setNome(estadoDTO.nome());
        entity.setSigla(estadoDTO.sigla());
        estadoRepository.persist(entity);

        return new EstadoResponceDTO(entity);
    }

    @Override
    public EstadoResponceDTO findById(long id) {
        return new EstadoResponceDTO(estadoRepository.findById(id));
    }

    @Override
    public void delete(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (Exception e) {
            Log.info("erro no delete: " + e.getMessage());
        }

    }

    @Override
    public List<EstadoResponceDTO> findByNome(String nome) {

        List<Estado> list = estadoRepository.findByListaNome(nome);
        return list.stream().map(EstadoResponceDTO::new).collect(Collectors.toList());
    }



    @Override
    public long count() {
        return estadoRepository.count();
    }

    private void validar(EstadoDTO estadoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(estadoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }
}
