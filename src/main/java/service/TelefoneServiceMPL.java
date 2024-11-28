package service;

import DTO.TelefoneDTO;
import DTO.TelefoneResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.Telefone;
import repository.TelefoneRepository;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class TelefoneServiceMPL implements TelefoneService{

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    Validator validator;



    @Override
    public List<TelefoneResponceDTO> getAll(int page, int size) {
        List<Telefone> list = telefoneRepository.findAll().page(page,size).list();
        return list.stream().map(TelefoneResponceDTO::valueOf).toList();
    }

    @Override
    public TelefoneResponceDTO create(TelefoneDTO telefoneDTO) throws ConstraintViolationException {
        validar(telefoneDTO);

        Telefone entity = new Telefone();

        entity.setCodegoDeArea(telefoneDTO.codegoDeArea());
        entity.setNumero(telefoneDTO.numero());

        telefoneRepository.persist(entity);

        return TelefoneResponceDTO.valueOf(entity);
    }

    @Override
    public TelefoneResponceDTO update(Long id, TelefoneDTO telefoneDTO) throws ConstraintViolationException {
        validar(telefoneDTO);

        Telefone entity = telefoneRepository.findById(id);

        entity.setCodegoDeArea(telefoneDTO.codegoDeArea());
        entity.setNumero(telefoneDTO.numero());

        telefoneRepository.persist(entity);

        return TelefoneResponceDTO.valueOf(entity);
    }

    @Override
    public void delete(Long id) {
        telefoneRepository.deleteById(id);
    }

    @Override
    public List<TelefoneResponceDTO> findByNumero(String numero) {
        List<Telefone> list = telefoneRepository.findByNumeroPanache(numero).list();
        return list.stream().map(TelefoneResponceDTO::valueOf).toList();
    }

    @Override
    public TelefoneResponceDTO findById(long id) {
        return TelefoneResponceDTO.valueOf(telefoneRepository.findById(id));
    }

    @Override
    public long count() {
        return telefoneRepository.count();
    }

    private void validar(TelefoneDTO telefoneDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<TelefoneDTO>> violations = validator.validate(telefoneDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
