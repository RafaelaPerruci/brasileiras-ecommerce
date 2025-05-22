package io.github.rafaelaperruci.brasileiras_e_commerce.api.controller;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClienteResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClientesDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Fornecedores;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.service.FornecedoresService;
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
@RequestMapping("/fornecedores")
public class FornecedoresController {

    private FornecedoresService fornecedoresService;

    public FornecedoresController(FornecedoresService fornecedoresService) {
        this.fornecedoresService = fornecedoresService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FornecedoresDTO dto) {
        try {
            Fornecedores fornecedor = fornecedoresService.createFornecedor(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new FornecedoresResponseDTO(fornecedor));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado ao cadastrar fornecedor.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject @PageableDefault(size = 10, sort = {"razaoSocial"}) Pageable pagination) {
        Page<FornecedoresResponseDTO> page = fornecedoresService.getAllFornecedores(pagination);
        if (page.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        FornecedoresResponseDTO fornecedor = fornecedoresService.findFornecedoresById(id);
        if (fornecedor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(fornecedor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<FornecedoresResponseDTO> fornecedor = fornecedoresService.deleteFornecedorById(id);
        if (fornecedor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(Map.of("message", "Fornecedor deletado com sucesso."));
    }
}
