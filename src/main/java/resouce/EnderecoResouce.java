package resouce;

import DTO.EnderecoDTO;
import DTO.EnderecoResponceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;
import service.EnderecoService;

import java.util.List;

@Path("/endereco")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoResouce {
    @Inject
    EnderecoService enderecoService;
    private static final Logger LOG = Logger.getLogger(EnderecoResouce.class);

    @GET
    @Path("/getAll")
    public List<EnderecoResponceDTO> getAll() {
        LOG.info("Buscando todos os estados.");
        LOG.debug("Debug de busca de lista de estados.");
        return enderecoService.getAll();
    }

    @GET
    @Path("/search/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando ID de estados.");
        return Response.ok(enderecoService.findById(id)).build();
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insert(EnderecoDTO enderecoDTO) {
        LOG.info("Inserindo um endereco.");
        try {
            EnderecoResponceDTO endereco = enderecoService.create(enderecoDTO);
            return Response.status(Status.CREATED).entity(endereco).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de endereco.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")/*
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)*/
    @Transactional
    public Response update(@PathParam("id") Long id, EnderecoDTO enderecoDTO) {
        LOG.info("Atualiza um estado.");
        try {
            enderecoService.update(id, enderecoDTO);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de updat de endereco.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @Transactional
    @Path("criarESalvarViaCep/{cep}")
    public Response enderecoCep(@PathParam("cep") String cep) {

        LOG.info("Pegando o endereco pelo cep.");
        try {
            EnderecoResponceDTO enderecoResponceDTO = enderecoService.enderecoCep(cep);
            return Response.ok(enderecoResponceDTO).status(Status.OK).build();
        } catch (Exception e) {
            Result result = new Result(e.getMessage());

            LOG.error("ERRO: " + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }



    }

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Conta estados.");
        return enderecoService.count();
    }

    @DELETE
    @Path("/DeleteForId/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        enderecoService.delete(id);
    }


    @GET
    @Path("/search/{cep}")
    public List<EnderecoResponceDTO> search(@PathParam("cep") String cep) {
        LOG.info("Busca nome de estados.");
        return enderecoService.findByCep(cep);
    }


}