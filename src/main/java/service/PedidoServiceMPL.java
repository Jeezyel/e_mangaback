package service;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import DTO.PedidoDTO;
import DTO.PedidoResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import model.*;
import repository.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoServiceMPL implements PedidoService {

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
    public PedidoResponceDTO create(PedidoDTO pedidoDTO) throws ConstraintViolationException {
        validar(pedidoDTO);

        Pedido entity = new Pedido();

        entity.setUsuario(usuarioRepository.findById(pedidoDTO.usuario()));
        entity.setProduto(SetManga(pedidoDTO.produto()));
        entity.setValortotal(pedidoDTO.valortotal());
        entity.setFormaDePagamento(pedidoDTO.formaDePagamento());
        entity.setQuantidadeParcela(pedidoDTO.quantidadeParcela());
        entity.setData(LocalDateTime.now());


        pedidoRepository.persist(entity);

        return PedidoResponceDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public PedidoResponceDTO update(Long id, PedidoDTO pedidoDTO) throws ConstraintViolationException {
        validar(pedidoDTO);

        Pedido entity = pedidoRepository.findById(id);

        entity.setUsuario(usuarioRepository.findById(pedidoDTO.usuario()));
        entity.setProduto(SetManga(pedidoDTO.produto()));
        entity.setValortotal(pedidoDTO.valortotal());
        entity.setFormaDePagamento(pedidoDTO.formaDePagamento());
        entity.setQuantidadeParcela(pedidoDTO.quantidadeParcela());
        entity.setData(LocalDateTime.now());


        pedidoRepository.persist(entity);

        return PedidoResponceDTO.valueOf(entity);
    }

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
        List<Manga> list = new ArrayList<>();

        for (int i = 0; i < mangarr.size(); i++) {
            list.add(mangaRepository.findById(mangarr.get(i)));
        }

        return new HashSet<Manga>(list);
    }
}


























