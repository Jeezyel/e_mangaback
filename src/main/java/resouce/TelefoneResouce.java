package resouce;


import DTO.MunicipiosDTO;
import DTO.MunicipiosResponceDTO;
import DTO.TelefoneDTO;
import DTO.TelefoneResponceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import service.JwtService;
import service.TelefoneService;

import java.util.List;

@Path("/telefones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TelefoneResouce {

    private static final Logger LOG = Logger.getLogger(TelefoneResouce.class);

    @Inject
    TelefoneService telefoneService;
    @Inject
    JwtService jwtService;


    @GET
    @Path("/")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int index , @QueryParam("size")  @DefaultValue("100") int size) {
        LOG.info("buscando todos os municipios." );
        return Response.ok(telefoneService.getAll(index,size)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(TelefoneDTO dto) {
        LOG.info("Inserindo um telefone.");
        try {
            TelefoneResponceDTO telefone = telefoneService.create(dto);
            return Response.status(Response.Status.CREATED).entity(telefone).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de telefone.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, TelefoneDTO dto) {
        try {
            TelefoneResponceDTO telefone = telefoneService.update(id, dto);
            return Response.ok(telefone).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{Id}")
    @Transactional
    public void DeleteForId(@PathParam("Id") long id){
        telefoneService.delete(id);
    }

    @GET
    @Path("/{Id}")
    @Transactional
    public Response GetForId(@PathParam("Id") long id){
        return Response.ok(telefoneService.findById(id)).build();
    }

    @GET
    @Path("/findByNome/{numero}")
    public Response search(@PathParam("numero") String numero){
        return Response.ok(telefoneService.findByNumero(numero)).build();

    }

    @GET
    @Path("/count")
    public long count(){
        return telefoneService.count();
    }



}
