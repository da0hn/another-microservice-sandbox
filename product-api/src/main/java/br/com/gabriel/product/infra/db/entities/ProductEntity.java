package br.com.gabriel.product.infra.db.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

  @Serial private static final long serialVersionUID = 8832009239863722652L;
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "quantity_available", nullable = false)
  private Integer quantityAvailable;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private SupplierEntity supplier;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryEntity category;

}
