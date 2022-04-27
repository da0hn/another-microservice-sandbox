package br.com.gabriel.product.infra.db.repositories;

import br.com.gabriel.product.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {
}
