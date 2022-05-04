package br.com.gabriel.product.core.services.product;

import br.com.gabriel.product.application.mappers.ProductMapper;
import br.com.gabriel.product.application.rest.request.EntityIdRequest;
import br.com.gabriel.product.application.rest.response.ProductSalesResponse;
import br.com.gabriel.product.core.gateway.SalesGateway;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.finders.ProductFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetProductSalesService implements ExecutableService<EntityIdRequest, ProductSalesResponse> {

  private final SalesGateway salesGateway;
  private final ProductMapper productMapper;
  private final ProductFinder productFinder;

  @Override public ProductSalesResponse execute(final EntityIdRequest input) {
    final var product = this.productFinder.find(input.id());

    final var salesByProductId = this.salesGateway.getSalesByProductId(input.id());

    return this.productMapper.fromEntity(product, salesByProductId);
  }
}
