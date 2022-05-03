package br.com.gabriel.product.application.rest.response;

import io.jsonwebtoken.Claims;

public record UserResponse(
  Long id,
  String name,
  String email
) {

  public static UserResponse fromClaims(final Claims claims) {
    final var id = claims.get("id", Long.class);
    final var name = claims.get("name", String.class);
    final var email = claims.get("email", String.class);
    return new UserResponse(id, name, email);
  }

}
