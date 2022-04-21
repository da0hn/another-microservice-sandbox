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
  private String name;

  @OneToMany(mappedBy = "supplier")
  private List<ProductEntity> products;

}
