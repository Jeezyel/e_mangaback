package resouce;

import DTO.EstadoDTO;
import DTO.EstadoResponceDTO;
import aplication.Result;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import jakarta.ws.rs.core.MediaType;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

import jakarta.ws.rs.core.Response.Status;
import jakarta.transaction.Transactional;
import service.EstadoService;

import java.util.List;

@Path("/estados")
@RolesAllowed("admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResouce {

    @Inject
    EstadoService estadoService;
    private static final Logger LOG = Logger.getLogger(EstadoResouce.class);

    @GET
    @Path("/")
    public List<EstadoResponceDTO> getAll(@QueryParam("index") @DefaultValue("0") int index
                                         ,@QueryParam("pageSize")  @DefaultValue("100") int size) {
        LOG.info("Buscando todos os estados.");
        LOG.debug("Debug de busca de lista de estados.");
        return estadoService.getAll(index,size);
    }

    @GET
    @Path("/{id}")
    public EstadoResponceDTO findById(@PathParam("id") Long id) {
        LOG.info("Buscando ID de estados.");
        return estadoService.findById(id);
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insert(EstadoDTO estadoDTO) {
        LOG.info("Inserindo um estado.");
        try {
            EstadoResponceDTO estado = estadoService.create(estadoDTO);
            return Response.status(Status.CREATED).entity(estado).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de estados.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")/*
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)*/
    @Transactional
    public Response update(@PathParam("id") Long id, EstadoDTO estadoDTO) {
        LOG.info("Atualiza um estado.");
        try {
            estadoService.update(id, estadoDTO);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de updat de estados.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Conta estados.");
        return estadoService.count();
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id) {
        estadoService.delete(id);
    }


    @GET
    @Path("/findByNome/{nome}")
    public List<EstadoResponceDTO> search(@PathParam("nome") String nome) {
        LOG.info("Busca nome de estados.");
        return estadoService.findByNome(nome);
    }
}