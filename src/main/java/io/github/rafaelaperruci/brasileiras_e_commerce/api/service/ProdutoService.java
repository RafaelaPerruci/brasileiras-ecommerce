package io.github.rafaelaperruci.brasileiras_e_commerce.api.service;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ProdutoResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ProdutosDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Fornecedores;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.ItemVenda;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Produto;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(ProdutosDTO dto) {
        return produtoRepository.save(new Produto(dto));
    }

    public Page<ProdutoResponseDTO> getAllProdutos(Pageable pagination) {
        Page<Produto> produtos = produtoRepository.findAll(pagination);
        if (produtos.isEmpty()){
            return Page.empty();
        }
        return produtos.map(ProdutoResponseDTO::new);
    }

    public ProdutoResponseDTO findProdutoById(Long id) {
       Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null){
            return null;
        }
        return new ProdutoResponseDTO(produto);
    }

    public Optional<ProdutoResponseDTO> deleteProdutoById(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return Optional.empty();
        }
        produtoRepository.deleteById(id);
        return produto.map(ProdutoResponseDTO::new);
    }

    public boolean verificarEstoque(List<ItemVenda> itens) {

        boolean checkEstoque = false;
        for (ItemVenda item : itens){
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElse(null);
            if (produto != null){
                if(produto.getEstoque() > item.getQuantidade()){
                    produto.setEstoque(produto.getEstoque() - item.getQuantidade());
                    produtoRepository.save(produto);
                    checkEstoque = true;
                }else{
                    checkEstoque = false;
                    break;
                }
            }
        }
        return checkEstoque;
    }
}
