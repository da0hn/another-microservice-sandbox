package br.com.gabriel.product.application.rest.response;

import br.com.gabriel.product.core.services.ExecutableService;

import java.util.Collection;

public record CollectionResponse<T extends ExecutableService.Output>(
  Collection<T> data
) implements ExecutableService.Output {
}
