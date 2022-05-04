package br.com.gabriel.product.core.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product extends BaseEntity implements Validable {

  @Serial private static final long serialVersionUID = 8832009239863722652L;
  @Column(name = "name", nullable = false)
  private String name;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "quantity_available", nullable = false)
  private Integer quantityAvailable;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Override public void validate() {
    if(ObjectUtils.isEmpty(this.name) || this.name.isBlank()) {
      throw new ValidationException("The product name was not informed");
    }
    if(Objects.isNull(this.quantityAvailable)) {
      throw new ValidationException("The product quantity available was not informed");
    }
    if(this.quantityAvailable < 0) {
      throw new ValidationException("The product quantity available must be positive value");
    }
    if(Objects.isNull(this.supplier)) {
      throw new ValidationException("The product suplier was not informed");
    }
    if(Objects.isNull(this.category)) {
      throw new ValidationException("The product category was not informed");
    }
  }

  public void updateStock(final Integer quantity) {
    if(quantity <= 0) {
      throw new ValidationException("The required product quantity amount is invalid: " + quantity);
    }

    if(this.quantityAvailable - quantity <= 0) {
      throw new ValidationException("The product " + this.name + " is out of stock");
    }
    this.quantityAvailable -= quantity;
  }
}
