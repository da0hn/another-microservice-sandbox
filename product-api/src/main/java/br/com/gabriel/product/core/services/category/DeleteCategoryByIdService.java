package br.com.gabriel.product.core.services.category;

import br.com.gabriel.product.application.rest.request.EntityIdRequest;
import br.com.gabriel.product.application.rest.response.EmptyResponse;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DeleteCategoryByIdService implements ExecutableService<EntityIdRequest, EmptyResponse> {

  private final JpaCategoryRepository repository;
  private final JpaProductRepository productRepository;


  @Override public EmptyResponse execute(final EntityIdRequest input) {
    final var categoryId = input.id();

    if(Objects.isNull(categoryId)) {
      throw new ValidationException("The id must be informed");
    }

    if(this.productRepository.existsByCategoryId(categoryId)) {
      throw new ValidationException("You cannot delete this category because it's already defined by a product.");
    }

    this.repository.deleteById(categoryId);

    return EmptyResponse.empty();
  }
}
