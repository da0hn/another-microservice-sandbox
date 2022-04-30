package br.com.gabriel.product.application.rest.request;

import br.com.gabriel.product.core.services.ExecutableService;

public record FetchCategoryByDescriptionRequest(
  String description
) implements ExecutableService.Input {
}
