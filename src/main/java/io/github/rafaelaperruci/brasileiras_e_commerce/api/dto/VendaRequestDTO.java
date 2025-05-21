package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

public record VendaRequestDTO(
        @NotBlank(message = "O número da venda é obrigatório")
        String numeroVenda,

        @NotNull(message = "A data da venda é obrigatória")
        @PastOrPresent(message = "A data da venda não pode ser no futuro")
        LocalDate data,

        @NotNull(message = "O valor total é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor total deve ser maior que zero")
        Double valorTotal,

        @NotBlank(message = "O endereço de entrega é obrigatório")
        String enderecoEntrega,

        @NotBlank(message = "O ID do cliente é obrigatório")
        Long clienteId,

        List<ItemVendaDTO> itens
) {
}
