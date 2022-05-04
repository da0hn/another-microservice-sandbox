package br.com.gabriel.product.infra.http;

import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.core.gateway.SalesGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SalesGatewayImpl implements SalesGateway {

  private final SalesFeignClient salesFeignClient;

  @Override public SalesByProductResponse getSalesByProductId(final Long productId) {
    try {
      return this.salesFeignClient.getSalesByProduct(productId)
        .orElseThrow(() -> new EntityNotFoundException("Not found sales related to this product " + productId));
    }
    catch(final Exception exception) {
      throw new GatewayException("There was an error trying to get sales by product: " + exception.getMessage());
    }
  }

}
