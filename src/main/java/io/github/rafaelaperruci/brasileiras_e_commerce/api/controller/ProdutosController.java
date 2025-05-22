package io.github.rafaelaperruci.brasileiras_e_commerce.api.controller;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ProdutoResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ProdutosDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Fornecedores;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Produto;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
class ProdutosController {

    private ProdutoService produtoService;

    public ProdutosController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProdutosDTO dto) {
        try {
            Produto p = produtoService.createProduto(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoResponseDTO(p));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Erro inesperado ao cadastrar produto."));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject @PageableDefault(size = 10, sort = {"codigo"}) Pageable pagination) {
        Page<ProdutoResponseDTO> page = produtoService.getAllProdutos(pagination);
        if (page.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProdutoResponseDTO produtoResponseDTO = produtoService.findProdutoById(id);
        if (produtoResponseDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ProdutoResponseDTO> dto = produtoService.deleteProdutoById(id);
        if (dto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(Map.of("message", "Produto deletado com sucesso."));
    }
}
