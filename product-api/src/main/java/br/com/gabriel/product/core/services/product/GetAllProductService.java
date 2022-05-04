package br.com.gabriel.product.core.services.product;

import br.com.gabriel.product.application.mappers.ProductMapper;
import br.com.gabriel.product.application.rest.common.EmptyRequest;
import br.com.gabriel.product.application.rest.response.CollectionResponse;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllProductService implements ExecutableService<EmptyRequest, CollectionResponse<ProductResponse>> {

  private final JpaProductRepository repository;
  private final ProductMapper mapper;

  @Override public CollectionResponse<ProductResponse> execute(final EmptyRequest input) {
    return this.repository.findAll().stream()
      .map(this.mapper::fromEntity)
      .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionResponse::new));
  }
}
