package service;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import DTO.UsuarioResponceDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Perfil;
import model.Usuario;
import org.jboss.logging.Logger;
import repository.UsuarioRepository;


@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Logger LOG = Logger.getLogger(JwtServiceImpl.class);

    @Inject
    UsuarioRepository usuarioRepository;


    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioResponceDTO dto) {
        return generateJwt(usuarioRepository.findById(dto.id()));
    }

    private String generateJwt(Usuario usuario) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = usuario.getPerfil()
                .stream().map(Perfil::getLabel)
                .collect(Collectors.toSet());

        return Jwt.issuer("unitins-jwt")
            .subject(usuario.getUsername())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();

    }


}
