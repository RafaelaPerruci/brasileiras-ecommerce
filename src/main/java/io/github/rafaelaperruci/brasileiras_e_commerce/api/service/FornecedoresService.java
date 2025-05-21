package io.github.rafaelaperruci.brasileiras_e_commerce.api.service;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.FornecedoresRepository;
import org.springframework.stereotype.Service;

@Service
public class FornecedoresService {

    private FornecedoresRepository fornecedoresRepository;

    public FornecedoresService(FornecedoresRepository fornecedoresRepository) {
        this.fornecedoresRepository = fornecedoresRepository;
    }
}
