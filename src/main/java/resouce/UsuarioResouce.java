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
import org.jboss.logging.Logger;
import service.FileService;
import service.UsuarioService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResouce {
    @Inject
    UsuarioService usuarioService;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(UsuarioResouce.class);

    @GET
    @Path("/")
    public Response getAll(){
        return Response.ok(usuarioService.getAll()).build();
    }

    @POST
    @Path("/insert")
    @Transactional
    public Response insert(UsuarioDTO usuarioDTO) {
        try {
            UsuarioResponceDTO usuario = usuarioService.create(usuarioDTO);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de usuario.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, UsuarioDTO usuarioDTO) {
        LOG.info("Atualiza um usuario.");
        try {
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
}
