package resouce;

import DTO.MangaDTO;
import DTO.MangaResponceDTO;
import DTO.PedidoDTO;
import DTO.PedidoResponceDTO;
import aplication.Result;
import form.ConsultaImageForm;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.ClassificacaoIndicativa;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import service.FileService;
import service.MangaService;
import service.PedidoService;

@Path("/mangas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResouce {

    private static final Logger LOG = Logger.getLogger(PedidoResouce.class);

    @Inject
    PedidoService pedidoService;

    @Inject
    FileService fileService;

    @GET
    @Path("/")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("100") int size) {
        return Response.ok(pedidoService.getAll(page, size)).build();
    }

    @POST
    @Transactional
    @Path("/insert")
    public Response insert(PedidoDTO pedidoDTO) {
        LOG.info("Inserindo um novo mangá.");
        try {
            PedidoResponceDTO pedido = pedidoService.create(pedidoDTO);
            return Response.status(Response.Status.CREATED).entity(pedido).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar o mangá: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, PedidoDTO pedidoDTO) {
        try {
            PedidoResponceDTO pedido = pedidoService.update(id, pedidoDTO);
            return Response.ok(pedido).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                           .entity(e.getMessage())
                           .build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(e.getMessage())
                           .build();
        }        
    }

    @PUT
    @Path("/updatestatus/{id}{status}")
    @Transactional
    public Response updateStatus(@PathParam("id") Long id, @PathParam("status") String status) {
        try {
            PedidoResponceDTO pedido = pedidoService.updateStatus(id, status);
            return Response.ok(pedido).build();
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        try {
            pedidoService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mangá não encontrado").build();
        }
    }

    @GET
    @Path("/count")
    public long count() {
        return pedidoService.count();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        try {
            PedidoResponceDTO pedido = pedidoService.findById(id);
            return Response.ok(pedido).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mangá não encontrado").build();
        }
    }

    @GET
    @Path("/{iduser}")
    public Response findByUser(@PathParam("iduser") Long iduser) {
        try {
            PedidoResponceDTO pedido = pedidoService.findByUser(iduser);
            return Response.ok(pedido).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mangá não encontrado").build();
        }
    }
}