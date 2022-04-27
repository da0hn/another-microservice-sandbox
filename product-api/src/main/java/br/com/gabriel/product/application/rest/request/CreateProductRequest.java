package br.com.gabriel.product.application.rest.request;

import br.com.gabriel.product.core.services.ExecutableService;

public record CreateProductRequest(
  String name,
  Integer quantityAvailable,
  Long categoryId,
  Long supplierId
) implements ExecutableService.Input {
}
