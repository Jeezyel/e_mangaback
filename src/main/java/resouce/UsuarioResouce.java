package resouce;


import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;
import aplication.Result;
import form.ConsultaImageForm;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import model.Endereco;
import model.Telefone;
import model.Perfil;
import model.Usuario;
import repository.UsuarioRepository;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
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
    UsuarioRepository usuarioRepository;

    @Inject
    FileService fileService;

    @Inject
    JwtUtils jwtUtils;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(UsuarioResouce.class);
        
    @POST
    @Path("/insert")
    @Transactional
    public Response insert(UsuarioDTO usuarioDTO, @HeaderParam("Authorization") String authToken) {
            
        LOG.info("inserindo um usuario.");
            
        String username = jwtUtils.extractUsername(authToken);
        Usuario currentUser = usuarioRepository.findByUsername(username);
        
        // Validate admin privileges for admin creation
        if (usuarioDTO.perfil() != null && Perfil.ADMIN.equals(Perfil.valueOf(usuarioDTO.perfil())) 
         && (currentUser == null || currentUser.getPerfil() != Perfil.ADMIN)) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Somente admins podem criar outro admin").build();
        }

        try {
            UsuarioResponceDTO usuario = usuarioService.create(usuarioDTO, authToken);
            return Response.ok(usuario).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("inserir de contato.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }

    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, UsuarioDTO usuarioDTO, @HeaderParam("Authorization") String authToken) {
        LOG.info("Atualiza um usuario.");
        try {
            UsuarioResponceDTO usuario = usuarioService.update(id,usuarioDTO, authToken);
            return Response.ok(usuario).build();
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
    @Path("/userLogin")
    public  Response userLogin (){
        LOG.info("coletando usuario");

        String user = jwt.getSubject();

        LOG.info("USUARIO: " + user);
        return Response.ok(usuarioService.userLogin(user)).build();
    }
    
    @POST
    @Path("/endereco/{id}")
    @Transactional
    public Response addEndereco(@PathParam("idEndereco") Long id, Endereco endereco) {
        LOG.info("Adding address to user.");
        try {
            UsuarioResponceDTO updateUser = usuarioService.addEndereco(id, endereco);
            return Response.ok(updateUser).build();
        } catch (Exception e) {
            LOG.error("Error adding address: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/telefone/{id}")
    @Transactional
    public Response addTelefone(@PathParam("idTelefone") Long id, Telefone telefone) {
        LOG.info("Adding address to user.");
        try {
            UsuarioResponceDTO updateUser = usuarioService.addTelefone(id, telefone);
            return Response.ok(updateUser).build();
        } catch (Exception e) {
            LOG.error("Error adding address: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/endereco")
    public Response getEndereco(@PathParam("idEndereco") Long id) {
        LOG.info("Fetching addresses for user.");
        try {
            return Response.ok(usuarioService.getEnderecos(id)).build();
        } catch (Exception e) {
            LOG.error("Error fetching addresses: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/telefone")
    public Response getTelefone(@PathParam("idTelefone") Long id) {
        LOG.info("Fetching addresses for user.");
        try {
            return Response.ok(usuarioService.getTelefones(id)).build();
        } catch (Exception e) {
            LOG.error("Error fetching addresses: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/")
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page
                            ,@QueryParam("size")  @DefaultValue("100") int size){
        return Response.ok(usuarioService.getAll(page, size)).build();
    }

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Conta usuario.");
        return usuarioService.count();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(usuarioService.findById(id)).build();
    }

    @GET
    @Path("/perfil")
    public Response getPerfis() {
        return Response.ok(Perfil.values()).build();
    }

}
