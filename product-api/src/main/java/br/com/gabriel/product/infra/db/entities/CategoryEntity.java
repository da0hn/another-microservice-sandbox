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
  private String description;

  @OneToMany(mappedBy = "category")
  private List<ProductEntity> products;

}
