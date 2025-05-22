package io.github.rafaelaperruci.brasileiras_e_commerce.api.service;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.ClienteResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.dto.FornecedoresResponseDTO;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Fornecedores;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.FornecedoresRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornecedoresService {

    private FornecedoresRepository fornecedoresRepository;

    public FornecedoresService(FornecedoresRepository fornecedoresRepository) {
        this.fornecedoresRepository = fornecedoresRepository;
    }

    public Fornecedores createFornecedor(FornecedoresDTO dto) {
        return fornecedoresRepository.save(new Fornecedores(dto));
    }

    public Page<FornecedoresResponseDTO> getAllFornecedores(Pageable pagination) {
        Page<Fornecedores> page = fornecedoresRepository.findAll(pagination);
        if (page.isEmpty()) {
            return Page.empty();
        }
        return page.map(FornecedoresResponseDTO::new);
    }

    public FornecedoresResponseDTO findFornecedoresById(Long id) {
        Fornecedores fornecedores = fornecedoresRepository.findById(id).orElse(null);
        if (fornecedores == null) {
            return null;
        }
        return new FornecedoresResponseDTO(fornecedores);
    }

    public Optional<FornecedoresResponseDTO> deleteFornecedorById(Long id) {
        Optional<Fornecedores> fornecedor = fornecedoresRepository.findById(id);
        if (fornecedor.isEmpty()) {
            return Optional.empty();
        }
        fornecedoresRepository.deleteById(id);
        return fornecedor.map(FornecedoresResponseDTO::new);
    }
}
