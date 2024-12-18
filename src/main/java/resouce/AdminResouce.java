package resouce;


import DTO.EnderecoDTO;
import DTO.TelefoneDTO;
import DTO.UsuarioDTO;
import DTO.UsuarioResponceDTO;
import aplication.Result;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Endereco;
import model.Perfil;
import model.Telefone;
import model.Usuario;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import repository.UsuarioRepository;
import service.AdminService;
import service.FileService;
import service.UsuarioService;
import util.JwtUtils;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResouce {

    @Inject
    AdminService adminService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    FileService fileService;

    @Inject
    JwtUtils jwtUtils;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(AdminResouce.class);
        
    @POST
    @Path("/insert")
    @Transactional
    public Response insert(UsuarioDTO usuarioDTO, @HeaderParam("Authorization") String authToken) {
            
        LOG.info("inserindo um usuario.");
            
        String username = jwt.getSubject();
        Usuario currentUser = usuarioRepository.findByUsername(username);

        LOG.info("usuario: " + username );
        LOG.error("usuario: " + username );
        
        // Validate admin privileges for admin creation
        if (usuarioDTO.perfil() != null && Perfil.ADMIN.equals(Perfil.valueOf(usuarioDTO.perfil())) 
         && (currentUser == null || currentUser.getPerfil() != Perfil.ADMIN)) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Somente admins podem criar outro admin").build();
        }

        try {
            UsuarioResponceDTO usuario = adminService.create(usuarioDTO);
            return Response.ok(usuario).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("inserir de contato.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }

    }

    /*@PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, UsuarioDTO usuarioDTO) {
        LOG.info("Atualiza um usuario.");
        try {
            UsuarioResponceDTO usuario = adminService.update(id,usuarioDTO);
            return Response.ok(usuario).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de updat de contato.");
            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        }
    }*/

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response DeleteById(@PathParam("id") Long id){
        adminService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @POST
    @Path("/endereco/{id}")
    @Transactional
    public Response addEndereco(@PathParam("idEndereco") Long id, EnderecoDTO enderecoDTO) {
        LOG.info("Adding address to user.");
        try {
            UsuarioResponceDTO updateUser = adminService.addEndereco(id, enderecoDTO);
            return Response.ok(updateUser).build();
        } catch (Exception e) {
            LOG.error("Error adding address: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/telefone/{id}")
    @Transactional
    public Response addTelefone(@PathParam("idTelefone") Long id, TelefoneDTO telefoneDTO) {
        LOG.info("Adding address to user.");
        try {
            UsuarioResponceDTO updateUser = adminService.addTelefone(id, telefoneDTO);
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
            return Response.ok(adminService.getEnderecos(id)).build();
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
            return Response.ok(adminService.getTelefones(id)).build();
        } catch (Exception e) {
            LOG.error("Error fetching addresses: ", e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/")
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page
                            ,@QueryParam("size")  @DefaultValue("100") int size){
        return Response.ok(adminService.getAll(page, size)).build();
    }

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Conta usuario.");
        return adminService.count();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(adminService.findById(id)).build();
    }

    @GET
    @Path("/perfil")
    public Response getPerfis() {
        return Response.ok(Perfil.values()).build();
    }

}
