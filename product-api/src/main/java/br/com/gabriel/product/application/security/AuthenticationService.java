package br.com.gabriel.product.application.security;

import br.com.gabriel.product.application.rest.response.UserResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

@Service
public class AuthenticationService {

  private static final String BEARER = "Bearer ";
  private final String apiSecret;

  public AuthenticationService(@Value("${app-config.secrets.api-secret}") final String apiSecret) {
    this.apiSecret = apiSecret;
  }

  public boolean isAuthenticated(final String token) {
    try {
      final var accessToken = this.parseToken(token);
      final var claims = Jwts.parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(this.apiSecret.getBytes(StandardCharsets.UTF_8)))
        .build()
        .parseClaimsJws(accessToken)
        .getBody();

      final var user = UserResponse.fromClaims(claims);

      return !ObjectUtils.isEmpty(user.id());
    }
    catch(final ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException |
                WeakKeyException e) {
      throw new AuthenticationException("Error while trying process the access token: " + e.getMessage());
    }
  }

  private String parseToken(final String token) {
    if(ObjectUtils.isEmpty(token)) {
      throw new AuthenticationException("The access token was not informed");
    }

    if(!token.contains(BEARER)) {
      throw new AuthenticationException("The access token is malformed");
    }

    final var splittedToken = token.split(BEARER);
    return splittedToken[1];
  }

}
