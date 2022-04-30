package br.com.gabriel.product.application.rest.response;

import br.com.gabriel.product.core.services.ExecutableService;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ProductResponse(
  Long id,
  String name,
  Integer quantityAvailable,
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  LocalDateTime createdAt,
  CategoryResponse category,
  SupplierResponse supplier
) implements ExecutableService.Output {
}
