package br.com.gabriel.product.core.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  @Serial private static final long serialVersionUID = 2065340050486690519L;

  public EntityNotFoundException(final String message) {
    super(message);
  }

}
