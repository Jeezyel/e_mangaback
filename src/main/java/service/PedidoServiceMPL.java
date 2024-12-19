package service;

import DTO.*;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.*;
import org.jboss.logging.Logger;
import repository.*;
import resouce.UsuarioResouce;

import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
public class PedidoServiceMPL implements PedidoService {

    private static final Logger LOG = Logger.getLogger(PedidoServiceMPL.class);

    @Inject
    Validator validator;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    MangaRepository mangaRepository;

    @Override
    public List<PedidoResponceDTO> getAll(int page, int size) {
        List<Pedido> list = pedidoRepository.findAll().page(page,size).list();
        return list.stream().map(PedidoResponceDTO::valueOf).toList();
    }

    @Override
    @Transactional
    public UsuarioPedidoJuncaoResponceDTO create(UsuarioPedidoJuncaoDTO usuarioPedidoJuncaoDTO) throws ConstraintViolationException {


        Pedido pedido = new Pedido();

        Usuario usuario = usuarioRepository.findById(usuarioPedidoJuncaoDTO.id());

        pedido.setUsuario(usuarioRepository.findById(usuario.getId()));

        List<Manga> listManga = new ArrayList<>();

        for (int i = 0; i < usuarioPedidoJuncaoDTO.produto().size(); i++) {
            listManga.add(mangaRepository.findById(usuarioPedidoJuncaoDTO.produto().get(i)));
        }
        if (listManga == null)
            return null;

        pedido.setProduto(listManga);
        pedido.setValortotal(usuarioPedidoJuncaoDTO.valortotal());
        pedido.setFormaDePagamento(usuarioPedidoJuncaoDTO.formaDePagamento());

        if (usuarioPedidoJuncaoDTO.formaDePagamento().equals(FormaDePagamento.PIX))
            pedido.setQuantidadeParcela(1);
        else
            pedido.setQuantidadeParcela(usuarioPedidoJuncaoDTO.quantidadeParcela());

        pedido.setData(LocalDateTime.now());

        //////////////////////////////////////////////////////////////

        usuario.setNome(usuarioPedidoJuncaoDTO.nome());
        usuario.setEmail(usuarioPedidoJuncaoDTO.email());
        usuario.setTelefone(usuarioPedidoJuncaoDTO.telefone());
        usuario.setEndereco(usuarioPedidoJuncaoDTO.endereco());


        usuarioRepository.persist(usuario);
        pedidoRepository.persist(pedido);

        return UsuarioPedidoJuncaoResponceDTO.valueOf(pedido, usuario );
    }

    /*@Override
    @Transactional
    public PedidoResponceDTO update(Long id, PedidoDTO pedidoDTO) throws ConstraintViolationException {
        validar(pedidoDTO);

        Pedido entity = pedidoRepository.findById(id);

        entity.setUsuario(usuarioRepository.findById(pedidoDTO.idUsuario()));
        entity.setProduto(SetManga(pedidoDTO.produto()));
        entity.setValortotal(pedidoDTO.valortotal());
        entity.setFormaDePagamento(pedidoDTO.formaDePagamento());

        if (pedidoDTO.formaDePagamento().equals(FormaDePagamento.PIX))
            entity.setQuantidadeParcela(1);
        else
            entity.setQuantidadeParcela(pedidoDTO.quantidadeParcela());

        entity.setData(LocalDateTime.now());


        pedidoRepository.persist(entity);

        return PedidoResponceDTO.valueOf(entity);
    }*/

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoResponceDTO findByUser(Long idUsuario) {
        Usuario user = usuarioRepository.findById(idUsuario);
        return PedidoResponceDTO.valueOf(pedidoRepository.findByUser(user));
    }

    @Override
    public List<PedidoResponceDTO> History(String userName) {

        Usuario usuario = usuarioRepository.findByUsername(userName);


        List<Pedido> history = pedidoRepository.history(usuario.getId());

        return history.stream().map(PedidoResponceDTO::valueOf).toList();
    }

    @Override
    @Transactional
    public PedidoResponceDTO updateStatus(Long id, String status) {
        Pedido entity = pedidoRepository.findById(id);

        entity.setStatus(Status.statusSet(status.toUpperCase()));

        pedidoRepository.persist(entity);

        return PedidoResponceDTO.valueOf(entity);
    }

    @Override
    public PedidoResponceDTO findById(long id) {
        return PedidoResponceDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public long count() {
        return pedidoRepository.count();
    }

    private void validar(PedidoDTO pedidoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PedidoDTO>> violations = validator.validate(pedidoDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private Set<Manga> SetManga(Set<Long> mangar){

        List<Long> mangarr = mangar.stream().toList();
        List<Manga> list = new ArrayList<Manga>();

        for (int i = 0; i < mangarr.size(); i++) {
            list.add(mangaRepository.findById(mangarr.get(i)));
        }

        return new HashSet<Manga>(list);
    }
}


























