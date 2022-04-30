package br.com.gabriel.product.core.services;

import br.com.gabriel.product.application.mappers.CategoryMapper;
import br.com.gabriel.product.application.rest.request.FetchByIdRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class GetCategoryByIdService implements ExecutableService<FetchByIdRequest, CategoryResponse> {

  private final JpaCategoryRepository repository;
  private final CategoryMapper mapper;

  @Override public CategoryResponse execute(final FetchByIdRequest input) {
    Objects.requireNonNull(input.id(), "The category id was not informed");
    return this.repository
      .findById(input.id())
      .map(this.mapper::fromEntity)
      .orElseThrow(() -> new EntityNotFoundException("The category " + input.id() + " was not found"));
  }
}
