package br.com.gabriel.product.core.services;

import br.com.gabriel.product.application.mappers.SupplierMapper;
import br.com.gabriel.product.application.rest.request.UpdateSupplierRequest;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.domain.Supplier;
import br.com.gabriel.product.infra.db.finders.SupplierFinder;
import br.com.gabriel.product.infra.db.repositories.JpaSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@AllArgsConstructor
public class UpdateSupplierService implements ExecutableService<UpdateSupplierRequest, SupplierResponse> {

  private final JpaSupplierRepository repository;
  private final SupplierFinder supplierFinder;
  private final SupplierMapper mapper;

  @Override public SupplierResponse execute(final UpdateSupplierRequest input) {
    Objects.requireNonNull(input);

    final var supplier = this.findSupplierById(input);

    supplier.setName(input.name());

    supplier.validate();

    this.repository.save(supplier);

    return this.mapper.fromEntity(supplier);
  }

  private Supplier findSupplierById(final UpdateSupplierRequest input) {
    return this.supplierFinder.find(input.id());
  }
}
