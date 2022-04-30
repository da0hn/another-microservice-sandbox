package br.com.gabriel.product.core.services.product;

import br.com.gabriel.product.application.mappers.ProductMapper;
import br.com.gabriel.product.application.rest.request.EntityIdRequest;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.finders.ProductFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetProductByIdService implements ExecutableService<EntityIdRequest, ProductResponse> {

  private final ProductFinder productFinder;
  private final ProductMapper mapper;

  @Override public ProductResponse execute(final EntityIdRequest input) {
    final var product = this.productFinder.find(input.id());
    return this.mapper.fromEntity(product);
  }
}
