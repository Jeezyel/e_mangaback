package service;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.File;

@ApplicationScoped
public interface FileService {
    // recursos basicos
    void salvar(Long id, String nomeImagem, byte[] imagem);

    void salvarManga(Long id, String nomeImagem, byte[] imagem);

    File download(String nomeArquivo);

}