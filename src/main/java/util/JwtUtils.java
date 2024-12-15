package util;

import io.smallrye.jwt.auth.principal.JWTParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class JwtUtils {

    @Inject
    JWTParser jwtParser;

    /**
     * Verifica se o token pertence a um administrador (perfil ADMIN).
     *
     * @param token O token JWT do usuário.
     * @return `true` se o usuário tiver o perfil ADMIN, caso contrário, `false`.
     */
    public boolean isAdmin(String token) {
        try {
            @SuppressWarnings("unchecked")
            List<String> groups = jwtParser.parse(token).getClaim("groups");
            System.out.println("Grupos no token: " + groups);
            return groups != null && groups.contains("ADMIN");
        } catch (Exception e) {
            System.err.println("Erro ao verificar se o usuário é admin: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extrai o username do token JWT.
     *
     * @param authToken O token JWT do usuário.
     * @return O username do usuário ou `null` em caso de erro.
     */
    public String extractUsername(String authToken) {
        try {
            return jwtParser.parse(authToken).getClaim("username");
        } catch (Exception e) {
            System.err.println("Erro ao extrair username do token: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Extrai o perfil do usuário do token JWT.
     *
     * @param token O token JWT do usuário.
     * @return O perfil do usuário (e.g., USER ou ADMIN) ou `null` em caso de erro.
     */
    public String extractUsuarioPerfil(String token) {
        try {
            return jwtParser.parse(token).getClaim("perfil");
        } catch (Exception e) {
            System.err.println("Erro ao extrair perfil do usuário: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
