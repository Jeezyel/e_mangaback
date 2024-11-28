package repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Editora;
import model.Telefone;

import java.util.List;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone> {
    public List<Telefone> findByDDD(String codegoDeArea){
        if (codegoDeArea == null)
            return null;
        return find("UPPER(codegoDeArea) LIKE ?1 ", "%"+codegoDeArea.toUpperCase()+"%").list();
    }

    public PanacheQuery<Telefone> findByNumeroPanache(String numero){
        if (numero == null)
            return null;
        return find("UPPER(numero) LIKE ?1 ", "%"+numero.toUpperCase()+"%");
    }

}