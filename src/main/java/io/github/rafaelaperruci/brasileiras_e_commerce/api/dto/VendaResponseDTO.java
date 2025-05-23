package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.enums.MetodoPagamento;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.ItemVenda;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Venda;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

public record VendaResponseDTO(
        Long id,
        String numeroVenda,
        LocalDate data,
        Double valorTotal,
        String enderecoEntrega,
        Long clienteId,
        MetodoPagamento metodoPagamento,
        List<ItemVendaResponseDTO> itens
) {
    public VendaResponseDTO(Venda venda){
        this(venda.getId(), venda.getNumeroVenda(), venda.getData(), venda.getValorTotal(), venda.getEnderecoEntrega(),
                venda.getCliente().getId(), venda.getPagamento(),
                venda.getItens().stream()
                        .map(ItemVendaResponseDTO::new)
                        .toList());
    }
}
