package br.com.gabriel.product.core.services.category;

import br.com.gabriel.product.application.mappers.CategoryMapper;
import br.com.gabriel.product.application.rest.request.FetchCategoryByDescriptionRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.application.rest.response.CollectionResponse;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetCategoryByDescriptionService implements ExecutableService<FetchCategoryByDescriptionRequest, CollectionResponse<CategoryResponse>> {


  private final JpaCategoryRepository repository;
  private final CategoryMapper mapper;

  @Override public CollectionResponse<CategoryResponse> execute(final FetchCategoryByDescriptionRequest input) {

    if(ObjectUtils.isEmpty(input.description())) {
      throw new ValidationException("The description must be informed");
    }

    return this.repository.findByDescription(input.description()).stream()
      .map(this.mapper::fromEntity)
      .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionResponse::new));

  }
}
