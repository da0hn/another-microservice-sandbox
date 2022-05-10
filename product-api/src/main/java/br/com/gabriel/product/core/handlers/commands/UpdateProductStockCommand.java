package br.com.gabriel.product.core.handlers.commands;

import br.com.gabriel.product.application.rest.common.ProductQuantityItem;
import br.com.gabriel.product.core.domain.Validable;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.core.handlers.Command;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.ObjectUtils;

import java.util.List;

public record UpdateProductStockCommand(
  String salesId,
  List<ProductQuantityItem> itens,

  @JsonProperty("transactionid")
  String transactionId
) implements Command, Validable {

  @Override public void validate() {
    if(ObjectUtils.isEmpty(this) || ObjectUtils.isEmpty(this.salesId())) {
      throw new ValidationException("The product data or sale id must be informed");
    }
    if(ObjectUtils.isEmpty(this.itens())) {
      throw new ValidationException("The sales itens must be informed");
    }
    if(ObjectUtils.isEmpty(this.transactionId())) {
      throw new ValidationException("The transaction id must be informed");
    }
    this.itens.forEach(ProductQuantityItem::validate);
  }
}
