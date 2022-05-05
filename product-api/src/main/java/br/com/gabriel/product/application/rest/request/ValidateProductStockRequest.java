package br.com.gabriel.product.application.rest.request;

import br.com.gabriel.product.application.rest.common.ProductQuantityItem;
import br.com.gabriel.product.core.services.ExecutableService;

import java.util.List;

public record ValidateProductStockRequest(
  List<ProductQuantityItem> products
) implements ExecutableService.Input {
}
