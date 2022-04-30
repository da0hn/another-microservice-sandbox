package br.com.gabriel.product.application.rest.request;

import br.com.gabriel.product.core.services.ExecutableService;

public record FetchByIdRequest(
  Long id
) implements ExecutableService.Input {
}
