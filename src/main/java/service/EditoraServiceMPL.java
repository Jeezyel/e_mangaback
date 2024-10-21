package service;


import DTO.EditoraDTO;
import DTO.EditoraResponceDTO;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.Editora;
import repository.EditoraRepository;
import repository.EnderecoRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class EditoraServiceMPL implements EditoraService{
    @Inject
    EditoraRepository editoraRepository;

    @Inject
    EnderecoRepository enderecoRepository;



    @Inject
    Validator validator;


    @Override
    public List<EditoraResponceDTO> getAll() {

        List<Editora> list = editoraRepository.listAll();
        return list.stream().map(EditoraResponceDTO::new).collect(Collectors.toList());
    }

    @Override
    public EditoraResponceDTO create(EditoraDTO editoraDTO) {
        validar(editoraDTO);

        Editora entity = new Editora();

        entity.setCnpj(editoraDTO.cnpj());
        entity.setNome(editoraDTO.nome());
        entity.setTelefone(editoraDTO.telefone());


        editoraRepository.persist(entity);

        return new EditoraResponceDTO(entity);


    }

    @Override
    public EditoraResponceDTO update(Long id, EditoraDTO editoraDTO) {
        validar(editoraDTO);

        Editora entity = editoraRepository.findById(id);

        entity.setCnpj(editoraDTO.cnpj());
        entity.setNome(editoraDTO.nome());
        entity.setTelefone(editoraDTO.telefone());


        editoraRepository.persist(entity);

        return new EditoraResponceDTO(entity);
    }

    @Override
    public EditoraResponceDTO findById(long id) {
        return new EditoraResponceDTO(editoraRepository.findById(id));
    }

    @Override
    public void delete(Long id) {
        editoraRepository.deleteById(id);
    }


    @Override
    public List<EditoraResponceDTO> search(int page, int size, String nome) {
        List<Editora> list = editoraRepository.findByNomePanache(nome).page(page,size).list();
        return list.stream().map(EditoraResponceDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EditoraResponceDTO> search() {
        List<Editora> list = editoraRepository.findAll(Sort.by("nome")).list();

        return list.stream().map(EditoraResponceDTO::new).toList();
    }

    private void validar(EditoraDTO editoraDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EditoraDTO>> violations = validator.validate(editoraDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }
}
