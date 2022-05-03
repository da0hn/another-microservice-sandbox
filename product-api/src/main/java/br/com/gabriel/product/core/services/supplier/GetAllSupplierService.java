package br.com.gabriel.product.core.services.supplier;

import br.com.gabriel.product.application.mappers.SupplierMapper;
import br.com.gabriel.product.application.rest.request.EmptyRequest;
import br.com.gabriel.product.application.rest.response.CollectionResponse;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.services.ExecutableService;
import br.com.gabriel.product.infra.db.repositories.JpaSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllSupplierService implements ExecutableService<EmptyRequest, CollectionResponse<SupplierResponse>> {

  private final JpaSupplierRepository repository;
  private final SupplierMapper mapper;

  @Override public CollectionResponse<SupplierResponse> execute(final EmptyRequest input) {
    return this.repository.findAll().stream()
      .map(this.mapper::fromEntity)
      .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionResponse::new));
  }
}
