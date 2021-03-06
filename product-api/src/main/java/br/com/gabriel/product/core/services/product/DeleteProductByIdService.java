package br.com.gabriel.product.core.services.product;

import br.com.gabriel.product.application.rest.common.EmptyResponse;
import br.com.gabriel.product.application.rest.common.EntityIdRequest;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DeleteProductByIdService implements ExecutableService<EntityIdRequest, EmptyResponse> {

  private final JpaProductRepository repository;


  @Override public EmptyResponse execute(final EntityIdRequest input) {

    if(Objects.isNull(input.id())) {
      throw new ValidationException("The id must be informed");
    }

    if(!this.repository.existsById(input.id())) {
      throw new ValidationException("The product does not exists.");
    }

    this.repository.deleteById(input.id());

    return EmptyResponse.empty();
  }
}
