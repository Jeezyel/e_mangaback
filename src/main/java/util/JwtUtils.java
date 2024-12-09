package util;

import io.smallrye.jwt.auth.principal.JWTParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Perfil;
import model.Usuario;

import java.util.List;
@ApplicationScoped
public class JwtUtils {

    @Inject
    JWTParser jwtParser;

    public boolean isAdmin(String token) {
        try {
            // Converte o claim "groups" para uma lista de strings
            @SuppressWarnings("unchecked")
            List<String> groups = (List<String>) jwtParser.parse(token).getClaim("groups");
            System.out.println("Grupos no token: " + groups);
            // Verifica se o grupo "admin" está presente
            return groups != null && groups.contains("admin");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario getUsuarioFromToken(String token) {
        try {
            var jwtClaims = jwtParser.parse(token); // Parseia o token

            // Extrai os claims necessários
            String nome = jwtClaims.getClaim("nome");
            String email = jwtClaims.getClaim("email");
            String perfil = jwtClaims.getClaim("perfil");
            String username = jwtClaims.getClaim("preferred_username"); // Claim comum para usernames
            String senha = jwtClaims.getClaim("senha"); // Use apenas se necessário (não recomendado armazenar senhas em tokens)

            // Popula o DTO
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setPerfil(Perfil.perfilSet(perfil));
            usuario.setUsername(username);
            usuario.setSenha(senha);

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro (pode lançar exceções ou usar Optional)
        }
    }
}

