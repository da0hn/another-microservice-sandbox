package br.com.gabriel.product.application.rest.response;

import br.com.gabriel.product.core.services.ExecutableService;

public record ProductResponse(
  Long id,
  String name,
  Integer quantityAvailable,
  CategoryResponse category,
  SupplierResponse supplier
) implements ExecutableService.Output {
}
