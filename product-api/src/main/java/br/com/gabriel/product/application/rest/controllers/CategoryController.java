package br.com.gabriel.product.application.rest.controllers;

import br.com.gabriel.product.application.rest.request.CreateCategoryRequest;
import br.com.gabriel.product.application.rest.request.EmptyRequest;
import br.com.gabriel.product.application.rest.request.EntityIdRequest;
import br.com.gabriel.product.application.rest.request.FetchCategoryByDescriptionRequest;
import br.com.gabriel.product.application.rest.request.UpdateCategoryRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.application.rest.response.CollectionResponse;
import br.com.gabriel.product.core.services.category.DeleteCategoryByIdService;
import br.com.gabriel.product.core.services.category.GetAllCategoriesService;
import br.com.gabriel.product.core.services.category.GetCategoryByDescriptionService;
import br.com.gabriel.product.core.services.category.GetCategoryByIdService;
import br.com.gabriel.product.core.services.category.UpdateCategoryService;
import br.com.gabriel.product.core.services.product.CreateCategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

  private final CreateCategoryService createCategoryService;
  private final GetCategoryByIdService getCategoryByIdService;
  private final GetCategoryByDescriptionService getCategoryByDescriptionService;
  private final GetAllCategoriesService getAllCategoriesService;
  private final DeleteCategoryByIdService deleteCategoryByIdService;
  private final UpdateCategoryService updateCategoryService;

  @PostMapping
  public ResponseEntity<CategoryResponse> create(@RequestBody final CreateCategoryRequest request) {
    return ResponseEntity.ok(this.createCategoryService.execute(request));
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryResponse> getById(@PathVariable final Long categoryId) {
    return ResponseEntity.ok(this.getCategoryByIdService.execute(new EntityIdRequest(categoryId)));
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<CollectionResponse<CategoryResponse>> getByDescription(@PathVariable final String description) {
    return ResponseEntity.ok(this.getCategoryByDescriptionService.execute(new FetchCategoryByDescriptionRequest(description)));
  }

  @GetMapping
  public ResponseEntity<CollectionResponse<CategoryResponse>> getAll() {
    return ResponseEntity.ok(this.getAllCategoriesService.execute(EmptyRequest.empty()));
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteById(@PathVariable final Long categoryId) {
    this.deleteCategoryByIdService.execute(new EntityIdRequest(categoryId));
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public ResponseEntity<CategoryResponse> update(@RequestBody final UpdateCategoryRequest request) {
    return ResponseEntity.ok(this.updateCategoryService.execute(request));
  }


}
