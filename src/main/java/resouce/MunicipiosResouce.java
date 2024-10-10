package resouce;


import DTO.MunicipiosDTO;
import DTO.MunicipiosResponceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import jakarta.ws.rs.core.Response.Status;

import jakarta.transaction.Transactional;
import service.MunicipioService;

import java.util.List;


@Path("/municipios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MunicipiosResouce {

    private static final Logger LOG = Logger.getLogger(MunicipiosResouce.class);
    @Inject
    MunicipioService municipioService;

    @GET
    @Path("/getall")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int index ,@QueryParam("size")  @DefaultValue("100") int size) {
        LOG.info("buscando todos os municipios." );
        return Response.ok(municipioService.getAll(index,size)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(MunicipiosDTO municipioDTO) {
        LOG.info("Inserindo um municipio.");
        try {
            MunicipiosResponceDTO municipio = municipioService.create(municipioDTO);
            return Response.status(Status.CREATED).entity(municipio).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de municipios.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, MunicipiosDTO dto) {
        try {
            MunicipiosResponceDTO municipio = municipioService.update(id, dto);
            return Response.ok(municipio).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        municipioService.delete(id);
    }


    @GET
    @Path("/count")
    public long count(){
        return municipioService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<MunicipiosResponceDTO> search(@PathParam("nome") String nome){
        return municipioService.findByNome(nome);

    }

}