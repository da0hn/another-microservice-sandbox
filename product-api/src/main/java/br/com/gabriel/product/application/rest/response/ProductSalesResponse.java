package br.com.gabriel.product.application.rest.response;

import br.com.gabriel.product.core.services.ExecutableService;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ProductSalesResponse(
  Long id,
  String name,
  Integer quantityAvailable,
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  LocalDateTime createdAt,
  CategoryResponse category,
  SupplierResponse supplier,
  List<String> sales
) implements ExecutableService.Output {
}
