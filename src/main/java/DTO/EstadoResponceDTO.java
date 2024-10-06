package DTO;


import model.Estado;

import java.util.List;

public record EstadoResponceDTO(
        Long id,
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

    /*
    modelo do professorA
    public static EstadoResponceDTO valueOf(Estado estado) {
        return new EstadoResponceDTO(
                estado.getId(),
                estado.getNome(),
                estado.getSigla());
    }
     */
}