package br.com.gabriel.product.application.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationException extends RuntimeException {

  public AuthenticationException(final String message) {
    super(message);
  }

}
