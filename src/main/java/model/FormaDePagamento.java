package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collections;
import java.util.Set;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FormaDePagamento {

    PIX(1, "Pix"),
    CARTAO(2, "Cartão");

    private int id;
    private String label;

    FormaDePagamento(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static FormaDePagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for(FormaDePagamento formaDePagamento : FormaDePagamento.values()) {
            if (id.equals(formaDePagamento.getId()))
                return formaDePagamento;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }

    public static Set<FormaDePagamento> formaDePagamentoSet (String formaDePagamento){
        if (formaDePagamento.toUpperCase() == "ADMIN" || formaDePagamento != null)
            return Collections.singleton(FormaDePagamento.PIX);


        return Collections.singleton(FormaDePagamento.CARTAO);
    }
}
