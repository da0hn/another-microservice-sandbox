package br.com.gabriel.product.infra.db.repositories;

import br.com.gabriel.product.core.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}

