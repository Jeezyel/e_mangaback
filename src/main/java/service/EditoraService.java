package service;

import DTO.EditoraDTO;
import DTO.EditoraResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface EditoraService {

    // recursos basicos
    List<EditoraResponceDTO> getAll();


    EditoraResponceDTO create(EditoraDTO editoraDTO);

    EditoraResponceDTO update(Long id, EditoraDTO editoraDTO);

    void delete(Long id);

    // recursos extras

    List<EditoraResponceDTO> search(int page , int size, String nome);

    //List<EditoraResponceDTO> search(String nome);

    EditoraResponceDTO findById(long id);
}