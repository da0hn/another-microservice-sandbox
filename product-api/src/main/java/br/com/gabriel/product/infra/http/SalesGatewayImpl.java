package br.com.gabriel.product.infra.http;

import br.com.gabriel.product.core.gateway.SalesGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SalesGatewayImpl implements SalesGateway {

  private final SalesFeignClient salesFeignClient;

  @Override public SalesByProductResponse getSalesByProductId(final Long productId) {
    return this.salesFeignClient.getSalesByProduct(productId);
  }

}
