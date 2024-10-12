package service;


import DTO.FormatoResponceDTO;
import DTO.MunicipiosDTO;
import DTO.MunicipiosResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolationException;

import jakarta.transaction.Transactional;
import model.Estado;
import model.Municipio;
import repository.EstadoRepository;
import repository.MunicipioRepository;

import java.util.stream.Collectors;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class MunicipioServiceMPL implements MunicipioService{
    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<MunicipiosResponceDTO> getAll(int index , int size) {

        List<Municipio> list = municipioRepository.findAll().page(index,size).list();
        return list.stream().map(MunicipiosResponceDTO::new).collect(Collectors.toList());

    }



    @Override
    @Transactional
    public MunicipiosResponceDTO create(MunicipiosDTO municipioDTO) throws ConstraintViolationException{
        validar(municipioDTO);

        var entity = new Municipio();
        entity.setNome(municipioDTO.nome());

        entity.setEstado(new Estado());
        entity.getEstado().setId(municipioDTO.idEstado());

        municipioRepository.persist(entity);

        return new MunicipiosResponceDTO(entity);

    }

    @Override
    @Transactional
    public MunicipiosResponceDTO update(Long id, MunicipiosDTO municipioDTO) throws ConstraintViolationException{
        validar(municipioDTO);

        Municipio entity = municipioRepository.findById(id);


        entity.setNome(municipioDTO.nome());
        entity.setEstado(estadoRepository.findById(municipioDTO.idEstado()));

        return new MunicipiosResponceDTO(entity);
    }

    @Override
    public void delete(Long id) {
        municipioRepository.deleteById(id);
    }

    @Override
    public List<MunicipiosResponceDTO> findByNome(String nome) {
        List<Municipio> list = municipioRepository.findByNome(nome);
        return list.stream().map(MunicipiosResponceDTO::new).collect(Collectors.toList());
    }

    @Override
    public MunicipiosResponceDTO findById(long id) {
        return new MunicipiosResponceDTO(municipioRepository.findById(id));
    }

    @Override
    public long count() {
        return municipioRepository.count();
    }

    private void validar(MunicipiosDTO municipioDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<MunicipiosDTO>> violations = validator.validate(municipioDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
