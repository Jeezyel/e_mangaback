package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Endereco;
import model.Municipio;
import model.Usuario;

import java.util.List;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByListaCep(String cep){
        if (cep == null)
            return null;
        return find("UPPER(cep) LIKE ?1 ", "%"+cep.toUpperCase()+"%").list();
    }

    public Endereco findByCep(String cep){
        if (cep == null)
            return null;
        return find("UPPER(cep) LIKE ?1 ", "%"+cep.toUpperCase()+"%").firstResult();
    }

}