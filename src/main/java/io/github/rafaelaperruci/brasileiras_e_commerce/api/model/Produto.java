package io.github.rafaelaperruci.brasileiras_e_commerce.api.model;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ProdutosDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String descricao;

    @Column(name = "codigo_barras", unique = true)
    private String codigoBarra;

    @Column(name = "valor_compra")
    private Double valorCompra;

    @Column(name = "valor_venda")
    private Double valorVenda;
    private Integer estoque;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedores fornecedor;

    public Produto() {}

    public Produto(String codigo, String descricao, String codigoBarra, Double valorCompra, Double valorVenda, Integer estoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.codigoBarra = codigoBarra;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.estoque = estoque;
    }
    public Produto(ProdutosDTO produtos) {
        this.codigo = produtos.codigo();
        this.descricao = produtos.descricao();
        this.codigoBarra = produtos.codigoBarra();
        this.valorCompra = produtos.valorCompra();
        this.valorVenda = produtos.valorVenda();
        this.estoque = produtos.estoque();
        this.fornecedor = new Fornecedores();
        this.fornecedor.setId(produtos.fornecedorId());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }
}
