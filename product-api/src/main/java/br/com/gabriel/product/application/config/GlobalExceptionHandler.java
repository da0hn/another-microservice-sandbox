package br.com.gabriel.product.application.config;

import br.com.gabriel.product.application.rest.response.ErrorDetailResponse;
import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.core.domain.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorDetailResponse> handleValidationException(final ValidationException exception) {
    final var response = fromExceptionToResponse(exception, HttpStatus.BAD_REQUEST);
    return ResponseEntity.badRequest().body(response);
  }

  private static ErrorDetailResponse fromExceptionToResponse(
    final RuntimeException exception,
    final HttpStatus httpStatus
  ) {
    return new ErrorDetailResponse(
      httpStatus.value(),
      exception.getMessage(),
      LocalDateTime.now()
    );
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorDetailResponse> handleEntityNotFoundException(final EntityNotFoundException exception) {
    final var response = fromExceptionToResponse(exception, HttpStatus.NOT_FOUND);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

}
