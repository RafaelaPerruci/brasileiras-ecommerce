package io.github.rafaelaperruci.brasileiras_e_commerce.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record ClientesRequestDTO(

        @NotBlank
        String nome,

        @CPF(message = "CPF inválido")
        @NotBlank(message = "O campo CPF é obrigatório")
        String cpf,

        @Email(message = "O email fornecido não é um email válido")
        String email,

        @Pattern(regexp = "\\d{10,11}", message = "Formato de telefone inválido")
        String telefone,

        @NotBlank()
        String endereco
        ) {
}
