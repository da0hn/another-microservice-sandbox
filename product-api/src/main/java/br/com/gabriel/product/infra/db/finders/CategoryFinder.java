package br.com.gabriel.product.infra.db.finders;

import br.com.gabriel.product.core.domain.Category;
import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryFinder implements EntityFinder<Category> {

  private final JpaCategoryRepository repository;

  @Override public Category find(final Long id) {
    return Optional.ofNullable(id)
      .map(notNullId -> this.repository
        .findById(notNullId)
        .orElseThrow(() -> new EntityNotFoundException("The category " + notNullId + " was not found"))
      ).orElseThrow(() -> new ValidationException("The category id was not informed"));
  }
}
