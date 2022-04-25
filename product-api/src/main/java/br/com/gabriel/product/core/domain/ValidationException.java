package br.com.gabriel.product.core.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

  @Serial private static final long serialVersionUID = -7060285039878754291L;

  public ValidationException(final String message) {
    super(message);
  }

}
