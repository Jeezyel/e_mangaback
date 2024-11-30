package resouce;

import DTO.EstoqueDTO;
import DTO.EstoqueResponceDTO;
import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import service.EstoqueService;

@Path("/estoque")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstoqueResouce {
    private static final Logger LOG = Logger.getLogger(EstoqueResouce.class);

    @Inject
    EstoqueService estoqueService;

    @GET
    @Path("/")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int page , @QueryParam("size")  @DefaultValue("100") int size) {
        return Response.ok(estoqueService.getAll(page, size)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(EstoqueDTO estoqueDTO) {
        LOG.info("Inserindo um estoque.");
        try {
            EstoqueResponceDTO estoque = estoqueService.create(estoqueDTO);
            return Response.status(Response.Status.CREATED).entity(estoque).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de estoque.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, EstoqueDTO estoqueDTO) {
        try {
            EstoqueResponceDTO estoque = estoqueService.update(id, estoqueDTO);
            return Response.ok(estoque).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        estoqueService.delete(id);
    }


    @GET
    @Path("/count")
    public long count(){
        return estoqueService.count();
    }
}
