package br.com.gabriel.product.core.handlers;

import br.com.gabriel.product.core.handlers.commands.SalesConfirmationCommand;

public interface SalesConfirmationPublisher {

  void publish(final SalesConfirmationCommand command);

}
