package resouce;

import DTO.AuthUsuarioDTO;
import DTO.UsuarioResponceDTO;
import org.jboss.logging.Logger;
import service.HashService;
import service.JwtService;
import service.UsuarioService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResouce {

    private static final Logger LOG = Logger.getLogger(AuthResouce.class);

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthUsuarioDTO authDTO) {

        LOG.info("pegando o hesh da senha");
        String hash = hashService.getHashSenha(authDTO.senha());

        LOG.info("buscando no banco o usuario ");
        UsuarioResponceDTO usuario = usuarioService.findByUsernameAndSenha(authDTO.username(), hash);

        if (usuario == null) {
            LOG.info("usuario n encontrado ");
            return Response.status(Status.NOT_FOUND)
                    .entity("Usuario não encontrado").build();
        }
        return Response.ok(usuario)
                .header("Authorization", "Bearer " + jwtService.generateJwt(usuario))
                .build();

    }

}
