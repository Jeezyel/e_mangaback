package resouce;


import DTO.IdiomaDTO;
import DTO.IdiomaResposceDTO;
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
import service.MangaService;

import java.util.List;

@Path("/mangas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MangaResouce {
    private static final Logger LOG = Logger.getLogger(MangaResouce.class);

    @Inject
    MangaService mangaService;


    @GET
    @Path("/")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int page , @QueryParam("size")  @DefaultValue("100") int size) {
        return Response.ok(mangaService.getAll(page, size)).build();
    }

    //para crar a tela com os manga
    //retorna tudo sem ordenação com paginação
    @GET
    @Path("/search/{nome}")
    public Response search(@QueryParam("index") @DefaultValue("0") int page,
                           @QueryParam("size")  @DefaultValue("100") int size,
                           @PathParam("nome") String nome) {
        return Response.ok(mangaService.search(page, size, nome)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(MangaDTO mangaDTO) {
        LOG.info("Inserindo um idioma.");
        try {
            MangaResponceDTO manga = mangaService.create(mangaDTO);
            return Response.status(Response.Status.CREATED).entity(manga).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de municipios.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, MangaDTO mangaDTO) {
        try {
            MangaResponceDTO manga = mangaService.update(id, mangaDTO);
            return Response.ok(manga).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        mangaService.delete(id);
    }


    @GET
    @Path("/count")
    public long count(){
        return mangaService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<MangaResponceDTO> search(@PathParam("nome") String nome){
        return mangaService.findByNome(nome);

    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando ID de estados.");
        return Response.ok(mangaService.findById(id)).build();
    }
}
