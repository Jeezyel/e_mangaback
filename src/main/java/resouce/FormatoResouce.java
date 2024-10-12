package resouce;

import DTO.FormatoDTO;
import DTO.FormatoResponceDTO;
import DTO.IdiomaDTO;
import DTO.IdiomaResposceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import service.FormatoService;

import java.util.List;

@Path("/formatos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FormatoResouce {
    private static final Logger LOG = Logger.getLogger(FormatoResouce.class);

    @Inject
    FormatoService formatoService;

    @GET
    @Path("/")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int page , @QueryParam("size")  @DefaultValue("100") int size) {
        return Response.ok(formatoService.getAll(page, size)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(FormatoDTO formatoDTO) {
        LOG.info("Inserindo um idioma.");
        try {
            FormatoResponceDTO formato = formatoService.create(formatoDTO);
            return Response.status(Response.Status.CREATED).entity(formato).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de municipios.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, FormatoDTO formatoDTO) {
        try {
            FormatoResponceDTO formato = formatoService.update(id, formatoDTO);
            return Response.ok(formato).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        formatoService.delete(id);
    }

    @GET
    @Path("/{Id}")
    @Transactional
    public Response GetForId(@PathParam("Id") long id){
        return Response.ok(formatoService.findById(id)).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return formatoService.count();
    }

    @GET
    @Path("/findByFormato/{formato}")
    public List<FormatoResponceDTO> search(@PathParam("formato") String formato){
        return formatoService.findByFormato(formato);

    }

}
