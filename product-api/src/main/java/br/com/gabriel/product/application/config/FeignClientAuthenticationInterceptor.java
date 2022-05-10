package br.com.gabriel.product.application.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import static br.com.gabriel.product.application.config.RequestHeader.getCurrentRequest;


@Component
public class FeignClientAuthenticationInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION = "Authorization";
  private static final String TRANSACTION_ID = "transactionid";

  @Override public void apply(final RequestTemplate template) {
    final var currentRequest = getCurrentRequest();
    template.header(AUTHORIZATION, currentRequest.getHeader(AUTHORIZATION))
      .header(TRANSACTION_ID, currentRequest.getHeader(TRANSACTION_ID));
  }


}
