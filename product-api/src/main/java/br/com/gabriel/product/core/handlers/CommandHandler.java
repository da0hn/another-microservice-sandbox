package br.com.gabriel.product.core.handlers;

public interface CommandHandler<COMMAND extends Command> {

  void handle(COMMAND command, String serviceId);

}
