package br.com.gabriel.product.application.rest.controllers;

import br.com.gabriel.product.application.rest.request.CreateSupplierRequest;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.services.CreateSupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

  private final CreateSupplierService createSupplierService;

  @PostMapping
  public ResponseEntity<SupplierResponse> create(@RequestBody final CreateSupplierRequest request) {
    return ResponseEntity.ok(this.createSupplierService.execute(request));
  }

}
