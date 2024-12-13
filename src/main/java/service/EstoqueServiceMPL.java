package service;

import DTO.EstoqueDTO;
import DTO.EstoqueResponceDTO;
import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import model.Estoque;
import model.Usuario;
import repository.EstoqueRepository;
import repository.MangaRepository;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class EstoqueServiceMPL implements EstoqueService{

    @Inject
    EstoqueRepository estoqueRepository;

    @Inject
    Validator validator;

    @Inject
    MangaRepository mangaRepository;

    @Override
    public List<EstoqueResponceDTO> getAll(int page , int size) {
        List<Estoque> list = estoqueRepository.findAll().page(page, size).list();
        return list.stream().map(EstoqueResponceDTO::valueOf).toList();
    }

    @Override
    public EstoqueResponceDTO create(EstoqueDTO estoqueDTO) throws ConstraintViolationException {
        validar(estoqueDTO);

        Estoque entity = new Estoque();

        entity.setQuantidade(estoqueDTO.quantidade());
        entity.setManga(mangaRepository.findById(estoqueDTO.manga()));
        entity.setValorUnitario(estoqueDTO.valorUnitario());

        estoqueRepository.persist(entity);

        return EstoqueResponceDTO.valueOf(entity);
    }

    @Override
    public EstoqueResponceDTO update(Long id, EstoqueDTO estoqueDTO) throws ConstraintViolationException {
        validar(estoqueDTO);

        Estoque entity = estoqueRepository.findById(id);

        entity.setQuantidade(estoqueDTO.quantidade());
        entity.setManga(mangaRepository.findById(estoqueDTO.manga()));
        entity.setValorUnitario(estoqueDTO.valorUnitario());

        estoqueRepository.persist(entity);

        return EstoqueResponceDTO.valueOf(entity);
    }

    @Override
    public void delete(Long id) {
        estoqueRepository.deleteById(id);
    }

    @Override
    public EstoqueResponceDTO findById(long id) {
        return EstoqueResponceDTO.valueOf(estoqueRepository.findById(id));
    }

    @Override
    public long count() {
        return estoqueRepository.count();
    }

    private void validar(EstoqueDTO estoqueDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EstoqueDTO>> violations = validator.validate(estoqueDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }

    public Response testeEstoque(){
        return estoqueRepository.findByListaNomeMangar("Naruto").isEmpty()? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(estoqueRepository.findByListaNomeMangar("Naruto")).build();
    }
}
