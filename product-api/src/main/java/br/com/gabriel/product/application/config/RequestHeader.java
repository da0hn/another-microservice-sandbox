package br.com.gabriel.product.application.config;

import br.com.gabriel.product.core.domain.ValidationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public final class RequestHeader {

  private RequestHeader() {
  }

  public static HttpServletRequest getCurrentRequest() {
    try {
      return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
    catch(final Exception exception) {
      throw new ValidationException("The current request could not be processed");
    }
  }

}
