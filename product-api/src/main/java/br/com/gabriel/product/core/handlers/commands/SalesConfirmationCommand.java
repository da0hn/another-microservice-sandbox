package br.com.gabriel.product.core.handlers.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SalesConfirmationCommand(
  String salesId,
  SalesStatus status,
  @JsonProperty("transactionid")
  String transactionId
) {
}
