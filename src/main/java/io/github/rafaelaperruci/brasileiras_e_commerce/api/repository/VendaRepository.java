package io.github.rafaelaperruci.brasileiras_e_commerce.api.repository;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    Page<Venda> findAll(Pageable pageable);
}
