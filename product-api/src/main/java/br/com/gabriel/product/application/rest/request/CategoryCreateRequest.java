package br.com.gabriel.product.application.rest.request;

import br.com.gabriel.product.core.services.ExecutableService;

public record CategoryCreateRequest(
  String description
) implements ExecutableService.Input {
}
