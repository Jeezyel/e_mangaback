package repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Manga;
import model.MangaGenero;

import java.util.List;

@ApplicationScoped
public class MangaGeneroRepository implements PanacheRepository<MangaGenero> {

    public List<MangaGenero> findByListGenero(String genero){
        if (genero == null)
            return null;
        return find("UPPER(genero) LIKE ?1 ", "%"+genero.toUpperCase()+"%").list();
    }

    public PanacheQuery<MangaGenero> findByGeneroPanache(String genero){
        if (genero == null)
            return null;
        return find("UPPER(genero) LIKE ?1 ", "%"+genero.toUpperCase()+"%");
    }
}
