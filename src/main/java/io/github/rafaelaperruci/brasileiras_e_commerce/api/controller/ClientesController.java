package io.github.rafaelaperruci.brasileiras_e_commerce.api.controller;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClienteResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClientesDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.service.ClientesService;
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
@RequestMapping("/clientes")
public class ClientesController {

    private ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientesDTO clientesDTO) {
       try {
           Clientes cliente = clientesService.createCliente(clientesDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteResponseDTO(cliente));
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado ao cadastrar cliente.");
       }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject @PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        Page<ClienteResponseDTO> page = clientesService.getAllClientes(pagination);
        if (page.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ClienteResponseDTO cliente = clientesService.findClienteById(id);
        if (cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ClienteResponseDTO> cliente = clientesService.deleteClienteById(id);
        if (cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(Map.of("message", "Cliente deletado com sucesso."));
    }

}
