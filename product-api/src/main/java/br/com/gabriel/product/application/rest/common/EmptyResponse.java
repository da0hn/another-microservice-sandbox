package br.com.gabriel.product.application.rest.common;

import br.com.gabriel.product.core.services.ExecutableService;

public record EmptyResponse() implements ExecutableService.Output {

  public static EmptyResponse empty() {
    return new EmptyResponse();
  }

}
