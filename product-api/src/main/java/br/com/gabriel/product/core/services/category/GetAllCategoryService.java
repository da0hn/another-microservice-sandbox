package br.com.gabriel.product.core.services.category;

import br.com.gabriel.product.application.mappers.CategoryMapper;
import br.com.gabriel.product.application.rest.common.EmptyRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.application.rest.response.CollectionResponse;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllCategoryService implements ExecutableService<EmptyRequest, CollectionResponse<CategoryResponse>> {

  private final JpaCategoryRepository repository;
  private final CategoryMapper mapper;

  @Override public CollectionResponse<CategoryResponse> execute(final EmptyRequest input) {
    return this.repository.findAll().stream()
      .map(this.mapper::fromEntity)
      .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionResponse::new));
  }
}
