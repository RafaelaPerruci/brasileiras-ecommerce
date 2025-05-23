package io.github.rafaelaperruci.brasileiras_e_commerce.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MetodoPagamento {

    CARTAO_CREDITO("crédito"),
    CARTAO_DEBITO("débito"),
    PIX("pix"),
    BOLETO("boleto"),
    DINHEIRO("dinheiro");

    private String metodo;

    MetodoPagamento(String descricao) {
        this.metodo = descricao;

    }
    @JsonValue
    public String getMetodo() {
        return metodo;
    }

    @JsonCreator
    public static MetodoPagamento fromDescricao(String descricao) {
        for (MetodoPagamento metodo : MetodoPagamento.values()) {
            if (metodo.getMetodo().equalsIgnoreCase(descricao)) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Método de pagamento inválido: " + descricao);
    }
}
