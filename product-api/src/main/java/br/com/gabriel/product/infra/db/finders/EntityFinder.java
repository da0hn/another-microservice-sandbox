package br.com.gabriel.product.infra.db.finders;

import br.com.gabriel.product.core.domain.BaseEntity;

public interface EntityFinder<E extends BaseEntity> {

  E find(Long id);

}
