package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Editora;

import java.util.List;

@ApplicationScoped
public class EditoraRepository implements PanacheRepository<Editora> {
    public List<Editora> findByNome(String cnpj){
        if (cnpj == null)
            return null;
        return find("UPPER(cnpj) LIKE ?1 ", "%"+cnpj.toUpperCase()+"%").list();
    }

}