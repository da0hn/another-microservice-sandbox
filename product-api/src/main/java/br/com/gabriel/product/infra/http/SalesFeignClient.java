package br.com.gabriel.product.infra.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
  name = "salesClient",
  contextId = "salesClient",
  url = "${app-config.services.sales}"
)
public interface SalesFeignClient {


  @GetMapping("/products/{productId}/orders")
  Optional<SalesByProductResponse> getSalesByProduct(@PathVariable("productId") Long productId);


}
