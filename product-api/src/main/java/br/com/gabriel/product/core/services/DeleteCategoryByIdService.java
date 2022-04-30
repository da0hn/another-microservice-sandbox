package br.com.gabriel.product.core.services;

import br.com.gabriel.product.application.rest.request.EntityIdRequest;
import br.com.gabriel.product.application.rest.response.EmptyResponse;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DeleteCategoryByIdService implements ExecutableService<EntityIdRequest, EmptyResponse> {

  private final JpaCategoryRepository repository;


  @Override public EmptyResponse execute(final EntityIdRequest input) {

    if(Objects.isNull(input.id())) {
      throw new ValidationException("The id must be informed");
    }

    this.repository.deleteById(input.id());

    return EmptyResponse.empty();
  }
}
