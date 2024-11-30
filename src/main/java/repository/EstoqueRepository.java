package repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Estoque;

import java.util.List;

@ApplicationScoped
public class EstoqueRepository implements PanacheRepository<Estoque> {

    public List<Estoque> findByListaNomeMangar(String nome){
        if (nome == null)
            return null;
        return find("SELECT e.manga.nome FROM Estoque e").list();
    }
    public Estoque findByNomeMangar(String nome){
        if (nome == null)
            return null;
        return find("UPPER(manga.nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").firstResult();
    }
}
