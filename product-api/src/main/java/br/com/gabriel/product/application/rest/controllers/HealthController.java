package br.com.gabriel.product.application.rest.controllers;

import br.com.gabriel.product.application.rest.response.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

  @GetMapping("/status")
  public ResponseEntity<StatusResponse> getStatus() {
    return ResponseEntity.ok(new StatusResponse(
      "product-api",
      "up",
      HttpStatus.OK.value()
    ));
  }

}
