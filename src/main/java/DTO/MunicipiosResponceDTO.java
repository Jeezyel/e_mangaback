package DTO;

import model.Estado;
import model.Municipio;

public record MunicipiosResponceDTO(
        long idMunicipio,
        String nome,
        String nomeEstado
) {
    public MunicipiosResponceDTO (Municipio municipio){
        this(
                municipio.getId(),
                municipio.getNome(),
                municipio.getEstado().getNome()
        );
    }
}
