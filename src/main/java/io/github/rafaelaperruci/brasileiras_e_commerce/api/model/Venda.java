package io.github.rafaelaperruci.brasileiras_e_commerce.api.model;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.VendaRequestDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.enums.MetodoPagamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_venda", unique = true)
    private String numeroVenda;

    @Column(name = "data_venda")
    private LocalDate data;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento")
    private MetodoPagamento pagamento;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda() {}

    public Venda(String numeroVenda, LocalDate data, Double valorTotal, String enderecoEntrega, MetodoPagamento metodoPagamento) {
        this.numeroVenda = numeroVenda;
        this.data = data;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
        this.pagamento = metodoPagamento;

    }

    public Venda(VendaRequestDTO dto, Clientes cliente) {
        this.numeroVenda = dto.numeroVenda();
        this.data = dto.data();
        this.valorTotal = dto.itens().stream().mapToDouble(item -> item.quantidade() * item.valorUnitario())
                .sum();
        this.pagamento = dto.metodoPagamento();
        this.enderecoEntrega = dto.enderecoEntrega();
        this.cliente = cliente;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroVenda() {
        return numeroVenda;
    }

    public void setNumeroVenda(String numeroVenda) {
        this.numeroVenda = numeroVenda;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public MetodoPagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(MetodoPagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void addItens(ItemVenda item) {
        this.itens.add(item);
        item.setVenda(this);
    }
}
