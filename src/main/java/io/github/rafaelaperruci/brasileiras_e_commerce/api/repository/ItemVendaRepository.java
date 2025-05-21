package io.github.rafaelaperruci.brasileiras_e_commerce.api.repository;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
