package br.com.gabriel.product.infra.queue.publishers;

import br.com.gabriel.product.core.handlers.SalesConfirmationPublisher;
import br.com.gabriel.product.core.handlers.commands.SalesConfirmationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesConfirmationPublisherImpl implements SalesConfirmationPublisher {

  private final RabbitTemplate rabbitTemplate;
  @Value("${app-config.rabbit.exchange.product}")
  private String productTopicExchange;
  @Value("${app-config.rabbit.routing-key.sales-confirmation}")
  private String salesConfirmationKey;

  @Override public void publish(final SalesConfirmationCommand command, final String serviceId) {
    try {
      log.info("Sending command: {} | [ transactionid: {} | serviceid: {} ]", command, command.transactionId(), serviceId);
      this.rabbitTemplate.convertAndSend(this.productTopicExchange, this.salesConfirmationKey, command);
      log.info("Command was sent successfully | [ transactionid: {} | serviceid: {} ]", command.transactionId(), serviceId);
    }
    catch(final Exception exception) {
      log.error(
        "Error while trying to send sales confirmation command {} | [ transactionid: {} | serviceid: {} ]",
        exception.getMessage(),
        command.transactionId(),
        serviceId,
        exception
      );
    }
  }
}
