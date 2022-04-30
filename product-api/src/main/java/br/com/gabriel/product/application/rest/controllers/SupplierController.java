package br.com.gabriel.product.application.rest.controllers;

import br.com.gabriel.product.application.rest.request.CreateSupplierRequest;
import br.com.gabriel.product.application.rest.request.EntityIdRequest;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.services.CreateSupplierService;
import br.com.gabriel.product.core.services.DeleteSupplierByIdService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/suppliers")
public class SupplierController {

  private final CreateSupplierService createSupplierService;
  private final DeleteSupplierByIdService deleteSupplierByIdService;

  @PostMapping
  public ResponseEntity<SupplierResponse> create(@RequestBody final CreateSupplierRequest request) {
    return ResponseEntity.ok(this.createSupplierService.execute(request));
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteById(@PathVariable final Long productId) {
    this.deleteSupplierByIdService.execute(new EntityIdRequest(productId));
    return ResponseEntity.noContent().build();
  }

}
