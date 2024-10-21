package repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.FormatoManga;
import model.MangaGenero;

import java.util.List;

@ApplicationScoped
public class FormatoRepository implements PanacheRepository<FormatoManga> {

    public List<FormatoManga> findByListFormato(String formato){
        if (formato == null)
            return null;
        return find("UPPER(formato) LIKE ?1 ", "%"+formato.toUpperCase()+"%").list();
    }

    public PanacheQuery<FormatoManga> findByFormatoPanache(String formato){
        if (formato == null)
            return null;
        return find("UPPER(formato) LIKE ?1 ", "%"+formato.toUpperCase()+"%");
    }
}
