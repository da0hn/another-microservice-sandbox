package br.com.gabriel.product;

public record StatusResponse(
  String service,
  String status,
  Integer httpStatus
) {
}
