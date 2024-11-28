package resouce;

import DTO.AuthUsuarioDTO;
import DTO.UsuarioResponceDTO;
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

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthUsuarioDTO authDTO) {
        try {

            String hash = hashService.getHashSenha(authDTO.senha());
            
            UsuarioResponceDTO usuario = usuarioService.findByUsernameAndSenha(
                authDTO.username(), 
                hash, 
                authDTO.administrador()
            );
            // Verifica se o usuário foi encontrado
            if (usuario == null) {
                return Response.status(Status.NOT_FOUND)
                    .entity("Usuário não encontrado ou perfil incorreto").build();
            }
            // Gera o token JWT e retorna o usuário e o token
            String token = jwtService.generateJwt(usuario);
            return Response.ok(usuario)
                .header("Authorization", token)
                .build();
        } catch (Exception e) {

            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR)
            .entity("Erro interno no sistema: " + e.getMessage()).build();
        }
    }    
}
