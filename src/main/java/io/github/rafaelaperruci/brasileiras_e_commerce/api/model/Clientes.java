package io.github.rafaelaperruci.brasileiras_e_commerce.api.model;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClientesDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    @Column(name = "endereco_entrega")
    private String endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> compra = new ArrayList<>();

    public Clientes() {}

    public Clientes(String nome, String cpf, String email, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Clientes(ClientesDTO clienteDTO) {
        this.nome = clienteDTO.nome();
        this.cpf = clienteDTO.cpf();
        this.email = clienteDTO.email();
        this.telefone = clienteDTO.telefone();
        this.endereco = clienteDTO.endereco();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotBlank(message = "O ID do cliente é obrigatório") Long aLong) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Venda> getCompra() {
        return compra;
    }

    public void setCompra(Venda compra) {
        this.compra.add(compra);
        compra.setCliente(this);
    }
}
