package br.com.gabriel.product.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


  @Value("${app-config.rabbit.exchange.product}")
  private String productTopicExchange;
  @Value("${app-config.rabbit.routing-key.product-stock}")
  private String productStockUpdateKey;
  @Value("${app-config.rabbit.routing-key.sales-confirmation}")
  private String salesConfirmationKey;
  @Value("${app-config.rabbit.queue.product-stock}")
  private String productStockUpdateQueue;
  @Value("${app-config.rabbit.queue.sales-confirmation}")
  private String salesConfirmationQueue;

  @Bean
  public TopicExchange getProductTopicExchange() {
    return new TopicExchange(this.productTopicExchange);
  }

  @Bean
  public Binding productStockUpdateBinding(final TopicExchange topicExchange) {
    return BindingBuilder
      .bind(this.productStockUpdateQueue())
      .to(topicExchange)
      .with(this.productStockUpdateKey);
  }

  @Bean
  public Queue productStockUpdateQueue() {
    return new Queue(this.productStockUpdateQueue, true);
  }

  @Bean
  public Binding salesConfirmationQueueBinding(final TopicExchange topicExchange) {
    return BindingBuilder
      .bind(this.salesConfirmationQueue())
      .to(topicExchange)
      .with(this.salesConfirmationKey);
  }

  @Bean
  public Queue salesConfirmationQueue() {
    return new Queue(this.salesConfirmationQueue, true);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
