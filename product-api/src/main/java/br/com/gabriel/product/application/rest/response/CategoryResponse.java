package br.com.gabriel.product.application.rest.response;

import br.com.gabriel.product.core.services.ExecutableService;

public record CategoryResponse(
  Long id,
  String description
) implements ExecutableService.Output {
}
