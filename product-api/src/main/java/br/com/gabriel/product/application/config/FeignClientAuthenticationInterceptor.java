package br.com.gabriel.product.application.config;

import br.com.gabriel.product.core.domain.ValidationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Component
public class FeignClientAuthenticationInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION = "Authorization";

  @Override public void apply(final RequestTemplate template) {
    final var currentRequest = getCurrentRequest();
    template.header(AUTHORIZATION, currentRequest.getHeader(AUTHORIZATION));
  }

  private static HttpServletRequest getCurrentRequest() {
    try {
      return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
    catch(final Exception exception) {
      throw new ValidationException("The current request could not be processed");
    }
  }
}
