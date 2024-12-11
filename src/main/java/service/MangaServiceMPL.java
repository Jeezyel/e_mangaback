package service;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.WebApplicationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.Manga;
import model.Editora;
import model.MangaGenero;
import model.FormatoManga;
import model.Idioma;
import model.ClassificacaoIndicativa;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;

import repository.MangaRepository;
import repository.EditoraRepository;
import repository.MangaGeneroRepository;
import repository.FormatoRepository;
import repository.IdiomaRepository;

@ApplicationScoped
public class MangaServiceMPL implements MangaService {

    @Inject
    MangaRepository mangaRepository;

    @Inject
    EditoraRepository editoraRepository;

    @Inject
    FormatoRepository formatoRepository;

    @Inject
    MangaGeneroRepository generoRepository;

    @Inject
    IdiomaRepository idiomaRepository;

    @Inject
    Validator validator;

    @Override
    public List<MangaResponceDTO> getAll(int page, int size) {
        List<Manga> list = mangaRepository.findAll().page(page, size).list();
        return list.stream().map(MangaResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public MangaResponceDTO create(MangaDTO mangaDTO) throws ConstraintViolationException {
        validar(mangaDTO);

        Manga entity = new Manga();

        // Resolver Editora
        Editora editora = editoraRepository.findById(mangaDTO.idEditora());
        if (editora == null) {
            throw new RuntimeException("Editora não encontrada");
        }
        entity.setEditora(editora);

        // Resolver Formato
        FormatoManga formato = formatoRepository.findById(mangaDTO.idFormato());
        if (formato == null) {
            throw new RuntimeException("Formato não encontrado");
        }
        entity.setFormato(formato);

        // Resolver Gêneros
        entity.setGenero(mangaDTO.idMangaGenero().stream()
            .map(generoId -> {
                MangaGenero genero = generoRepository.findById(generoId);
                if (genero == null) {
                    throw new RuntimeException("Gênero não encontrado");
                }
                return genero;
            })
            .collect(Collectors.toSet()));

        // Resolver Idioma
        Idioma idioma = idiomaRepository.findById(mangaDTO.idIdioma());
        if (idioma == null) {
            throw new RuntimeException("Idioma não encontrado");
        }
        entity.setIdioma(idioma);

        // Atribuir demais campos
        entity.setNome(mangaDTO.nome());
        entity.setValor(mangaDTO.valor());
        entity.setClassificacaoIndicativa(ClassificacaoIndicativa.fromId(mangaDTO.id()));
        entity.setEstoque(mangaDTO.estoque());

        mangaRepository.persist(entity);

        return MangaResponceDTO.valueOf(entity);
    }

    @Override
    public MangaResponceDTO update(Long id, MangaDTO mangaDTO) throws ConstraintViolationException {
        
        validar(mangaDTO);

        Manga entity = mangaRepository.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Mangá não encontrado", Response.Status.NOT_FOUND);
        }

        // Resolver Editora
        Editora editora = editoraRepository.findById(mangaDTO.idEditora());
        if (editora == null) {
            throw new RuntimeException("Editora não encontrada");
        }
        entity.setEditora(editora);

        // Resolver Formato
        FormatoManga formato = formatoRepository.findById(mangaDTO.idFormato());
        if (formato == null) {
            throw new RuntimeException("Formato não encontrado");
        }
        entity.setFormato(formato);

        // Resolver Gêneros
        entity.setGenero(mangaDTO.idMangaGenero().stream()
            .map(generoId -> {
                MangaGenero genero = generoRepository.findById(generoId);
                if (genero == null) {
                    throw new RuntimeException("Gênero não encontrado");
                }
                return genero;
            })
            .collect(Collectors.toSet()));

        // Resolver Idioma
        Idioma idioma = idiomaRepository.findById(mangaDTO.idIdioma());
        if (idioma == null) {
            throw new RuntimeException("Idioma não encontrado");
        }
        entity.setIdioma(idioma);

        // Atualizar demais campos
        entity.setNome(mangaDTO.nome());
        entity.setValor(mangaDTO.valor());
        entity.setClassificacaoIndicativa(ClassificacaoIndicativa.fromId(mangaDTO.id()));
        entity.setEstoque(mangaDTO.estoque());

        mangaRepository.persist(entity);

        return MangaResponceDTO.valueOf(entity);
    }

    @Override
    public void delete(Long id) {
        mangaRepository.deleteById(id);
    }

    @Override
    public List<MangaResponceDTO> findByNome(String nome) {
        return mangaRepository.findByListaNome(nome).stream().map(MangaResponceDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public MangaResponceDTO findById(long id) {
        Manga manga = mangaRepository.findById(id);
        if (manga == null) {
            throw new RuntimeException("Mangá não encontrado");
        }
        return MangaResponceDTO.valueOf(manga);
    }

    @Override
    public long count() {
        return mangaRepository.count();
    }

    @Override
    public List<MangaResponceDTO> search(int page, int size, String nome) {
        List<Manga> list = mangaRepository.findByNomePanche(nome).page(page, size).list();
        return list.stream().map(MangaResponceDTO::valueOf).collect(Collectors.toList());
    }

    private void validar(MangaDTO mangaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<MangaDTO>> violations = validator.validate(mangaDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}