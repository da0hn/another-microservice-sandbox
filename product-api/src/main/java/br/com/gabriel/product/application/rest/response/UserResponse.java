package br.com.gabriel.product.application.rest.response;

public record UserResponse(
  Long id,
  String name,
  String email
) {
}
