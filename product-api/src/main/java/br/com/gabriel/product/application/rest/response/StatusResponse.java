package br.com.gabriel.product.application.rest.response;

public record StatusResponse(
  String service,
  String status,
  Integer httpStatus
) {
}
