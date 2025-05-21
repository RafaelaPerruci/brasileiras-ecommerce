package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemVendaDTO(

        @NotBlank(message = "O id da venda é obrigatório")
        Long vendaId,

        @NotBlank(message = "O id do produto é obrigatório")
        Long produtoId,

        @NotBlank(message = "A quantidade dos itens é obrigatória")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
        Integer quantidade,

        @NotNull(message = "O valor unitário é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor unitário deve ser maior que zero")
        Double valorUnitario
) {
}
