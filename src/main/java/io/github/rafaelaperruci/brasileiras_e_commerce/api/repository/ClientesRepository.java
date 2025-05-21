package io.github.rafaelaperruci.brasileiras_e_commerce.api.repository;

import io.github.rafaelaperruci.brasileiras_e_commerce.api.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
