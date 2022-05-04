package br.com.gabriel.product.core.services.supplier;

import br.com.gabriel.product.application.rest.common.EmptyResponse;
import br.com.gabriel.product.application.rest.common.EntityIdRequest;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import br.com.gabriel.product.infra.db.repositories.JpaSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DeleteSupplierByIdService implements ExecutableService<EntityIdRequest, EmptyResponse> {

  private final JpaSupplierRepository repository;
  private final JpaProductRepository productRepository;


  @Override public EmptyResponse execute(final EntityIdRequest input) {

    if(Objects.isNull(input.id())) {
      throw new ValidationException("The id must be informed");
    }

    if(this.productRepository.existsBySupplierId(input.id())) {
      throw new ValidationException("You cannot delete this supplier because it's already defined by a product.");
    }

    this.repository.deleteById(input.id());

    return EmptyResponse.empty();
  }
}
