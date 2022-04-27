package br.com.gabriel.product.application.mappers;

import br.com.gabriel.product.application.rest.request.CreateProductRequest;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.core.domain.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "name", target = "name"),
    @Mapping(source = "quantityAvailable", target = "quantityAvailable"),
    @Mapping(source = "category.id", target = "category.id"),
    @Mapping(source = "category.description", target = "category.description"),
    @Mapping(source = "supplier.id", target = "supplier.id"),
    @Mapping(source = "supplier.name", target = "supplier.name"),
  })
  ProductResponse fromEntity(Product entity);


  @Mappings({
    @Mapping(source = "name", target = "name"),
    @Mapping(source = "quantityAvailable", target = "quantityAvailable")
  })
  Product toEntity(CreateProductRequest request);
}
