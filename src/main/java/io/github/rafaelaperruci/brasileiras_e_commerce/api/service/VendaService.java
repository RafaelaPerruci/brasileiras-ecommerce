package io.github.rafaelaperruci.brasileiras_e_commerce.api.service;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.ItemVendaRepository;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.repository.VendaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private VendaRepository vendaRepository;
    private ItemVendaRepository itemVendaRepository;

    public VendaService(VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }
}
