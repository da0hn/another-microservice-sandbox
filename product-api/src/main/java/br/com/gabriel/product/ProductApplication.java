package br.com.gabriel.product;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ProductApplication {

  public static void main(final String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }

}
