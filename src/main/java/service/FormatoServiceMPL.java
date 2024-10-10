package service;

import DTO.FormatoDTO;
import DTO.FormatoResponceDTO;
import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.FormatoManga;
import model.Manga;
import repository.FormatoRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class FormatoServiceMPL implements FormatoService {

    @Inject
    FormatoRepository formatoRepository;

    @Inject
    Validator validator;


    @Override
    public List<FormatoResponceDTO> getAll(int page, int size) {
        List<FormatoManga> list = formatoRepository.findAll().page(page,size).list();
        return list.stream().map(FormatoResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public FormatoResponceDTO create(FormatoDTO formato) throws ConstraintViolationException {
        validar(formato);

        FormatoManga entity = new FormatoManga();

        entity.setFormato(formato.formato());

        formatoRepository.persist(entity);

        return FormatoResponceDTO.valueOf(entity);
    }

    @Override
    public FormatoResponceDTO update(Long id, FormatoDTO formato) throws ConstraintViolationException {
        validar(formato);

        FormatoManga entity = formatoRepository.findById(id);

        if (entity == null) {
            return null;
        }

        entity.setFormato(formato.formato());

        formatoRepository.persist(entity);

        return FormatoResponceDTO.valueOf(entity);
    }

    @Override
    public void delete(Long id) {
        formatoRepository.deleteById(id);
    }

    @Override
    public List<FormatoResponceDTO> findByFormato(String formato) {
        List<FormatoManga> list = formatoRepository.findByListFormato(formato);
        return list.stream().map(FormatoResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public FormatoResponceDTO findById(long id) {
        return FormatoResponceDTO.valueOf(formatoRepository.findById(id));
    }

    @Override
    public long count() {
        return formatoRepository.count();
    }

    private void validar(FormatoDTO formatoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FormatoDTO>> violations = validator.validate(formatoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
