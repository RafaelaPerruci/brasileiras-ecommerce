package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Fornecedores;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Produto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoResponseDTO(
        Long id,
        String codigo,
        String descricao,
        String codigoBarra,
        Double valorCompra,
        Double valorVenda,
        Integer estoque,
        Long fornecedorId
) {
    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(),
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getCodigoBarra(),
                produto.getValorCompra(),
                produto.getValorVenda(),
                produto.getEstoque(),
                produto.getFornecedor().getId());
    }

}
