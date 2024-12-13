package repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Manga;
import model.Pedido;
import model.Usuario;

import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public Pedido findByUser(Usuario usuario){
        if (usuario == null)
            return null;
        return find("UPPER(usuario) LIKE ?1 ", usuario).firstResult();
    }

}