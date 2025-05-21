package io.github.rafaelaperruci.brasileiras_e_commerce.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens;

    public Venda() {}

    public Venda(String numeroVenda, LocalDate data, Double valorTotal, String enderecoEntrega) {
        this.numeroVenda = numeroVenda;
        this.data = data;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
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

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void addItens(ItemVenda item) {
        this.itens.add(item);
        item.setVenda(this);
    }
}
