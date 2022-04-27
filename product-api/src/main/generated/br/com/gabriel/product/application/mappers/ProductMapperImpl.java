package br.com.gabriel.product.application.mappers;

import br.com.gabriel.product.application.rest.request.CreateProductRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.domain.Category;
import br.com.gabriel.product.core.domain.Product;
import br.com.gabriel.product.core.domain.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-27T16:52:21-0400",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse fromEntity(Product entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryResponse category = null;
        SupplierResponse supplier = null;
        Long id = null;
        String name = null;
        Integer quantityAvailable = null;

        category = categoryToCategoryResponse( entity.getCategory() );
        supplier = supplierToSupplierResponse( entity.getSupplier() );
        id = entity.getId();
        name = entity.getName();
        quantityAvailable = entity.getQuantityAvailable();

        ProductResponse productResponse = new ProductResponse( id, name, quantityAvailable, category, supplier );

        return productResponse;
    }

    @Override
    public Product toEntity(CreateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( request.name() );
        product.setQuantityAvailable( request.quantityAvailable() );

        return product;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String description = null;

        id = category.getId();
        description = category.getDescription();

        CategoryResponse categoryResponse = new CategoryResponse( id, description );

        return categoryResponse;
    }

    protected SupplierResponse supplierToSupplierResponse(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = supplier.getId();
        name = supplier.getName();

        SupplierResponse supplierResponse = new SupplierResponse( id, name );

        return supplierResponse;
    }
}
