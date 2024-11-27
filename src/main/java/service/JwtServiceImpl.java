package service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import DTO.UsuarioResponceDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioResponceDTO dto) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<String>();

        String perfil;
        if(dto.administrador()){
            perfil = "admin";
        }else{
            perfil = "cliente";
        }
        
        roles.add(perfil);

        return Jwt.issuer("unitins-jwt")
            .subject(dto.username())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();

    }
    
}
