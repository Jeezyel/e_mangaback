package service;

import DTO.EditoraResponceDTO;
import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import model.Editora;
import model.Manga;
import repository.MangaRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class MangaServiceMPL implements MangaService {

    @Inject
    MangaRepository mangaRepository;

    @Inject
    Validator validator;


    @Override
    public List<MangaResponceDTO> getAll(int page, int size) {
        List<Manga> list = mangaRepository.findAll().page(page,size).list();
        return list.stream().map(MangaResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public MangaResponceDTO create(MangaDTO mangaDTO) throws ConstraintViolationException {

        validar(mangaDTO);

        Manga entity = new Manga();

        entity.setNome(mangaDTO.nome());
        entity.setFormatos(mangaDTO.formatoManga());
        entity.setGeneros(mangaDTO.mangaGeneros());
        entity.setIdioma(mangaDTO.idioma());
        entity.setClassificacao(mangaDTO.classificacao());

        mangaRepository.persist(entity);


        return MangaResponceDTO.valueOf(entity);
    }

    @Override
    public MangaResponceDTO update(Long id, MangaDTO mangaDTO) throws ConstraintViolationException {
        validar(mangaDTO);


        Manga entity = new Manga();

        entity = mangaRepository.findById(id);

        entity.setNome(mangaDTO.nome());
        entity.setFormatos(mangaDTO.formatoManga());
        entity.setGeneros(mangaDTO.mangaGeneros());
        entity.setIdioma(mangaDTO.idioma());
        entity.setClassificacao(mangaDTO.classificacao());

        mangaRepository.persist(entity);


        return MangaResponceDTO.valueOf(entity);
    }

    @Override
    public void delete(Long id) {
        mangaRepository.deleteById(id);
    }

    @Override
    public List<MangaResponceDTO> findByNome(String nome) {
        return mangaRepository.findByListaNome(nome).stream().map(MangaResponceDTO ::valueOf).collect(Collectors.toList());
    }

    @Override
    public MangaResponceDTO findById(long id) {
        return MangaResponceDTO.valueOf(mangaRepository.findById(id));
    }

    @Override
    public long count() {
        return mangaRepository.count();
    }

    @Override
    public List<MangaResponceDTO> search(int page, int size, String nome) {
        List<Manga> list = mangaRepository.findByNomePanche(nome).page(page,size).list();
        return list.stream().map(MangaResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public List<MangaResponceDTO> search() {
        List<Manga> list = mangaRepository.findAll(Sort.by("nome")).list();

        return list.stream().map(MangaResponceDTO::valueOf).toList();
    }

    private void validar(MangaDTO mangaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<MangaDTO>> violations = validator.validate(mangaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
