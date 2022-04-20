package br.com.gabriel.product.infra.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "supplier")
public class SupplierEntity extends BaseEntity {

  @Serial private static final long serialVersionUID = 1880245100425537615L;

  @Column(name = "name", nullable = false)
  private final String name;

  @OneToMany(mappedBy = "supplier")
  private final List<ProductEntity> products;

  public SupplierEntity(final Long id, final String name, final List<ProductEntity> products) {
    super(id);
    this.name = name;
    this.products = products;
  }

}
