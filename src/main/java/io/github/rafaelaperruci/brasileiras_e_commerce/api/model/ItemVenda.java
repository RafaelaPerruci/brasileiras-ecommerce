package io.github.rafaelaperruci.brasileiras_e_commerce.api.model;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ItemVendaDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    @Column(name = "preco_unitario")
    private Double valorUnitario;

    private Double subtotal;

    public ItemVenda() {}

    public ItemVenda(Venda venda, Produto produto, Integer quantidade, Double valorUnitario) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.subtotal = quantidade * valorUnitario;
    }

    public ItemVenda(ItemVendaDTO itemVendaDTO) {
        this.produto = new Produto();
        this.produto.setId(itemVendaDTO.produtoId());
        this.quantidade = itemVendaDTO.quantidade();
        this.valorUnitario = itemVendaDTO.valorUnitario();
        this.subtotal = itemVendaDTO.quantidade() * itemVendaDTO.valorUnitario();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
