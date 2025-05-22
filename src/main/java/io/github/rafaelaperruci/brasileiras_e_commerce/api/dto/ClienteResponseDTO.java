package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteResponseDTO(

        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,

        String endereco
) {
    public ClienteResponseDTO(Clientes cliente) {
        this(cliente.getId(),cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(), cliente.getEndereco());
    }
}
