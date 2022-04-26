package br.com.gabriel.product.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

  @Serial private static final long serialVersionUID = 463544359525272305L;

  @Column(name = "description", nullable = false)
  private String description;

  @OneToMany(mappedBy = "category")
  private List<Product> products;


}
