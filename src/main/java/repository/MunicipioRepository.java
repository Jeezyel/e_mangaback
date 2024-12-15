package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Municipio;

import java.util.List;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio> {
    public List<Municipio> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

    public Municipio findByName(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").firstResult();
    }

    public Municipio findByMunicipioAndEstado(String nome, String estado){
        if (nome == null || estado == null) {
            return null;
        }
        return find("municipio.nome = ?1 and municipio.estado.sigla = ?2", nome, estado).firstResult();
    }

}
