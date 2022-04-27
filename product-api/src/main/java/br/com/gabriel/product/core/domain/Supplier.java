package br.com.gabriel.product.core.domain;

import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity {

  @Serial private static final long serialVersionUID = 1880245100425537615L;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "supplier")
  private List<Product> products;

  public void validate() {
    if(ObjectUtils.isEmpty(this.name)) {
      throw new ValidationException("The supplier name was not informed");
    }
  }
}
