package service;

import DTO.MangaGeneroDTO;
import DTO.MangaGeneroResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.MangaGenero;
import repository.MangaGeneroRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class MangaGeneroServiceMPL implements MangaGeneroService {

    @Inject
    MangaGeneroRepository mangaGeneroRepository;

    @Inject
    Validator validator;

    @Override
    public List<MangaGeneroResponceDTO> getAll(int page, int size) {
        List<MangaGenero> list = mangaGeneroRepository.findAll().page(page,size).list();
        return list.stream().map(MangaGeneroResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public MangaGeneroResponceDTO create(MangaGeneroDTO mangaGeneroDTO) throws ConstraintViolationException {
        validar(mangaGeneroDTO);

        MangaGenero entity = new MangaGenero();

        entity.setGenero(mangaGeneroDTO.genero());

        mangaGeneroRepository.persist(entity);

        return MangaGeneroResponceDTO.valueOf(entity);
    }

    @Override
    public MangaGeneroResponceDTO update(Long id, MangaGeneroDTO mangaGeneroDTO) throws ConstraintViolationException {
        validar(mangaGeneroDTO);

        MangaGenero entity = mangaGeneroRepository.findById(id);

        if (entity == null) {
            return null;
        }

        entity.setGenero(mangaGeneroDTO.genero());

        mangaGeneroRepository.persist(entity);

        return MangaGeneroResponceDTO.valueOf(entity);
    }

    @Override
    public void delete(Long id) {
        mangaGeneroRepository.deleteById(id);
    }

    @Override
    public List<MangaGeneroResponceDTO> findByGenero(String genero) {
        List<MangaGenero> list = mangaGeneroRepository.findByListGenero(genero);
        return list.stream().map(MangaGeneroResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public MangaGeneroResponceDTO findById(long id) {
        return MangaGeneroResponceDTO.valueOf(mangaGeneroRepository.findById(id));
    }

    @Override
    public long count() {
        return mangaGeneroRepository.count();
    }

    private void validar(MangaGeneroDTO mangaGeneroDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<MangaGeneroDTO>> violations = validator.validate(mangaGeneroDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
