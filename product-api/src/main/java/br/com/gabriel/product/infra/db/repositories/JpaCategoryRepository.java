package br.com.gabriel.product.infra.db.repositories;

import br.com.gabriel.product.core.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
  @Query("select category from Category category where category.description like concat('%', :description, '%')")
  Collection<Category> findByDescription(@Param("description") String description);
}

