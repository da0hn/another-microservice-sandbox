package br.com.gabriel.product.application.rest.request;

import br.com.gabriel.product.core.services.ExecutableService;

public record UpdateCategoryRequest(
  Long id,
  String description
) implements ExecutableService.Input {
}
