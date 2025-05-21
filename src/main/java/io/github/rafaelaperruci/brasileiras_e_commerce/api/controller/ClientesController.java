package io.github.rafaelaperruci.brasileiras_e_commerce.api.controller;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClientesRequestDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.service.ClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @PostMapping
    public ResponseEntity<ClientesRequestDTO> create(@RequestBody ClientesRequestDTO cliente) {
        //Clientes cliente = clientesService.createCliente(cliente);
        return null;
    }

}
