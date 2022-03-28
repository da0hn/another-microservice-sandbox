package br.com.gabriel.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

  @GetMapping
  public ResponseEntity<StatusResponse> getStatus() {
    return ResponseEntity.ok(new StatusResponse(
      "product-api",
      "up",
      HttpStatus.OK.value()
    ));
  }

}
