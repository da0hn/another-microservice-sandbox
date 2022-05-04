package br.com.gabriel.product.core.handlers.impl;

import br.com.gabriel.product.core.domain.Product;
import br.com.gabriel.product.core.handlers.CommandHandler;
import br.com.gabriel.product.core.handlers.SalesConfirmationPublisher;
import br.com.gabriel.product.core.handlers.commands.ProductSellItem;
import br.com.gabriel.product.core.handlers.commands.SalesConfirmationCommand;
import br.com.gabriel.product.core.handlers.commands.UpdateProductStockCommand;
import br.com.gabriel.product.infra.db.finders.ProductFinder;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

import static br.com.gabriel.product.core.handlers.commands.SalesStatus.APPROVED;
import static br.com.gabriel.product.core.handlers.commands.SalesStatus.REJECTED;

@Slf4j
@Service
@AllArgsConstructor
public class UpdateProductStockHandler implements CommandHandler<UpdateProductStockCommand> {

  private final SalesConfirmationPublisher salesConfirmationPublisher;
  private final ProductFinder productFinder;
  private final JpaProductRepository repository;

  @Transactional
  @Override public void handle(final UpdateProductStockCommand command) {
    try {
      command.validate();

      final var updatedProducts = new ArrayList<Product>();

      command.itens().forEach(item -> this.updateStock(item, updatedProducts));

      if(!ObjectUtils.isEmpty(updatedProducts)) {
        this.repository.saveAll(updatedProducts);
      }

      this.salesConfirmationPublisher.publish(new SalesConfirmationCommand(command.salesId(), APPROVED));
    }
    catch(final Exception exception) {
      log.error("Error while trying update product stock: {}", exception.getMessage());
      this.salesConfirmationPublisher.publish(new SalesConfirmationCommand(command.salesId(), REJECTED));
    }

  }

  private void updateStock(final ProductSellItem item, final Collection<? super Product> products) {
    final var product = this.productFinder.find(item.productId());
    product.updateStock(item.quantity());
    products.add(product);
    log.info("Product stock updated {}", product);
  }
}
