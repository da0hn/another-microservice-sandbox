package br.com.gabriel.product.application.mappers;

import br.com.gabriel.product.application.rest.request.CreateCategoryRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.core.domain.Category;
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
public interface CategoryMapper {

  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "description", target = "description")
  })
  CategoryResponse fromEntity(Category entity);

  @Mappings({
    @Mapping(source = "description", target = "description")
  })
  Category toEntity(CreateCategoryRequest request);

}
