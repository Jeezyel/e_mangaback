package resouce;

import DTO.*;
import aplication.Result;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.FormaDePagamento;
import model.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import service.PedidoService;

@Path("/pedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResouce {

    private static final Logger LOG = Logger.getLogger(PedidoResouce.class);

    @Inject
    PedidoService pedidoService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/")
    public Response getAll(@QueryParam("index") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("100") int size) {
        return Response.ok(pedidoService.getAll(page, size)).build();
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insert(UsuarioPedidoJuncaoDTO usuarioPedidoJuncaoDTO) {
        LOG.info("Inserindo um novo pedido.");
        try {
            UsuarioPedidoJuncaoResponceDTO pedido = pedidoService.create(usuarioPedidoJuncaoDTO);
            return Response.status(Response.Status.CREATED).entity(pedido).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        } /*catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar o pedido: " + e.getMessage())
                    .build();
        }*/
    }

    /*@PUT
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
    }*/

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
    @Path("/{idUser}")
    public Response findByUser(@PathParam("idUser") Long iduser) {
        try {
            PedidoResponceDTO pedido = pedidoService.findByUser(iduser);
            return Response.ok(pedido).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mangá não encontrado").build();
        }
    }

    @GET
    @Path("/history")
    @RolesAllowed("USER")
    public Response history (){
        String userName = jwt.getSubject();
        return Response.ok(pedidoService.History(userName)).build();
    }

    @GET
    @Path("/formaDePagamento")
    public Response getFormaDePagamento() {
        return Response.ok(FormaDePagamento.values()).build();
    }

    @GET
    @Path("/status")
    public Response getStatus() {
        return Response.ok(Status.values()).build();
    }

}