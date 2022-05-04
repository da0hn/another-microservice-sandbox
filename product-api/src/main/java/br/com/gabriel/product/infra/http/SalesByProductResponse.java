package br.com.gabriel.product.infra.http;

import java.util.List;

public record SalesByProductResponse(
  List<String> salesId
) {
}
