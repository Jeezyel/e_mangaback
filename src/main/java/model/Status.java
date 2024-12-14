package model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collections;
import java.util.Set;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    PEDIDO_PAGO(1 ,"Pedido Pago"),
    EM_PREPARACAO(2 ,"Em Preparação"),
    EM_ROTA_DE_ENTREGA( 3,"Em Rota de Entrega"),
    ENTREGUE(4 ,"Entregue");

    private int id;
    private String label;

    Status(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Status valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for(Status perfil : Status.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }

    public static Set<Status> statusSet (String status){
        if (status.equals("PEDIDO_PAGO") )
            return Collections.singleton(Status.PEDIDO_PAGO);
        else if (status.equals("EM_PREPARACAO")) {
            return Collections.singleton(Status.EM_PREPARACAO);
        }
        else if (status.equals("EM_ROTA_DE_ENTREGA")) {
            return Collections.singleton(Status.EM_ROTA_DE_ENTREGA);
        }
        else if (status.equals("ENTREGUE")) {
            return Collections.singleton(Status.ENTREGUE);
        }
        return null;
    }
}