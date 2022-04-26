package br.com.gabriel.product.application.mappers;

import br.com.gabriel.product.application.rest.request.CategoryCreateRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.core.domain.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-26T10:49:28-0400",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse fromEntity(Category entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String description = null;

        id = entity.getId();
        description = entity.getDescription();

        CategoryResponse categoryResponse = new CategoryResponse( id, description );

        return categoryResponse;
    }

    @Override
    public Category toEntity(CategoryCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Category category = new Category();

        category.setDescription( request.description() );

        return category;
    }
}
