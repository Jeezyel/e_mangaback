package service;

import DTO.EnderecoResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.ViaCep;

@ApplicationScoped
public interface ViacepService{

    ViaCep ViaCep(String cep) throws Exception;

    EnderecoResponceDTO enderecoCep(String cep) throws Exception;

}
