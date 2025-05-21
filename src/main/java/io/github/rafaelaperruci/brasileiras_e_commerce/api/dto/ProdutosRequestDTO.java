package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record ProdutosRequestDTO(

        @NotBlank
        String codigo,

        @NotBlank
        String descricao,

        @NotBlank
        String codigoBarra,

        @NotNull(message = "O valor de compra é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor de compra deve ser maior que zero")
        Double valorCompra,

        @NotNull(message = "O valor de venda é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor de venda deve ser maior que zero")
        Double valorVenda,

        @NotNull(message = "O estoque é obrigatório")
        @Min(value = 0, message = "O estoque não pode ser negativo")
        Integer estoque,

        @NotNull(message = "O ID do fornecedor é obrigatório")
        Long fornecedorId
) {
}
