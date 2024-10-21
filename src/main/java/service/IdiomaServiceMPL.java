package service;

import DTO.IdiomaDTO;
import DTO.IdiomaResposceDTO;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.Idioma;
import repository.IdiomaRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class IdiomaServiceMPL implements IdiomaService {

    @Inject
    IdiomaRepository idiomaRepository;

    @Inject
    Validator validator;


    @Override
    public List<IdiomaResposceDTO> getAll(int page, int size) {
        List<Idioma> list = idiomaRepository.findAll().page(page,size).list();
        return list.stream().map(IdiomaResposceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public IdiomaResposceDTO create(IdiomaDTO idiomaDTO) throws ConstraintViolationException {
        validar(idiomaDTO);

        Idioma entity = new Idioma();

        entity.setIdioma(idiomaDTO.idioma());
        entity.setSigla(idiomaDTO.sigla());

        idiomaRepository.persist(entity);

        return IdiomaResposceDTO.valueOf(entity);
    }

    @Override
    public IdiomaResposceDTO update(Long id, IdiomaDTO idiomaDTO) throws ConstraintViolationException {
        validar(idiomaDTO);

        Idioma entity = idiomaRepository.findById(id);

        if (entity == null) {
            return null;
        }

        entity.setIdioma(idiomaDTO.idioma());
        entity.setSigla(idiomaDTO.sigla());

        idiomaRepository.persist(entity);

        return IdiomaResposceDTO.valueOf(entity);
    }

    @Override
    public void delete(Long id) {
        idiomaRepository.deleteById(id);
    }

    @Override
    public List<IdiomaResposceDTO> findByIdioma(String idioma) {
        List<Idioma> list = idiomaRepository.findByListIdioma(idioma);
        return list.stream().map(IdiomaResposceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public IdiomaResposceDTO findById(long id) {
        return IdiomaResposceDTO.valueOf(idiomaRepository.findById(id));
    }

    @Override
    public long count() {
        return idiomaRepository.count();
    }

    public List<IdiomaResposceDTO> search(int page, int size, String idioma ) {

        List<Idioma> list = idiomaRepository.findByIdiomaPanache(idioma).page(page,size).list();
        return list.stream().map(IdiomaResposceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public List<IdiomaResposceDTO> search() {
        List<Idioma> list = idiomaRepository.findAll(Sort.by("nome")).list();

        return list.stream().map(IdiomaResposceDTO::valueOf).toList();
    }

    private void validar(IdiomaDTO idiomaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<IdiomaDTO>> violations = validator.validate(idiomaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
