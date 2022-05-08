package br.com.gabriel.product.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.util.List;

@Entity
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity implements Validable {

  @Serial private static final long serialVersionUID = 463544359525272305L;

  @Column(name = "description", nullable = false)
  private String description;

  @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
  private List<Product> products;

  @Override public void validate() {
    if(ObjectUtils.isEmpty(this.description)) {
      throw new ValidationException("The category description was not informed");
    }
  }
}
