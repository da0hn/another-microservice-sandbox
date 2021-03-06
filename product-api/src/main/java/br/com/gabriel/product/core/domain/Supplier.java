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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supplier")
public class Supplier extends BaseEntity implements Validable {

  @Serial private static final long serialVersionUID = 1880245100425537615L;

  @Column(name = "name", nullable = false)
  private String name;

  @ToString.Exclude
  @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
  private List<Product> products;

  @Override public void validate() {
    if(ObjectUtils.isEmpty(this.name)) {
      throw new ValidationException("The supplier name was not informed");
    }
  }
}
