package br.com.gabriel.product.application.config;

import br.com.gabriel.product.application.rest.response.ErrorDetailResponse;
import br.com.gabriel.product.core.domain.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

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

}
