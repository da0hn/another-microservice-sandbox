package br.com.gabriel.product.application.security;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpMethod.OPTIONS;

@AllArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private final AuthenticationService authenticationService;

  @Override public boolean preHandle(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final Object handler
  ) throws Exception {
    if(isHttpOptionsMethod(request)) {
      return true;
    }

    final var token = request.getHeader(AUTHORIZATION_HEADER);

    return this.authenticationService.isAuthenticated(token);
  }

  private static boolean isHttpOptionsMethod(final HttpServletRequest request) {
    return OPTIONS.name().equals(request.getMethod());
  }
}
