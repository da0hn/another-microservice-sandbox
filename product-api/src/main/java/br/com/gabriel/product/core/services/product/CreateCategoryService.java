package br.com.gabriel.product.core.services.product;

import br.com.gabriel.product.application.mappers.CategoryMapper;
import br.com.gabriel.product.application.rest.request.CreateCategoryRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import org.springframework.stereotype.Service;


@Service
public class CreateCategoryService implements ExecutableService<CreateCategoryRequest, CategoryResponse> {

  private final JpaCategoryRepository repository;
  private final CategoryMapper mapper;

  public CreateCategoryService(final JpaCategoryRepository repository, final CategoryMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }


  @Override public CategoryResponse execute(final CreateCategoryRequest input) {
    final var category = this.mapper.toEntity(input);

    category.validate();

    this.repository.save(category);

    return this.mapper.fromEntity(category);
  }
}
