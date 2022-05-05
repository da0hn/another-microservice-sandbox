package br.com.gabriel.product.application.rest.response;

import br.com.gabriel.product.application.rest.common.ProductQuantityItem;
import br.com.gabriel.product.core.services.ExecutableService;

import java.util.List;

public record ValidateProductStockResponse(
  boolean valid,
  List<ProductQuantityItem> productsOutOfStock
) implements ExecutableService.Output {
}
