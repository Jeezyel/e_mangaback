package repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.FormatoManga;
import model.Idioma;

import java.util.List;

@ApplicationScoped
public class IdiomaRepository implements PanacheRepository<Idioma> {

    public List<Idioma> findByListIdioma(String idioma){
        if (idioma == null)
            return null;
        return find("UPPER(idioma) LIKE ?1 ", "%"+idioma.toUpperCase()+"%").list();
    }

    public PanacheQuery<Idioma> findByIdiomaPanache(String idioma){
        if (idioma == null)
            return null;
        return find("UPPER(idioma) LIKE ?1 ", "%"+idioma.toUpperCase()+"%");
    }
}
