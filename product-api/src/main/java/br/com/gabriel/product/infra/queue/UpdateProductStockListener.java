package br.com.gabriel.product.infra.queue;

import br.com.gabriel.product.core.handlers.QueueListener;
import br.com.gabriel.product.core.handlers.commands.UpdateProductStockCommand;
import br.com.gabriel.product.core.handlers.impl.UpdateProductStockHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateProductStockListener implements QueueListener<UpdateProductStockCommand> {

  private final UpdateProductStockHandler handler;

  @Override
  @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
  public void listen(final UpdateProductStockCommand command) {
    final var serviceId = UUID.randomUUID().toString();
    log.info(
      "Update stock message received: {} | [ transactionid: {} | serviceid: {} ]",
      command,
      command.transactionId(),
      serviceId
    );
    this.handler.handle(command, serviceId);
  }

}
