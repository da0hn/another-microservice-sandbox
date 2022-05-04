package br.com.gabriel.product.core.handlers.commands;

import br.com.gabriel.product.core.domain.Validable;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.core.handlers.Command;
import org.springframework.util.ObjectUtils;

import java.util.List;

public record UpdateProductStockCommand(
  String salesId,
  List<ProductSellItem> itens
) implements Command, Validable {

  @Override public void validate() {
    if(ObjectUtils.isEmpty(this) || ObjectUtils.isEmpty(this.salesId())) {
      throw new ValidationException("The product data or sale id must be informed");
    }
    if(ObjectUtils.isEmpty(this.itens())) {
      throw new ValidationException("The sales itens must be informed");
    }
    this.itens.forEach(ProductSellItem::validate);
  }
}
