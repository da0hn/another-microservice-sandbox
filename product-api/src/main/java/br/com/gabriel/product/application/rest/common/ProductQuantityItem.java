package br.com.gabriel.product.application.rest.common;

import br.com.gabriel.product.core.domain.Validable;
import br.com.gabriel.product.core.domain.ValidationException;
import org.springframework.util.ObjectUtils;

public record ProductQuantityItem(
  Long productId,
  Integer quantity
) implements Validable {

  @Override public void validate() {
    if(ObjectUtils.isEmpty(this.productId()) || ObjectUtils.isEmpty(this.quantity())) {
      throw new ValidationException("Malformed sales item: " + this);
    }
  }
}
