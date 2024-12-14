package resouce;

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

import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/generos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MangaGeneroResouce {
    private static final Logger LOG = Logger.getLogger(MangaGeneroResouce.class);

    @Inject
    MangaGeneroService mangaGeneroService;

    @GET
    @Path("/")
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page , @QueryParam("size")  @DefaultValue("100") int size) {
        return Response.ok(mangaGeneroService.getAll(page, size)).build();
    }

    //para crar a tela com os manga
    //retorna tudo sem ordenação com paginação
    @GET
    @Path("/search/{genero}")
    public Response search(@QueryParam("index") @DefaultValue("0") int page,
                           @QueryParam("size")  @DefaultValue("100") int size,
                           @PathParam("genero") String genero) {
        return Response.ok(mangaGeneroService.search(page, size, genero)).build();
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
            mangaGeneroService.update(id, mangaGeneroDTO);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de updat de estados.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        mangaGeneroService.delete(id);
    }

    @GET
    @Path("/{Id}")
    @Transactional
    public Response GetForId(@PathParam("Id") long id){
        return Response.ok(mangaGeneroService.findById(id)).build();
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
