package util;

import io.smallrye.jwt.auth.principal.JWTParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
}

