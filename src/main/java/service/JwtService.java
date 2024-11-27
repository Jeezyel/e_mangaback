package service;

import DTO.UsuarioResponceDTO;

public interface JwtService {
    public String generateJwt(UsuarioResponceDTO dto);
}
