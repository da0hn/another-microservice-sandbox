package br.com.gabriel.product.application.rest.controllers;

import br.com.gabriel.product.application.rest.request.CreateProductRequest;
import br.com.gabriel.product.application.rest.request.EmptyRequest;
import br.com.gabriel.product.application.rest.request.EntityIdRequest;
import br.com.gabriel.product.application.rest.request.UpdateProductRequest;
import br.com.gabriel.product.application.rest.response.CollectionResponse;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.core.services.category.CreateProductService;
import br.com.gabriel.product.core.services.product.DeleteProductByIdService;
import br.com.gabriel.product.core.services.product.GetAllProductService;
import br.com.gabriel.product.core.services.product.GetProductByIdService;
import br.com.gabriel.product.core.services.product.UpdateProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final CreateProductService createProductService;
  private final DeleteProductByIdService deleteProductByIdService;
  private final UpdateProductService updateProductService;
  private final GetProductByIdService getProductByIdService;
  private final GetAllProductService getAllProductService;

  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody final CreateProductRequest request) {
    return ResponseEntity.ok(this.createProductService.execute(request));
  }

  @PutMapping
  public ResponseEntity<ProductResponse> update(@RequestBody final UpdateProductRequest request) {
    return ResponseEntity.ok(this.updateProductService.execute(request));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getById(@PathVariable final Long productId) {
    return ResponseEntity.ok(this.getProductByIdService.execute(new EntityIdRequest(productId)));
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteById(@PathVariable final Long productId) {
    this.deleteProductByIdService.execute(new EntityIdRequest(productId));
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<CollectionResponse<ProductResponse>> getAll() {
    return ResponseEntity.ok(this.getAllProductService.execute(EmptyRequest.empty()));
  }
}
