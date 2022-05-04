package br.com.gabriel.product.core.gateway;

import br.com.gabriel.product.infra.http.SalesByProductResponse;

public interface SalesGateway {

  SalesByProductResponse getSalesByProductId(Long productId);

}
