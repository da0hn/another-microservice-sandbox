package br.com.gabriel.product.core.handlers.commands;

import br.com.gabriel.product.core.domain.Validable;
import br.com.gabriel.product.core.domain.ValidationException;
import org.springframework.util.ObjectUtils;

public record ProductSellItem(
  Long productId,
  Integer quantity
) implements Validable {

  @Override public void validate() {
    if(ObjectUtils.isEmpty(this.productId()) || ObjectUtils.isEmpty(this.quantity())) {
      throw new ValidationException("Malformed sales item: " + this);
    }
  }
}
