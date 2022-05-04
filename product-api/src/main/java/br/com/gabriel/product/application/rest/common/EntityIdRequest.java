package br.com.gabriel.product.application.rest.common;

import br.com.gabriel.product.core.services.ExecutableService;

public record EntityIdRequest(
  Long id
) implements ExecutableService.Input {
}
