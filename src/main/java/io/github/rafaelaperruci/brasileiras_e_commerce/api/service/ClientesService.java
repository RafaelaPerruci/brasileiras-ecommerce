package io.github.rafaelaperruci.brasileiras_e_commerce.api.service;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClienteResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClientesDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.ClientesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientesService {

    private ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public Clientes createCliente(ClientesDTO clienteDTO) {
        return clientesRepository.save(new Clientes(clienteDTO));
    }

    public Page<ClienteResponseDTO> getAllClientes(Pageable pagination) {
        Page<Clientes> clientesPage = clientesRepository.findAll(pagination);
        if (clientesPage.isEmpty()) {
            return Page.empty();
        }
        return clientesPage.map(ClienteResponseDTO::new);
    }

    public ClienteResponseDTO findClienteById(Long id) {
        Clientes clientes = clientesRepository.findById(id).orElse(null);
        if (clientes == null) {
            return null;
        }
        return new ClienteResponseDTO(clientes);

    }

    public Optional<ClienteResponseDTO> deleteClienteById(Long id) {
        Optional<Clientes> clientes = clientesRepository.findById(id);
        if (clientes.isEmpty()) {
           return Optional.empty();
        }
        clientesRepository.deleteById(id);
        return clientes.map(ClienteResponseDTO::new);
    }
}
