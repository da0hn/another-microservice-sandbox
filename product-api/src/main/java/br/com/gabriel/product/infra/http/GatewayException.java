package br.com.gabriel.product.infra.http;


import java.io.Serial;
import java.io.Serializable;

public class GatewayException extends RuntimeException implements Serializable {

  @Serial private static final long serialVersionUID = -9028196274362625920L;

  public GatewayException(final String message) {
    super(message);
  }
}
