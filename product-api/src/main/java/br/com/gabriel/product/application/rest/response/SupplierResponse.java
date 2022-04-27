package br.com.gabriel.product.application.rest.response;

import br.com.gabriel.product.core.services.ExecutableService;

public record SupplierResponse(
  Long id,
  String name
) implements ExecutableService.Output {

}
