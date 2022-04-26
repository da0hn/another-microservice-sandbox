package br.com.gabriel.product.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity {

  @Serial private static final long serialVersionUID = 463544359525272305L;

  @Column(name = "description", nullable = false)
  private String description;

  @OneToMany(mappedBy = "category")
  private List<Product> products;

  public void validate() {
    if(ObjectUtils.isEmpty(this.description)) {
      throw new ValidationException("The category description was not informed");
    }
  }
}
