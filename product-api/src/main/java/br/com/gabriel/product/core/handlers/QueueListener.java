package br.com.gabriel.product.core.handlers;

public interface QueueListener<COMMAND extends Command> {

  void listen(COMMAND command);

}
