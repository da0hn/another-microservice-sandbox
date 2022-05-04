package br.com.gabriel.product.core.handlers.commands;

public record SalesConfirmationCommand(
  String salesId,
  SalesStatus status
) {
}
