package DTO;


import model.Estado;

import java.util.List;

public record EstadoResponceDTO(
        Long idEstado,
        String nome,
        String sigla
) {
    public  EstadoResponceDTO (Estado estado) {
        this(
                estado.getId(),
                estado.getNome(),
                estado.getSigla()
        );
    }
}