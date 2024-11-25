package service;

import DTO.EnderecoResponceDTO;
import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import model.Endereco;
import model.ViaCep;
import repository.MunicipioRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@ApplicationScoped
public class ViacepServiceMPL implements ViacepService{

    @Inject
    MunicipioRepository municipioRepository;


    @Override
    public ViaCep ViaCep(String cep) throws Exception {


        URL url = new URL("https://viacep.com.br/ws/"+cep+"/json/");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

        String Ceep = "";

        StringBuilder jsonCep = new StringBuilder();

        while ((Ceep = br.readLine()) != null){

            jsonCep.append(Ceep);

        }

        ViaCep viaCep = new Gson().fromJson(jsonCep.toString(), ViaCep.class);


        return  viaCep;
    }

    @Override
    public EnderecoResponceDTO enderecoCep(String cep) throws Exception {

        ViaCep viaCep = this.ViaCep(cep);
        Endereco endereco = new Endereco();

        endereco.setCep(viaCep.getCep());
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setComplemento(viaCep.getComplemento());
        endereco.setBairro(viaCep.getBairro());
        endereco.setMunicipio(municipioRepository.findByName(viaCep.getLocalidade()));


        return EnderecoResponceDTO.valueOf(endereco);
    }
}