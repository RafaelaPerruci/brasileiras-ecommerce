package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Fornecedores;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

public record FornecedoresResponseDTO(
        Long id,
        String razaoSocial,
        String cnpj,
        String email,
        String telefone,
        String endereco
) {
    public FornecedoresResponseDTO(Fornecedores fornecedores) {
        this(fornecedores.getId(),fornecedores.getRazaoSocial(), fornecedores.getCnpj(), fornecedores.getEmail(), fornecedores.getTelefone(), fornecedores.getEndereco());
    }
}
