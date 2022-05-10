package br.com.gabriel.product.application.config;

import br.com.gabriel.product.core.domain.ValidationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public final class RequestHeader {

  public static final String TRANSACTION_ID = "transactionid";
  private static final String SERVICE_ID = "serviceid";

  private RequestHeader() {
  }

  public static String getCurrentTransactionId() {
    return getCurrentRequest().getHeader(TRANSACTION_ID);
  }

  public static HttpServletRequest getCurrentRequest() {
    try {
      return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
    catch(final Exception exception) {
      throw new ValidationException("The current request could not be processed");
    }
  }

  public static String getCurrentServiceId() {
    return getCurrentRequest().getHeader(SERVICE_ID);
  }
}
