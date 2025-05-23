package io.github.rafaelaperruci.brasileiras_e_commerce.api.service;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ItemVendaDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.VendaRequestDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.VendaResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.ItemVenda;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Produto;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Venda;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.ClientesRepository;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.ItemVendaRepository;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.ProdutoRepository;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private VendaRepository vendaRepository;
    private ClientesRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    public VendaService(VendaRepository vendaRepository, ClientesRepository clienteRepository, ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository  = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    @Transactional
    public VendaResponseDTO createVenda(VendaRequestDTO dto) {
        Clientes cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        List<Long> ids = dto.itens().stream()
                .map(ItemVendaDTO::produtoId)
                .toList();

        List<Produto> produtos = produtoRepository.findAllById(ids);
        if (produtos.size() != ids.size()) {
            throw new RuntimeException("Um ou mais produtos não foram encontrados.");
        }
        List<ItemVenda> itens = dto.itens().stream().map(ItemVenda::new).toList();
        Venda venda = new Venda(dto, cliente);
        itens.forEach(item -> item.setVenda(venda));

        boolean b = produtoService.verificarEstoque(itens);
        if (!b){
            throw new RuntimeException("Não há estoque suficiente para a venda.");
        }
        venda.setItens(itens);
        vendaRepository.save(venda);
        return new VendaResponseDTO(venda);

    }

    public Page<VendaResponseDTO> getAllVendas(Pageable pagination) {
        Page<Venda> vendas = vendaRepository.findAll(pagination);
        if (vendas.isEmpty()) {
            return Page.empty();
        }
        return vendas.map(VendaResponseDTO::new);
    }
     public VendaResponseDTO findVendaById(Long id) {
        Venda venda = vendaRepository.findById(id).orElse(null);
        if (venda == null) {
            return null;
        }
        return new VendaResponseDTO(venda);
     }

     public Optional<VendaResponseDTO> deleteVendaById(Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
         if (venda.isEmpty()) {
             return Optional.empty();
         }
         vendaRepository.deleteById(id);
         return venda.map(VendaResponseDTO::new);
     }
}
