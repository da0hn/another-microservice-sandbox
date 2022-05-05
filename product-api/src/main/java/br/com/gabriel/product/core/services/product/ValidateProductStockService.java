package br.com.gabriel.product.core.services.product;

import br.com.gabriel.product.application.rest.common.ProductQuantityItem;
import br.com.gabriel.product.application.rest.request.ValidateProductStockRequest;
import br.com.gabriel.product.application.rest.response.ValidateProductStockResponse;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.finders.ProductFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class ValidateProductStockService implements ExecutableService<ValidateProductStockRequest, ValidateProductStockResponse> {

  private final ProductFinder productFinder;

  @Override public ValidateProductStockResponse execute(final ValidateProductStockRequest input) {
    if(ObjectUtils.isEmpty(input) || ObjectUtils.isEmpty(input.products())) {
      throw new ValidationException("The request data must be informed");
    }

    final var productsOutOfStock = input.products()
      .stream()
      .filter(this::notHasInStock)
      .toList();

    return new ValidateProductStockResponse(productsOutOfStock.isEmpty(), productsOutOfStock);
  }

  private boolean notHasInStock(final ProductQuantityItem item) {
    item.validate();
    final var product = this.productFinder.find(item.productId());
    return !product.hasInStock(item.quantity());
  }

}
