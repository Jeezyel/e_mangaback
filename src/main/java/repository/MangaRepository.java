package repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Manga;

import java.util.List;

@ApplicationScoped
public class MangaRepository implements PanacheRepository<Manga> {

    public List<Manga> findByListaNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

    public PanacheQuery<Manga> findByNomePanche(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%");
    }

    public Manga findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").firstResult();
    }
    public List<Manga> findAll2() {
        return find("SELECT c FROM Manga c ORDER BY c.nome ").list();
    }
}