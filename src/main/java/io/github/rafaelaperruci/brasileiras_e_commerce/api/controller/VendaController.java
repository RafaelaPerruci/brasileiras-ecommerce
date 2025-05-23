package io.github.rafaelaperruci.brasileiras_e_commerce.api.controller;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ProdutoResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ProdutosDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.VendaRequestDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.VendaResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Produto;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.service.VendaService;
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
@RequestMapping("/vendas")
public class VendaController {

    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody VendaRequestDTO dto) {
        try {
            VendaResponseDTO venda = vendaService.createVenda(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(venda);
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Erro inesperado ao cadastrar produto."));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject @PageableDefault(size = 10, sort = {"numeroVenda"}) Pageable pagination) {
        Page<VendaResponseDTO> page = vendaService.getAllVendas(pagination);
        if (page.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        VendaResponseDTO vendaResponseDTO = vendaService.findVendaById(id);
        if (vendaResponseDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Venda não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(vendaResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<VendaResponseDTO> dto = vendaService.deleteVendaById(id);
        if (dto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Venda não encontrada."));
        }
        return ResponseEntity.ok(Map.of("message", "Venda deletado com sucesso."));
    }
}
