package resouce;

import DTO.EditoraDTO;
import DTO.EditoraResponceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;
import service.EditoraService;

import java.util.List;

@Path("/editoras")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EditoraResouce {
    @Inject
    EditoraService editoraService;
    private static final Logger LOG = Logger.getLogger(EditoraResouce.class);

    @GET
    @Path("/getAll")
    public List<EditoraResponceDTO> getAll() {
        return editoraService.getAll();
    }

    @GET
    @Path("/search/{id}")
    public EditoraResponceDTO findById(@PathParam("id") Long id) {
        return editoraService.findById(id);
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insert(EditoraDTO editoraDTO) {
        try {
            EditoraResponceDTO editora = editoraService.create(editoraDTO);
            return Response.status(Status.CREATED).entity(editora).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de contato.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/DeleteForId/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        editoraService.delete(id);
    }


    @PUT
    @Path("/update/{id}")/*
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)*/
    @Transactional
    public Response update(@PathParam("id") Long id, EditoraDTO editoraDTO) {
        LOG.info("Atualiza um contato.");
        try {
            editoraService.update(id, editoraDTO);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de updat de contato.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }





}