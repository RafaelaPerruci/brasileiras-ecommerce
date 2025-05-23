package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.ItemVenda;

public record ItemVendaResponseDTO(
        Long vendaId,
        Long produtoId,
        Integer quantidade,
        Double valorUnitario,
        Double subtotal
) {


    public ItemVendaResponseDTO(ItemVenda itemVenda) {
        this(itemVenda.getVenda().getId(), itemVenda.getProduto().getId(), itemVenda.getQuantidade(), itemVenda.getValorUnitario(), itemVenda.getSubtotal());
    }
}
