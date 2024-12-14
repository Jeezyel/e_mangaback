package resouce;


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
import service.IdiomaService;

import java.util.List;

@Path("/idiomas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IdiomaResouce {
    private static final Logger LOG = Logger.getLogger(IdiomaResouce.class);

    @Inject
    IdiomaService idiomaService;

    @GET
    @Path("/")
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page , @QueryParam("size")  @DefaultValue("100") int size) {
        return Response.ok(idiomaService.getAll(page, size)).build();
    }

    //para crar a tela com os manga
    //retorna tudo sem ordenação com paginação
    @GET
    @Path("/search/{idioma}")
    public Response search(@QueryParam("index") @DefaultValue("0") int page,
                           @QueryParam("size")  @DefaultValue("100") int size,
                           @PathParam("idioma") String idioma) {
        return Response.ok(idiomaService.search(page, size, idioma)).build();
    }


    @POST
    @Transactional
    @Path("/insert")
    public Response insert(IdiomaDTO idiomaDTO) {
        LOG.info("Inserindo um idioma.");
        try {
            IdiomaResposceDTO idioma = idiomaService.create(idiomaDTO);
            return Response.status(Response.Status.CREATED).entity(idioma).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de municipios.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, IdiomaDTO idiomaDTO) {
        try {
            IdiomaResposceDTO idioma = idiomaService.update(id, idiomaDTO);
            return Response.ok(idioma).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        idiomaService.delete(id);
    }

    @GET
    @Path("/{Id}")
    @Transactional
    public Response GetForId(@PathParam("Id") long id){
        return Response.ok(idiomaService.findById(id)).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return idiomaService.count();
    }

    @GET
    @Path("/findByIdioma/{idioma}")
    public List<IdiomaResposceDTO> search(@PathParam("idioma") String idioma){
        return idiomaService.findByIdioma(idioma);

    }
}
