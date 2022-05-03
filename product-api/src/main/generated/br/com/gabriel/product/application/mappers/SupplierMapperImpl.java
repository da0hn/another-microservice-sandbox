package br.com.gabriel.product.application.mappers;

import br.com.gabriel.product.application.rest.request.CreateSupplierRequest;
import br.com.gabriel.product.application.rest.response.SupplierResponse;
import br.com.gabriel.product.core.domain.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-03T18:41:55-0400",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierResponse fromEntity(Supplier entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        SupplierResponse supplierResponse = new SupplierResponse( id, name );

        return supplierResponse;
    }

    @Override
    public Supplier toEntity(CreateSupplierRequest request) {
        if ( request == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setName( request.name() );

        return supplier;
    }
}
