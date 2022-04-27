package br.com.gabriel.product.infra.db.repositories;

import br.com.gabriel.product.core.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSupplierRepository extends JpaRepository<Supplier, Long> {
}
