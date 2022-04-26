package br.com.gabriel.product.core.services;

public interface ExecutableService<INPUT extends ExecutableService.Input, OUTPUT extends ExecutableService.Output> {

  OUTPUT execute(final INPUT input);

  interface Input {}

  interface Output {}

}
