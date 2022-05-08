package br.com.gabriel.product.application.mappers;

import br.com.gabriel.product.application.rest.request.CreateProductRequest;
import br.com.gabriel.product.application.rest.response.CategoryResponse;
import br.com.gabriel.product.application.rest.response.ProductResponse;
import br.com.gabriel.product.application.rest.response.ProductSalesResponse;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.domain.Category;
import br.com.gabriel.product.core.domain.Product;
import br.com.gabriel.product.core.domain.Supplier;
import br.com.gabriel.product.infra.http.SalesByProductResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T12:08:02-0400",
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
        LocalDateTime createdAt = null;

        category = categoryToCategoryResponse( entity.getCategory() );
        supplier = supplierToSupplierResponse( entity.getSupplier() );
        id = entity.getId();
        name = entity.getName();
        quantityAvailable = entity.getQuantityAvailable();
        createdAt = entity.getCreatedAt();

        ProductResponse productResponse = new ProductResponse( id, name, quantityAvailable, createdAt, category, supplier );

        return productResponse;
    }

    @Override
    public ProductSalesResponse fromEntity(Product entity, SalesByProductResponse salesByProduct) {
        if ( entity == null && salesByProduct == null ) {
            return null;
        }

        CategoryResponse category = null;
        SupplierResponse supplier = null;
        Long id = null;
        String name = null;
        Integer quantityAvailable = null;
        LocalDateTime createdAt = null;
        if ( entity != null ) {
            category = categoryToCategoryResponse1( entity.getCategory() );
            supplier = supplierToSupplierResponse1( entity.getSupplier() );
            id = entity.getId();
            name = entity.getName();
            quantityAvailable = entity.getQuantityAvailable();
            createdAt = entity.getCreatedAt();
        }
        List<String> sales = null;
        if ( salesByProduct != null ) {
            List<String> list = salesByProduct.salesId();
            if ( list != null ) {
                sales = new ArrayList<String>( list );
            }
        }

        ProductSalesResponse productSalesResponse = new ProductSalesResponse( id, name, quantityAvailable, createdAt, category, supplier, sales );

        return productSalesResponse;
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

    protected CategoryResponse categoryToCategoryResponse1(Category category) {
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

    protected SupplierResponse supplierToSupplierResponse1(Supplier supplier) {
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
