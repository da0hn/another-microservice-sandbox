package br.com.gabriel.product.core.services;

import br.com.gabriel.product.application.mappers.CategoryMapper;
import br.com.gabriel.product.application.rest.request.UpdateCategoryRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.core.domain.Category;
import br.com.gabriel.product.infra.db.finders.CategoryFinder;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@AllArgsConstructor
public class UpdateCategoryService implements ExecutableService<UpdateCategoryRequest, CategoryResponse> {

  private final JpaCategoryRepository repository;
  private final CategoryFinder categoryFinder;
  private final CategoryMapper mapper;

  @Override public CategoryResponse execute(final UpdateCategoryRequest input) {
    Objects.requireNonNull(input);

    final var category = this.findCategoryById(input);

    category.setDescription(input.description());

    category.validate();

    this.repository.save(category);

    return this.mapper.fromEntity(category);
  }

  private Category findCategoryById(final UpdateCategoryRequest input) {
    return this.categoryFinder.find(input.id());
  }
}
