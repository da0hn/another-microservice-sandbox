package br.com.gabriel.product.infra.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

  @Serial private static final long serialVersionUID = 463544359525272305L;

  @Column(name = "description", nullable = false)
  private final String description;

  @OneToMany(mappedBy = "category")
  private final List<ProductEntity> products;

  public CategoryEntity(final Long id, final String description, final List<ProductEntity> products) {
    super(id);
    this.description = description;
    this.products = products;
  }


}
