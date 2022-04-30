package br.com.gabriel.product.application.rest.controllers;

import br.com.gabriel.product.application.rest.request.CreateCategoryRequest;
import br.com.gabriel.product.application.rest.request.FetchByIdRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.core.services.CreateCategoryService;
import br.com.gabriel.product.core.services.GetCategoryByIdService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

  private final CreateCategoryService createCategoryService;
  private final GetCategoryByIdService getCategoryByIdService;

  @PostMapping
  public ResponseEntity<CategoryResponse> create(@RequestBody final CreateCategoryRequest request) {
    return ResponseEntity.ok(this.createCategoryService.execute(request));
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryResponse> getById(@PathVariable final Long categoryId) {
    return ResponseEntity.ok(this.getCategoryByIdService.execute(new FetchByIdRequest(categoryId)));
  }


}
