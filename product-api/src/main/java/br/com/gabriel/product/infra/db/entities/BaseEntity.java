package br.com.gabriel.product.infra.db.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = 1814479873005960342L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  protected BaseEntity(final Long id) {
    this.id = id;
  }

  @Override public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof BaseEntity)) return false;
    final BaseEntity that = (BaseEntity) o;
    return this.id.equals(that.id);
  }

  public Long getId() {
    return this.id;
  }
}
