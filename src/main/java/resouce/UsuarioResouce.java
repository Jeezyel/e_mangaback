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
import model.ClassificacaoIndicativa;
import model.Perfil;
import model.Usuario;
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
    FileService fileService;

    @Inject
    JwtUtils jwtUtils;

    @Inject
    JsonWebToken jwt;




    private static final Logger LOG = Logger.getLogger(UsuarioResouce.class);

    @GET
    @Path("/")
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page
                            ,@QueryParam("size")  @DefaultValue("100") int size){
        return Response.ok(usuarioService.getAll(page, size)).build();
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insert(UsuarioDTO usuarioDTO, @HeaderParam("Authorization") String authToken) {
        LOG.info("inserindo um usuario.");
        try {
            UsuarioResponceDTO usuario = usuarioService.create(usuarioDTO);
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
            UsuarioResponceDTO usuario = usuarioService.update(id,usuarioDTO);
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

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Conta usuario.");
        return usuarioService.count();
    }

    @PUT
    @Transactional
    @Path("/updateprivilege/{idUserUpdate}")
    @RolesAllowed("Admin")
    public Response updateprivilege (@PathParam("idUserUpdate") Long idUserUpdate){

        String usuarioo = jwt.getSubject();
        LOG.info("O QUE FOI COLETADO DO TOKEN: " + usuarioo);

        try {
            UsuarioResponceDTO usuario = usuarioService.updateprivilege( usuarioo, idUserUpdate);
            return Response.ok().build();
        }catch (Exception e){
            LOG.error("erro ao dar privilegio: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }



    }

    @GET
    @Path("/perfil")
    public Response getPerfis() {
        return Response.ok(Perfil.values()).build();
    }

}
