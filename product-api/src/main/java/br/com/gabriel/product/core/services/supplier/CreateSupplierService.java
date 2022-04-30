package br.com.gabriel.product.core.services.supplier;


import br.com.gabriel.product.application.mappers.SupplierMapper;
import br.com.gabriel.product.application.rest.request.CreateSupplierRequest;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateSupplierService implements ExecutableService<CreateSupplierRequest, SupplierResponse> {

  private final JpaSupplierRepository repository;
  private final SupplierMapper mapper;

  @Override public SupplierResponse execute(final CreateSupplierRequest input) {
    final var supplier = this.mapper.toEntity(input);

    supplier.validate();

    this.repository.save(supplier);

    return this.mapper.fromEntity(supplier);
  }

}
