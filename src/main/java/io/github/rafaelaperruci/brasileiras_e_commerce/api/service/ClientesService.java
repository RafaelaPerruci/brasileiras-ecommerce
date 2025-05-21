package io.github.rafaelaperruci.brasileiras_e_commerce.api.service;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClientesRequestDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public Clientes createCliente(ClientesRequestDTO cliente) {

        return null;
    }
}
