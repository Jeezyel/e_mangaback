package resouce;

import DTO.IdiomaDTO;
import DTO.IdiomaResposceDTO;
import DTO.MangaGeneroDTO;
import DTO.MangaGeneroResponceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import service.MangaGeneroService;

import java.util.List;

@Path("/generos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MangaGeneroResouce {
    private static final Logger LOG = Logger.getLogger(MangaGeneroResouce.class);

    @Inject
    MangaGeneroService mangaGeneroService;

    @GET
    @Path("/getall")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int page , @QueryParam("size")  @DefaultValue("100") int size) {
        return Response.ok(mangaGeneroService.getAll(page, size)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(MangaGeneroDTO mangaGeneroDTO) {
        LOG.info("Inserindo um idioma.");
        try {
            MangaGeneroResponceDTO genero = mangaGeneroService.create(mangaGeneroDTO);
            return Response.status(Response.Status.CREATED).entity(genero).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de municipios.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, MangaGeneroDTO mangaGeneroDTO) {
        try {
            MangaGeneroResponceDTO genero = mangaGeneroService.update(id, mangaGeneroDTO);
            return Response.ok(genero).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        mangaGeneroService.delete(id);
    }


    @GET
    @Path("/count")
    public long count(){
        return mangaGeneroService.count();
    }

    @GET
    @Path("/search/{genero}")
    public List<MangaGeneroResponceDTO> search(@PathParam("genero") String genero){
        return mangaGeneroService.findByGenero(genero);

    }
}
