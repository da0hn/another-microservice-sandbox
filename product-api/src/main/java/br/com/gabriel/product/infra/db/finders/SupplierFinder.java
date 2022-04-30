package br.com.gabriel.product.infra.db.finders;

import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.core.domain.Supplier;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.infra.db.repositories.JpaSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SupplierFinder implements EntityFinder<Supplier> {

  private final JpaSupplierRepository repository;

  @Override public Supplier find(final Long id) {
    return Optional.ofNullable(id)
      .map(notNullId -> this.repository
        .findById(notNullId)
        .orElseThrow(() -> new EntityNotFoundException("The supplier " + notNullId + " was not found"))
      ).orElseThrow(() -> new ValidationException("The supplier id was not informed"));
  }
}
