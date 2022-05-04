package br.com.gabriel.product.application.rest.common;

import br.com.gabriel.product.core.services.ExecutableService;

public record EmptyRequest() implements ExecutableService.Input {

  public static EmptyRequest empty() {
    return new EmptyRequest();
  }

}
