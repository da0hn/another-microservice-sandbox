package br.com.gabriel.product.application.rest.request;

import br.com.gabriel.product.core.services.ExecutableService;

public record EmptyRequest() implements ExecutableService.Input {

  public static EmptyRequest empty() {
    return new EmptyRequest();
  }

}
