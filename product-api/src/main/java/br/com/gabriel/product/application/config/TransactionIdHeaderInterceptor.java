package br.com.gabriel.product.application.config;

import br.com.gabriel.product.core.domain.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@AllArgsConstructor
public class TransactionIdHeaderInterceptor implements HandlerInterceptor {

  private static final String TRANSACTION_ID = "transactionid";

  @Override public boolean preHandle(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final Object handler
  ) throws Exception {

    final var transactionId = request.getHeader(TRANSACTION_ID);

    if(ObjectUtils.isEmpty(transactionId)) {
      throw new ValidationException("The transactionid header is required");
    }

    request.setAttribute("serviceid", UUID.randomUUID().toString());

    return true;
  }
}
