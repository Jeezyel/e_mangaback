package resouce;


import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;
import aplication.Result;
import form.ConsultaImageForm;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Perfil;
import model.Usuario;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import service.FileService;
import service.UsuarioService;
import util.JwtUtils;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.Collections;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResouce {

    @Inject
    UsuarioService usuarioService;

    @Inject
    FileService fileService;

    @Inject
    JwtUtils jwtUtils;




    private static final Logger LOG = Logger.getLogger(UsuarioResouce.class);

    @GET
    @Path("/")
    public Response getAll(){
        return Response.ok(usuarioService.getAll()).build();
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insert(UsuarioDTO usuarioDTO, @HeaderParam("Authorization") String authToken) {
        try {
            // Valida se o token pertence a um administrador
            boolean isAdminRequest = jwtUtils.isAdmin(authToken);
            LOG.info("isAdminRequest: " + isAdminRequest);

            // Se o novo usuário é administrador e o requisitante não for, lança erro
            if (usuarioDTO.perfil() != null && usuarioDTO.perfil().equals(Collections.singleton(Perfil.ADMIN))  && isAdminRequest) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"Somente administradores podem criar novos administradores.\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            UsuarioResponceDTO usuario = usuarioService.create(usuarioDTO, isAdminRequest);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (Exception e) {
            LOG.error("Erro ao criar usuário: ", e);
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"" + e.getMessage() + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
        }
    }   

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, UsuarioDTO usuarioDTO, @HeaderParam("Authorization") String authToken) {
        LOG.info("Atualiza um usuario.");
        try {
            // Valida se o token pertence a um administrador
            boolean isAdminRequest = jwtUtils.isAdmin(authToken);
            LOG.info("isAdminRequest: " + isAdminRequest);

            // Se o novo usuário é administrador e o requisitante não for, lança erro
            if (usuarioDTO.perfil() != null && usuarioDTO.perfil().equals(Collections.singleton(Perfil.ADMIN)) && isAdminRequest) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"Somente administradores podem torna-lo um novo administradores.\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            usuarioService.update(id, usuarioDTO);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de updat de contato.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response DeleteById(@PathParam("id") Long id){
        usuarioService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(usuarioService.findById(id)).build();
    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ConsultaImageForm form) {
        LOG.info("nome imagem: "+form.getNomeImagem());
        System.out.println("nome imagem: "+form.getNomeImagem());

        fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        System.out.println(nomeImagem);
        Response.ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

    @PUT
    @Path("/updateprivilege/{idUserUpdate}")
    @Transactional
    public Response updateprivilege (@PathParam("idUserUpdate") Long idUserUpdate, @HeaderParam("Authorization") String authToken){
        LOG.info("Authorization: "+ authToken);

        Usuario toke = jwtUtils.getUsuarioFromToken(authToken);

        LOG.info("toke maluco: "+toke.getUsername());
        return null;
    }
}
