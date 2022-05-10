package br.com.gabriel.product.infra.http;

import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.core.gateway.SalesGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

import static br.com.gabriel.product.application.config.RequestHeader.getCurrentServiceId;
import static br.com.gabriel.product.application.config.RequestHeader.getCurrentTransactionId;

@Component
@Slf4j
@AllArgsConstructor
public class SalesGatewayImpl implements SalesGateway {

  private final SalesFeignClient salesFeignClient;

  @Override public SalesByProductResponse getSalesByProductId(final Long productId) {
    final var transactionId = getCurrentTransactionId();
    final var serviceId = getCurrentServiceId();
    try {
      log.info(
        "Get products sales for id {} | [ transactionid: {} | serviceid: {} ]",
        productId,
        transactionId,
        serviceId
      );

      final var response = this.salesFeignClient.getSalesByProduct(productId)
        .orElseThrow(() -> new EntityNotFoundException("Not found sales related to this product " + productId));

      log.info(
        "Received response from sales service {} | [ transactionid: {} | serviceid: {} ]",
        response,
        serviceId,
        transactionId
      );

      return response;
    }
    catch(final Exception exception) {
      throw new GatewayException(MessageFormat.format(
        "There was an error trying to get sales by product: {] | [ transactionid: {} | serviceid: {} ]",
        exception.getMessage(),
        transactionId,
        serviceId
      ));
    }
  }

}
