package br.com.gabriel.product.core.services.product;

import br.com.gabriel.product.application.mappers.ProductMapper;
import br.com.gabriel.product.application.rest.request.CreateProductRequest;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.core.domain.Category;
import br.com.gabriel.product.core.domain.Supplier;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.finders.CategoryFinder;
import br.com.gabriel.product.infra.db.finders.SupplierFinder;
import br.com.gabriel.product.infra.db.repositories.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProductService implements ExecutableService<CreateProductRequest, ProductResponse> {

  private final JpaProductRepository repository;
  private final SupplierFinder supplierFinder;
  private final CategoryFinder categoryFinder;
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
    return this.categoryFinder.find(categoryId);
  }

  private Supplier findSupplierById(final Long supplierId) {
    return this.supplierFinder.find(supplierId);
  }
}
