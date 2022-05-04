package br.com.gabriel.product.core.services.category;

import br.com.gabriel.product.application.mappers.CategoryMapper;
import br.com.gabriel.product.application.rest.common.EntityIdRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.finders.CategoryFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class GetCategoryByIdService implements ExecutableService<EntityIdRequest, CategoryResponse> {

  private final CategoryFinder categoryFinder;
  private final CategoryMapper mapper;

  @Override public CategoryResponse execute(final EntityIdRequest input) {
    Objects.requireNonNull(input.id(), "The category id was not informed");
    final var category = this.categoryFinder.find(input.id());
    return this.mapper.fromEntity(category);
  }
}
