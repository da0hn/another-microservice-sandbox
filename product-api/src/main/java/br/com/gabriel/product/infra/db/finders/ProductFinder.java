package br.com.gabriel.product.infra.db.finders;

import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.core.domain.Product;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductFinder implements EntityFinder<Product> {

  private final JpaProductRepository repository;

  @Override public Product find(final Long id) {
    return Optional.ofNullable(id)
      .map(notNullId -> this.repository
        .findById(notNullId)
        .orElseThrow(() -> new EntityNotFoundException("The product " + notNullId + " was not found"))
      ).orElseThrow(() -> new ValidationException("The product id was not informed"));
  }
}
