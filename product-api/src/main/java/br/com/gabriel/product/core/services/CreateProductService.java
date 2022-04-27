package br.com.gabriel.product.core.services;

import br.com.gabriel.product.application.mappers.ProductMapper;
import br.com.gabriel.product.application.rest.request.CreateProductRequest;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.core.domain.Category;
import br.com.gabriel.product.core.domain.EntityNotFoundException;
import br.com.gabriel.product.core.domain.Supplier;
import br.com.gabriel.product.core.domain.ValidationException;
import br.com.gabriel.product.infra.db.repositories.JpaCategoryRepository;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import br.com.gabriel.product.infra.db.repositories.JpaSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateProductService implements ExecutableService<CreateProductRequest, ProductResponse> {

  private final JpaProductRepository repository;
  private final JpaSupplierRepository supplierRepository;
  private final JpaCategoryRepository categoryRepository;
  private final ProductMapper mapper;


  @Override public ProductResponse execute(final CreateProductRequest input) {

    final var product = this.mapper.toEntity(input);

    final var category = this.findCategoryById(input.categoryId());
    final var supplier = this.findSupplierById(input.supplierId());

    product.setCategory(category);
    product.setSupplier(supplier);

    product.validate();

    this.repository.save(product);

    return this.mapper.fromEntity(product);
  }

  private Category findCategoryById(final Long categoryId) {
    return Optional.ofNullable(categoryId)
      .map(id -> this.categoryRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("The category " + categoryId + " was not found"))
      ).orElseThrow(() -> new ValidationException("The category id was not informed"));
  }

  private Supplier findSupplierById(final Long supplierId) {
    return Optional.ofNullable(supplierId).map(id -> this.supplierRepository
      .findById(supplierId)
      .orElseThrow(() -> new EntityNotFoundException("The supplier " + supplierId + " was not found"))
    ).orElseThrow(() -> new ValidationException("The supplier id was not informed"));
  }
}
