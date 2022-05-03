package br.com.gabriel.product.application.config;

import br.com.gabriel.product.application.security.AuthenticationInterceptor;
import br.com.gabriel.product.application.security.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

  private final AuthenticationService authenticationService;

  @Override public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(this.authenticationInterceptor());
  }

  @Bean
  public AuthenticationInterceptor authenticationInterceptor() {
    return new AuthenticationInterceptor(this.authenticationService);
  }
}
