package DTO;

import model.Municipio;

public record MunicipiosResponceDTO(
        long idMunicipio,
        String nome,

        long idEstado
) {
    public MunicipiosResponceDTO (Municipio municipio){
        this(
                municipio.getId(),
                municipio.getNome(),
                municipio.getEstado().getId()
        );
    }
}
